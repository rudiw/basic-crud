package com.rudiwijaya.grudi;

import java.util.Locale;

import javax.annotation.Nullable;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rudi
 *
 */
public class WicketSession extends AuthenticatedWebSession {
	
	private static final Logger log = LoggerFactory.getLogger(WicketSession.class);

	private static final long serialVersionUID = 1L;
	
	private boolean authenticated = false;
	@Nullable private String loggedInUserId;
	private Url originalUrl;

	/**
	 * @param request
	 */
	public WicketSession(Request request) {
		super(request);
		
		setLocale(Locale.forLanguageTag("id-ID"));
		
		if (getClientInfo().getProperties().getTimeZone() == null) {
			getClientInfo().getProperties().setTimeZone(AppUtils.getDefaultDateTimeZone().toTimeZone());
		}
		
		log.info("{} --> Wicket Session - Time Zone is '{}'", AppUtils.APP_NAME, getClientInfo().getProperties().getTimeZone());
	}

	@Override
	public boolean authenticate(String username, String password) {
//		throw new UnsupportedOperationException("You are supposed to use Spring-Security!!");
		//FIXME: workaround for missed SecurityUtil.getSubject
		this.loggedInUserId = username;
		this.authenticated = true;
		return true;
	}

	@Override
	public Roles getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the authenticated
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}

	/**
	 * @return the loggedInUserId
	 */
	@Nullable public String getLoggedInUserId() {
		return loggedInUserId;
	}
	
	public Url getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(Url originalUrl) {
		this.originalUrl = originalUrl;
	}

	@Override
	public void invalidate() {
		//FIXME: workaround for missed SecurityUtil.getSubject
		this.loggedInUserId = null;
		this.authenticated = false;
		super.invalidate();
	}

}
