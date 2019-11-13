package com.rudiwijaya.grudi.pages;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.annotation.mount.MountPath;

import com.rudiwijaya.grudi.WicketSession;
import com.rudiwijaya.grudi.dao.JpaTeamRepository;
import com.rudiwijaya.grudi.jpa.Person;
import com.rudiwijaya.grudi.jpa.Team;
import com.rudiwijaya.grudi.security.NotLoggedInException;

/**
 * @author rudi
 *
 */
@SuppressWarnings("serial")
@MountPath("teams/")
public class TeamListPage extends BasePage {
	
	private static final Logger log = LoggerFactory.getLogger(TeamListPage.class);
	
	@Inject
	private JpaTeamRepository teamRepo;
	
	/**
	 * @param params
	 */
	public TeamListPage(PageParameters params) {
		super(params);
		
		final WicketSession wSession = (WicketSession) getSession();
		if (!wSession.isAuthenticated()) {
			throw new NotLoggedInException("Mohon untuk Login dahulu.");
		}
		
		final IModel<Person> loggedInPersonModel = new LoggedInPersonModel(wSession);
		
		add(new Label("loggedInPersonName", new PropertyModel<>(loggedInPersonModel, "name")));
		
		add(new AjaxLink<RegisterTeamPage>("linkRegisterTeam") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				getRequestCycle().setResponsePage(RegisterTeamPage.class);
			}
		});
		
		add(new LogoutLink("linkLogout"));
		
		add(new TeamListForm("form", loggedInPersonModel));
		
	}
	
	private class TeamListForm extends Form<Void> {
		
		public TeamListForm(final String id, IModel<Person> loggedInPersonModel) {
			super(id);
			
			final IModel<List<Team>> teamsModel = new LoadableDetachableModel<List<Team>>() {
				@Override
				protected List<Team> load() {
					return teamRepo.findAll();
				}
			};
			
			
			final WebMarkupContainer wmcTeams = new WebMarkupContainer("wmcTeams");
			wmcTeams.setOutputMarkupId(true);
			wmcTeams.add( new ListView<Team>("teams", teamsModel) {
				@Override
				protected void populateItem(ListItem<Team> item) {
					item.add(new Label("teamId", new PropertyModel<>(item.getModel(), "id")));
					item.add(new Label("teamName", new PropertyModel<>(item.getModel(), "name")));
					item.add(new AjaxButton("remove", TeamListForm.this) {
						protected void onSubmit(AjaxRequestTarget target, org.apache.wicket.markup.html.form.Form<?> form) {
							teamRepo.delete(item.getModelObject().getId());
							teamsModel.detach();
							target.add(wmcTeams);
						};
					});
					item.add(new AjaxLink<RegisterTeamPage>("linkRegisterTeam") {
						@Override
						public void onClick(AjaxRequestTarget target) {
							getRequestCycle().setResponsePage(
									RegisterTeamPage.class,
									new PageParameters().add("teamId", item.getModelObject().getId()));
						}
					});
				}
			} );
			
			add(wmcTeams);
		}
		
	}//end of main form

}
