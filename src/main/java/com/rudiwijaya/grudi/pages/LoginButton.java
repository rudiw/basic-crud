package com.rudiwijaya.grudi.pages;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.wicket.ajax.AjaxChannel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.rudiwijaya.grudi.AppUtils;
import com.rudiwijaya.grudi.WicketSession;
import com.rudiwijaya.grudi.security.LoginToken;

/**
 * Generic {@link IndicatingAjaxButton} that can be used with any Spring Security {@link Realm},
 * provided it accepts {@link UsernamePasswordToken}.
 *
 * <p>You need to override {@link #onLoginSuccess(AjaxRequestTarget, String)}.</p>
 *
 * <p>It's for stateful login form, if you want stateless use {@link StatelessLoginForm}.</p>
 *
 * <p>Requirements:</p>
 *
 * <ol>
 *     <li>Shiro {@link org.apache.shiro.realm.AuthenticatingRealm}</li>
 *     <li>{@link org.apache.wicket.protocol.http.WebApplication#newSession(Request, Response)} must return {@link SoluvasWebSession}</li>
 * </ol>
 *
 * @author rudi
 * @author ceefour
 * @see DedicatedLoginPage
 * @see org.apache.wicket.protocol.http.WebApplication#newSession(Request, Response)
 * @see SoluvasWebSession
 * @see StatelessLoginForm
 */
@SuppressWarnings("serial")
public class LoginButton extends IndicatingAjaxButton {
	private static final Logger log = LoggerFactory.getLogger(LoginButton.class);
	
	private final IModel<LoginToken> loginTokenModel;
	
	/**
	 * @param id
	 * @param loginTokenModel
	 * @param host To be used for {@link UsernamePasswordToken#setHost(String)}, usually {@link TenantRef#getTenantId()} for tenant logins
	 * 		and {@link StaticAppRealm#HOST} for app login.
	 */
	public LoginButton(String id, final IModel<LoginToken> loginTokenModel) {
		super(id);
		this.loginTokenModel = loginTokenModel;
	}
	
	/**
	 * Makes this a stateless AJAX button.
	 * @todo Find a better, more convenient, superclass.
	 */
	@Override
	protected boolean getStatelessHint() {
		return true;
	}
	
	/**
	 * Makes this a stateless AJAX button.
	 * @todo Find a better, more convenient, superclass.
	 */
	@Override
	protected AjaxFormSubmitBehavior newAjaxFormSubmitBehavior(String event)
	{
		return new AjaxFormSubmitBehavior(getForm(), event)
		{
			@Override
			public boolean getStatelessHint(org.apache.wicket.Component component) {
				return true;
			};
			
			@Override
			protected void onSubmit(AjaxRequestTarget target)
			{
				LoginButton.this.onSubmit(target, LoginButton.this.getForm());
			}

			@Override
			protected void onAfterSubmit(AjaxRequestTarget target)
			{
				LoginButton.this.onAfterSubmit(target, LoginButton.this.getForm());
			}

			@Override
			protected void onError(AjaxRequestTarget target)
			{
				LoginButton.this.onError(target, LoginButton.this.getForm());
			}

			@Override
			protected AjaxChannel getChannel()
			{
				return LoginButton.this.getChannel();
			}

			@Override
			protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
			{
				super.updateAjaxAttributes(attributes);
				LoginButton.this.updateAjaxAttributes(attributes);
			}

			@Override
			public boolean getDefaultProcessing()
			{
				return LoginButton.this.getDefaultFormProcessing();
			}
		};
	}

	protected void doAuthenticate(@Nullable AjaxRequestTarget target) {
		final LoginToken loginData = loginTokenModel.getObject();
		final String upUsername = Strings.nullToEmpty(loginData.getUsername());
		final String upPassword = Strings.nullToEmpty(loginData.getPassword());
		final UsernamePasswordToken token = new UsernamePasswordToken(upUsername, upPassword.toCharArray(), AppUtils.APP_NAME);
//		final UsernamePasswordToken token = new UsernamePasswordToken(upUsername, upPassword.toCharArray());
		log.debug("Logging in using '{}'", upUsername);
		try {
//			final Subject currentUser = SecurityUtils.getSubject();
			final HttpServletRequest servletRequest = (HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest();
			final HttpServletResponse servletResponse = (HttpServletResponse) RequestCycle.get().getResponse().getContainerResponse();
			final Subject currentUser = new WebSubject.Builder(SecurityUtils.getSecurityManager(), servletRequest, servletResponse).buildSubject();
			currentUser.login(token);
			ThreadContext.bind(currentUser);
			
			final String personId = Preconditions.checkNotNull((String) currentUser.getPrincipal(),
					"Cannot get current user as person ID");
			
			//FIXME: workaround for missed SecurityUtil.getSubject
			final WicketSession wSession = (WicketSession) getSession();
			wSession.authenticate(personId, upPassword);
			
			log.debug("Current user is now '{}'.", personId);
			if (target != null) {
				onLoginSuccess(target, personId);
			} else {
				onLoginSuccessStateless(personId);
			}
		} catch (final AuthenticationException e) {
			getSession().error(String.format("Wrong Username/Email and password combination."));
			log.info(String.format("Invalid credentials for '%s' tenant '%s'",
					token.getUsername(), token.getHost()), e);
		}
	}

	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		super.onSubmit(target, form);
		doAuthenticate(target);
	}
	
	@Override
	public void onSubmit() {
		super.onSubmit();
		doAuthenticate(null);
	}

	/**
	 * Override this method to handle Ajax submit <b>only</b>, for example, redirect to original page
	 * (if using {@link DedicatedLoginPage}).
	 * @param target 
	 * @param personId
	 */
	protected void onLoginSuccess(AjaxRequestTarget target, String personId) {
	}

	/**
	 * Override this method to handle <b>non</b>-Ajax submit, for example, redirect to original page
	 * (if using {@link DedicatedLoginPage}).
	 * @param personId
	 */
	protected void onLoginSuccessStateless(String personId) {
	}

}
