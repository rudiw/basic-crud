package com.rudiwijaya.grudi.pages;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.OptimisticLockException;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.LockAcquisitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.annotation.mount.MountPath;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.rudiwijaya.grudi.SlugUtils;
import com.rudiwijaya.grudi.WicketSession;
import com.rudiwijaya.grudi.dao.JpaPersonRepository;
import com.rudiwijaya.grudi.jpa.Gender;
import com.rudiwijaya.grudi.jpa.Person;
import com.rudiwijaya.grudi.security.HashedPasswordUtils;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda.LaddaAjaxButton;

/**
 * @author rudi
 *
 */
@SuppressWarnings("serial")
@MountPath("register/")
public class RegisterPersonPage extends BasePage {
	
	private static final Logger log = LoggerFactory.getLogger(RegisterPersonPage.class);
	
	@Inject
	private JpaPersonRepository personRepo;

	private final TermAndConditionsModal termAndConditionsModal;

	/**
	 * @param params
	 */
	public RegisterPersonPage(PageParameters params) {
		super(params);
		
		final WicketSession wSession = (WicketSession) getSession();
		if (wSession.isAuthenticated()) {
			info("Anda sudah login.");
			getRequestCycle().setResponsePage(HomePage.class);
		}
		
		add(new RegisterPersonForm("form"));
		
		termAndConditionsModal = new TermAndConditionsModal("termAndConditionsModal");
		add(termAndConditionsModal);
	}
	
	private class RegisterPersonForm extends Form<Void> {
		
		public RegisterPersonForm(final String id) {
			super(id);
			
			final IModel<Person> personModel = new Model<>(new Person());
			
			final TextField<String> username = new TextField<>("username", new PropertyModel<>(personModel, "username"));
			username.setRequired(true).setLabel(new Model<>("Username"));
			add(username);
			
			final TextField<String> txtName = new TextField<>("txtName", new PropertyModel<>(personModel, "name"));
			txtName.setRequired(true).setLabel(new Model<>("Nama"));
			add(txtName);
			
			final EmailTextField txtEmail = new EmailTextField("txtEmail", new PropertyModel<>(personModel, "email"), RfcCompliantEmailAddressValidator.getInstance());
			txtEmail.setRequired(true).setLabel(new Model<>("Email"));
			add(txtEmail);
			
			final TextField<String> phoneNumber = new TextField<>("phoneNumber", new PropertyModel<>(personModel, "phoneNumber"));
			add(phoneNumber);
			
			add( new DropDownChoice<>("gender", new PropertyModel<>(personModel, "gender"),
					new ListModel<>(Gender.VALUES), new IChoiceRenderer<Gender>() {

				@Override
				public Object getDisplayValue(Gender object) {
					return WordUtils.capitalizeFully(object.name().replace("_", " "));
				}

				@Override
				public String getIdValue(Gender object, int index) {
					return object.name();
				}
				
			}) );
			
			add( new TextArea("address", new PropertyModel<>(personModel, "address")) );
			
			final IModel<String> passwordModel = new Model<>();
			final PasswordTextField password = new PasswordTextField("password", passwordModel);
			password.setRequired(true).setLabel(new Model<>("Kata Sandi"));
			add(password);
			
			final IModel<String> repasswordModel = new Model<>();
			final PasswordTextField repassword = new PasswordTextField("repassword", repasswordModel);
			repassword.setRequired(true).setLabel(new Model<>("Pengulangan Kata Sandi"));
			add(repassword);
			
			add(new EqualPasswordInputValidator(password, repassword));
			
			add( new LaddaAjaxButton("btnLogin", new Model<>("LOGIN"), Type.Danger){
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					super.onSubmit(target, form);
					
					final Person upPerson = personModel.getObject();
					
					if (!SlugUtils.PERSON_USERNAME.matcher(upPerson.getUsername()).matches()) {
						error("Username field length must between 6-15 character, and only alphabet and numeric are allowed.");
						return;
					}
					
					if (!SlugUtils.PERSON_PASSWORD.matcher(repasswordModel.getObject()).matches()) {
						error("Password field length must between 8-16 character.");
						return;
					}
					
					if (!Strings.isNullOrEmpty(upPerson.getPhoneNumber())
							&& !SlugUtils.PHONE_NUMBER.matcher(upPerson.getPhoneNumber()).matches()) {
						error("Phone field length must between 7-14 and only numeric allowed.");
						return;
					}
					
					if (personRepo.existsByUsername(upPerson.getUsername())) {
						error("Username sudah terpakai!");
						return;
					}
					
					upPerson.setPassword(HashedPasswordUtils.encodeSsha(repasswordModel.getObject()));
					
					try {
						personModel.setObject( personRepo.add(upPerson) );
						
						getRequestCycle().setResponsePage(DedicatedLoginPage.class);
						
						getSession().info("Anda sudah berhasil mendaftar, silahkan login.");
					} catch (Exception e) {
						final String eMsg = String.format("Error register new person '%s': %s", personModel.getObject().getUsername(), e);
						final List<Throwable> causes = Throwables.getCausalChain(e);
						if (causes.stream().anyMatch((it) -> it instanceof LockAcquisitionException) || causes.stream().anyMatch((it) -> it instanceof GenericJDBCException)) {
							log.info(eMsg, e);
							info("Server sedang sibuk, silahkan coba lagi.");
						} else if (causes.stream().anyMatch((it) -> it instanceof OptimisticLockException)) {
							log.info(eMsg, e);
							info("Data telah berubah atau terhapus, silahkan refresh.");
						} else {
							log.error(eMsg, e);
							error(eMsg);
						}
						return;
					}
				}
			} );
			
			add( new AjaxLink<Void>("linkTermAndConditions") {
				@Override
				public void onClick(AjaxRequestTarget target) {
					termAndConditionsModal.appendShowDialogJavaScript(target);
					target.add(termAndConditionsModal);
				};
			} );
		}
		
	}//end of main form

}
