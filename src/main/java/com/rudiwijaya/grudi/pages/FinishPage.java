package com.rudiwijaya.grudi.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import com.rudiwijaya.grudi.WicketSession;
import com.rudiwijaya.grudi.jpa.Person;
import com.rudiwijaya.grudi.security.NotLoggedInException;

/**
 * @author clutax
 *
 */
@SuppressWarnings("serial")
@MountPath("finish/")
public class FinishPage extends BasePage {
	
	public FinishPage(PageParameters params) {
		super(params);
		
		final WicketSession wSession = (WicketSession) getSession();
		if (!wSession.isAuthenticated()) {
			throw new NotLoggedInException("Mohon untuk Login dahulu.");
		}
		
		final IModel<Person> loggedInPersonModel = new LoggedInPersonModel(wSession);
		
		add(new Label("loggedInPersonName", new PropertyModel<>(loggedInPersonModel, "name")));
		
		add(new LogoutLink("linkLogout"));
		
		add( new FinishForm("form") );
	}
	
	private class FinishForm extends Form<Void> {
		
		public FinishForm(final String id) {
			super(id);
			
			add(new ExternalLink("linkForum", new Model<>("http://www.rudiwijaya.com")));
			add( new LogoutLink("btnLogout"));
		}
		
	}
	
}
