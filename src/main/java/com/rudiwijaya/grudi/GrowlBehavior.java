package com.rudiwijaya.grudi;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.feedback.FeedbackCollector;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.rudiwijaya.grudi.sound.cleanus1.Cleanus1Sounds;

/**
 * <a href="https://github.com/ifightcrime/bootstrap-growl">bootstrap-growl</a> based {@link FeedbackMessage} notifier
 * for Bootstrap 3.x.
 * 
 * <p><a href="http://cdnjs.com/libraries/bootstrap-growl/">CDNJS</a> URIs:
 * <ul>
 * 	<li>//cdnjs.cloudflare.com/ajax/libs/bootstrap-growl/1.0.0/jquery.bootstrap-growl.js
 * 	<li>//cdnjs.cloudflare.com/ajax/libs/bootstrap-growl/1.0.0/jquery.bootstrap-growl.min.js
 * </ul>
 * 
 * <p>This also checks the {@link FeedbackMessage} during page render. To use it during page redirect,
 * use {@link Session#info(java.io.Serializable)} / {@link Session#warn(java.io.Serializable)} / {@link Session#error(java.io.Serializable)}
 * instead of {@link Component#info(java.io.Serializable)}'s variants.
 * Note that if you already have a {@link FeedbackPanel} in your {@link Page}, the panel will consume {@link FeedbackMessage}s before
 * {@link GrowlBehavior}.
 * 
 * <p>If you want to <a href="http://apache-wicket.1842946.n4.nabble.com/setResponsePage-swallows-my-session-feedback-messages-td4650274.html">preserve {@link FeedbackMessage} across page redirects</a>,
 *  please use {@link Session#info(java.io.Serializable)}
 * or {@link Interaction#info(String, Object...)}, not from {@link Component#info(java.io.Serializable)}.
 * 
 * <p>(Obsolete) <del>For Ajax behavior explanation, see:
 * 
 * <ul>
 * 	<li>http://www.wexoo.net/20110831/building-a-custom-feedbackpanel-in-wicket-with-js</li>
 * 	<li>http://javathoughts.capesugarbird.com/2009/06/replacing-wickets-feedbackpanel-with.html</li>
 * </ul></del>
 * 
 * @author ceefour
 */
@SuppressWarnings("serial")
public class GrowlBehavior extends Behavior {

	private static Logger log = LoggerFactory.getLogger(GrowlBehavior.class);
	private static JavaScriptResourceReference GROWL_JS = new JavaScriptResourceReference(GrowlBehavior.class, "jquery.bootstrap-growl-20e918de.js") {
		@Override
		public java.util.List<HeaderItem> getDependencies() {
			return ImmutableList.of(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));
		};
	};
	
	/**
	 * Use {@link Cleanus1Sounds} theme.
	 */
	public GrowlBehavior() {
		super();
		Injector.get().inject(this);
	}


	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);
		response.render(JavaScriptHeaderItem.forReference(GROWL_JS));
//		Howler.renderHead(component, response, Howler.getActive());
		
		final String script = getNotifyScript("renderHead", component.getPage());
		if (script != null) {
			log.trace("onLoad FeedbackMessages: {}", script);
			response.render(OnLoadHeaderItem.forScript(script));
		}
	}
	
	@Override
	public void onEvent(Component component, IEvent<?> event) {
		super.onEvent(component, event);
//		log.debug("We get event {}", event.getPayload());
		if (event.getPayload() instanceof AjaxRequestTarget) {
			final AjaxRequestTarget target = (AjaxRequestTarget) event.getPayload();
			final String script = getNotifyScript("AjaxRequestTarget", target.getPage());
			if (script != null) {
				target.appendJavaScript(script);
			}
		}
	}

	/**
	 * Gets the bootstrap growl notify popup script for FeedbackMessages for the specified Page.
	 * @return String Script, or {@code null} if no message needs to be displayed.
	 */
	@Nullable
	protected String getNotifyScript(String purpose, Page page) {
		final List<FeedbackMessage> feedbackMessages = new FeedbackCollector(page).collect();
		if (!feedbackMessages.isEmpty()) {
			log.debug("{} got {} feedback messages during {}", Session.get(), feedbackMessages.size(), purpose);
			String script = "";
			for (final FeedbackMessage msg : feedbackMessages) {
				final String growlType;
//				final String howlerScript;
				final Optional<Interaction> interaction = msg.getMessage() instanceof InteractionMessage ? 
						Optional.of(((InteractionMessage) msg.getMessage()).getInteraction()) : Optional.<Interaction>absent();
				log.trace("{} message: {}", interaction, msg);
//				final Sounds sounds = Howler.getActive();
				if (msg.isError()) {
					growlType = "danger";
//					howlerScript = Howler.play(interaction.or(Interaction.ERROR), sounds);
				} else if (msg.isWarning()) {
					growlType = "warning";
//					howlerScript = Howler.play(interaction.or(Interaction.WARNING), sounds);
				} else if (msg.isInfo()) {
					growlType = "success";
//					howlerScript = Howler.play(interaction.or(Interaction.INFO), sounds);
				} else if (msg.isDebug()) {
					growlType = "info";
//					howlerScript = Howler.play(interaction.or(Interaction.INFO), sounds);
				} else {
					growlType = "info";
//					howlerScript = Howler.play(interaction.or(Interaction.INFO), sounds);
				}
				
				final String messageText = !Strings.isNullOrEmpty(Objects.toString(msg.getMessage(), null)) ?
						msg.getMessage().toString() : "Unknown error: " + msg.getReporter().getPageRelativePath().replace(":", " > ");
				msg.markRendered();
				
//				script += howlerScript;
				script += "$.bootstrapGrowl(" +
					JSONObject.quote(messageText) + ", {type: '" + growlType + "', delay: 3000});\n";					
			}
			return Strings.emptyToNull(script);
		} else {
			return null;
		}
	}

}
