package com.rudiwijaya.grudi.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import com.rudiwijaya.grudi.SlugUtils;
import com.rudiwijaya.grudi.security.LoginToken;

/**
 * @author clutax
 *
 */
@SuppressWarnings("serial")
@MountPath("login/")
public class DedicatedLoginPage extends BasePage {
	
	public DedicatedLoginPage(PageParameters params) {
		super(params);
		
		add( new HomeForm("form") );
	}
	
	private class HomeForm extends Form<Void> {
		
		public HomeForm(final String id) {
			super(id);
			
			final IModel<LoginToken> loginTokenModel = new Model<>(new LoginToken());
			
			final IModel<String> usernameModel = new Model<String>(){
				@Override
				public void setObject(String object) {
					super.setObject(object);
					loginTokenModel.getObject().setUsername(object);
				}
			};	
			final TextField<String> username = new TextField<>("username", usernameModel);
			username.setRequired(true).setLabel(new Model<>("Username"));
			add(username);
			
			final IModel<String> passwordModel = new Model<String>(){
				@Override
				public void setObject(String object) {
					super.setObject(object);
					loginTokenModel.getObject().setPassword(object);
				}
			};
			final PasswordTextField password = new PasswordTextField("password", passwordModel);
			password.setRequired(true).setLabel(new Model<>("Password"));
			add(password);
			
			add( new LoginButton("btnLogin", loginTokenModel){
				@Override
				public void onSubmit() {
					if (!SlugUtils.PERSON_USERNAME.matcher(loginTokenModel.getObject().getUsername()).matches()) {
						error("Username field length must between 6-15 character, and only alphabet and numeric are allowed.");
						return;
					}
					
//					if (!SlugUtils.PERSON_PASSWORD.matcher(loginTokenModel.getObject().getPassword()).matches()) {
//						error("Password field length must between 8-16 character.");
//						return;
//					}
					
					super.onSubmit();
				};
				
				@Override
				protected void onSubmit(org.apache.wicket.ajax.AjaxRequestTarget target, org.apache.wicket.markup.html.form.Form<?> form) {
					if (!SlugUtils.PERSON_USERNAME.matcher(loginTokenModel.getObject().getUsername()).matches()) {
						error("Username field length must between 6-15 character, and only alphabet and numeric are allowed.");
						return;
					}
					
//					if (!SlugUtils.PERSON_PASSWORD.matcher(loginTokenModel.getObject().getPassword()).matches()) {
//						error("Password field length must between 8-16 character.");
//						return;
//					}
					
					super.onSubmit(target, form);
				};
				
				@Override
				protected void onLoginSuccess(org.apache.wicket.ajax.AjaxRequestTarget target, String personId) {
					getRequestCycle().setResponsePage(RegisterTeamPage.class);
				};
				
				@Override
				protected void onLoginSuccessStateless(String personId) {
					getRequestCycle().setResponsePage(RegisterTeamPage.class);
				};
			} );
			
			add( new BookmarkablePageLink<>("linkRegisterPage", RegisterPersonPage.class) );
		}
		
	}
	
}
