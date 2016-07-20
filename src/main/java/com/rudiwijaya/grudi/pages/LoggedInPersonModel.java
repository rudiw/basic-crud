package com.rudiwijaya.grudi.pages;

import javax.inject.Inject;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;

import com.rudiwijaya.grudi.WicketSession;
import com.rudiwijaya.grudi.dao.JpaPersonRepository;
import com.rudiwijaya.grudi.jpa.Person;

/**
 * @author rudi
 *
 */
public class LoggedInPersonModel extends LoadableDetachableModel<Person> {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private JpaPersonRepository personRepo;
	
	private final String personId;
	
	public LoggedInPersonModel(final WicketSession wSession) {
		super();
		Injector.get().inject(this);
	
		//FIXME: workaround for missed SecurityUtil.getSubject
//		final Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
		//TODO: use from shiro ex!
//			throw new NotLoggedInException("Mohon untuk Sign In terlebih dahulu!");
//		}
		
		if ( wSession.isAuthenticated() ) {
			this.personId = wSession.getLoggedInUserId();
		} else {
			this.personId = null;
		}
	}
	
	@Override
	protected Person load() {
		if (personId != null) {
			return personRepo.findOneByUsername(personId);
		} else {
			return null;
		}
	}

}
