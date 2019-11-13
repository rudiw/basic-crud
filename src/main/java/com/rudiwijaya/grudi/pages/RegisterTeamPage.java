package com.rudiwijaya.grudi.pages;

import java.util.List;
import java.util.function.Predicate;

import javax.inject.Inject;
import javax.persistence.OptimisticLockException;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
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
import com.rudiwijaya.grudi.dao.JpaTeamRepository;
import com.rudiwijaya.grudi.jpa.Gender;
import com.rudiwijaya.grudi.jpa.Person;
import com.rudiwijaya.grudi.jpa.PersonInfo;
import com.rudiwijaya.grudi.jpa.Team;
import com.rudiwijaya.grudi.jpa.TeamLine;
import com.rudiwijaya.grudi.jpa.TeamPersonKind;
import com.rudiwijaya.grudi.security.NotLoggedInException;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda.LaddaAjaxButton;

/**
 * @author rudi
 *
 */
@SuppressWarnings("serial")
@MountPath("registerteam/")
public class RegisterTeamPage extends BasePage {
	
	private static final Logger log = LoggerFactory.getLogger(RegisterTeamPage.class);
	
	@Inject
	private JpaTeamRepository teamRepo;
	
	private final TermAndConditionsModal termAndConditionsModal;
	
	/**
	 * @param params
	 */
	public RegisterTeamPage(PageParameters params) {
		super(params);
		
		final WicketSession wSession = (WicketSession) getSession();
		if (!wSession.isAuthenticated()) {
			throw new NotLoggedInException("Mohon untuk Login dahulu.");
		}
		
		final IModel<Long> editTeamIdModel = new Model<Long>();
		if (!getPageParameters().get("teamId").isNull()) {
			editTeamIdModel.setObject(getPageParameters().get("teamId").toLong());
		}
		
		final IModel<Person> loggedInPersonModel = new LoggedInPersonModel(wSession);
		
		add(new Label("loggedInPersonName", new PropertyModel<>(loggedInPersonModel, "name")));
		
		add(new AjaxLink<TeamListPage>("linkTeamList") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				getRequestCycle().setResponsePage(TeamListPage.class);
			}
		});
		
		add(new LogoutLink("linkLogout"));
		
		add(new RegisterTeamForm("form", loggedInPersonModel, editTeamIdModel));
		
		termAndConditionsModal = new TermAndConditionsModal("termAndConditionsModal");
		add(termAndConditionsModal);
	}
	
	private class RegisterTeamForm extends Form<Void> {
		
		public RegisterTeamForm(final String id, final IModel<Person> loggedInPersonModel,
				final IModel<Long> editTeamIdModel) {
			super(id);
			
			final IModel<Team> teamModel = new Model<>();
			
			if (editTeamIdModel.getObject() != null) {
				final Team editTeam = teamRepo.findOneWithLines(editTeamIdModel.getObject());
				teamModel.setObject(editTeam);
			} else {
				teamModel.setObject(new Team());
			}
			
			final TextField<String> txtName = new TextField<>("txtName", new PropertyModel<>(teamModel, "name"));
			txtName.setRequired(true).setLabel(new Model<>("Username"));
			add(txtName);
			
			//captain
			final IModel<PersonInfo> captainModel = new Model<>();
			if (editTeamIdModel.getObject() != null) {
				captainModel.setObject( teamModel.getObject().getLines().stream().filter(new Predicate<TeamLine>() {
					@Override
					public boolean test(TeamLine t) {
						return t.getKind() == TeamPersonKind.CAPTAIN;
					}
				}).findFirst().get().getPerson() );
			} else {
				final PersonInfo captain = new PersonInfo();
				captainModel.setObject(captain);
			}
			
			
			final TextField<String> txtCaptainName = new TextField<>("txtCaptainName", new PropertyModel<>(captainModel, "name"));
			txtCaptainName.setRequired(true).setLabel(new Model<>("Nama Kapten"));
			add(txtCaptainName);
			
			final TextField<String> captainPhoneNumber = new TextField<>("captainPhoneNumber", new PropertyModel<>(captainModel, "phoneNumber"));
			add(captainPhoneNumber);
			
			add( new DropDownChoice<>("captainGender", new PropertyModel<>(captainModel, "gender"),
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

			//member1
			final IModel<PersonInfo> member1Model = new Model<>();
			if (editTeamIdModel.getObject() != null) {
				member1Model.setObject( teamModel.getObject().getLines().stream().filter(new Predicate<TeamLine>() {
					@Override
					public boolean test(TeamLine t) {
						return t.getKind() == TeamPersonKind.MEMBER;
					}
				}).findFirst().get().getPerson() );
			} else {
				final PersonInfo member1 = new PersonInfo();
				member1Model.setObject(member1);
			}
			final TextField<String> txtMember1Name = new TextField<>("txtMember1Name", new PropertyModel<>(member1Model, "name"));
			txtMember1Name.setRequired(true).setLabel(new Model<>("Nama Anggota 1"));
			add(txtMember1Name);
			
			final TextField<String> member1PhoneNumber = new TextField<>("member1PhoneNumber", new PropertyModel<>(member1Model, "phoneNumber"));
			add(member1PhoneNumber);
			
			add( new DropDownChoice<>("member1Gender", new PropertyModel<>(member1Model, "gender"),
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
			
			add( new LaddaAjaxButton("btnRegister", new Model<>("REGISTRASI"), Type.Danger){
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					super.onSubmit(target, form);
					
					final Team upTeam = teamModel.getObject();
					
					if (!SlugUtils.TEAM_NAME.matcher(upTeam.getName()).matches()) {
						error("Team Name field length must between 4-15 character, Unique, and only '_', alphabet and numeric allowed.");
						return;
					}
					
					final PersonInfo objCaptain = captainModel.getObject();
					if (!Strings.isNullOrEmpty(objCaptain.getPhoneNumber())
							&& !SlugUtils.PHONE_NUMBER.matcher(objCaptain.getPhoneNumber()).matches()) {
						error("Captain's Phone field length must between 7-14 and only numeric allowed.");
						return;
					}
					
					final PersonInfo objMember1 = member1Model.getObject();
					if (!Strings.isNullOrEmpty(objMember1.getPhoneNumber())
							&& !SlugUtils.PHONE_NUMBER.matcher(objMember1.getPhoneNumber()).matches()) {
						error("Member 1's Phone field length must between 7-14 and only numeric allowed.");
						return;
					}
					
					if (editTeamIdModel.getObject() == null && teamRepo.existsByName(upTeam.getName())) {
						error("Nama sudah terpakai!");
						return;
					}
					
					if (editTeamIdModel.getObject() != null) {
						upTeam.clearLines();
					}
					
					//add captain line
					final TeamLine captainLine = new TeamLine();
					captainLine.setKind(TeamPersonKind.CAPTAIN);
					captainLine.setPerson(objCaptain);
					upTeam.addToLines(captainLine);
					
					//add member1 line
					final TeamLine member1Line = new TeamLine();
					member1Line.setKind(TeamPersonKind.MEMBER);
					member1Line.setPerson(member1Model.getObject());
					upTeam.addToLines(member1Line);
					
					try {
						if (editTeamIdModel.getObject() != null) {
							teamModel.setObject( teamRepo.modify(editTeamIdModel.getObject(), upTeam) );
						} else {
							teamModel.setObject( teamRepo.add(upTeam) );
						}
						
						
						getRequestCycle().setResponsePage(FinishPage.class);
						
						getSession().info("Team sudah di registrasi :).");
					} catch (Exception e) {
						final String eMsg = String.format("Error register team '%s': %s", teamModel.getObject().getName(), e);
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
