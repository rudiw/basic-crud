package com.rudiwijaya.grudi;

import java.util.List;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.wicket.Session;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.rudiwijaya.grudi.pages.DedicatedLoginPage;
import com.rudiwijaya.grudi.security.NotLoggedInException;

/**
 * TODO: Use {@link UnauthenticatedException}
 * 
 * @author clutax
 *
 */
public class NotLoggedInRequestCycleListener extends AbstractRequestCycleListener {
	
	private static final Logger log = LoggerFactory.getLogger(NotLoggedInRequestCycleListener.class);

	@Override
	public IRequestHandler onException(RequestCycle cycle, Exception ex) {
		// Since our actual exception is wrapped, we need to find based on the chain
		final List<Throwable> chain = Throwables.getCausalChain(ex);
		final NotLoggedInException notLoggedInException = Iterables.getFirst(
				Iterables.filter(chain, NotLoggedInException.class), null);
		if (notLoggedInException != null) {
			final Url url = cycle.getRequest().getClientUrl();
			log.debug("Got exception " + notLoggedInException.getClass().getName() + " on " + url, ex);
			final WicketSession wSession = (WicketSession) Session.get();
			wSession.setOriginalUrl(url);
			wSession.info("Mohon untuk masuk.");
			wSession.dirty();
			return new RenderPageRequestHandler(new PageProvider(DedicatedLoginPage.class));
		} else {
			return super.onException(cycle, ex);
		}
	}

}
