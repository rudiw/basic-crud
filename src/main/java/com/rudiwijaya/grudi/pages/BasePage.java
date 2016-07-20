package com.rudiwijaya.grudi.pages;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.FilteredHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rudiwijaya.grudi.AfterBootstrapStylesCssResourceReference;
import com.rudiwijaya.grudi.ApplicationJavaScript;
import com.rudiwijaya.grudi.BootstrapStylesCssResourceReference;
import com.rudiwijaya.grudi.GrowlBehavior;

/**
 * @author rudi
 *
 */
@SuppressWarnings("serial")
public class BasePage extends WebPage {
	
	private static final Logger log = LoggerFactory.getLogger(BasePage.class);
	
	public BasePage(final PageParameters params) {
		super(params);
		
		add(new GrowlBehavior());
		
		add(new HeaderResponseContainer("footer-container", "footer-container"));
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		/*CSSs*/
		
		response.render(CssHeaderItem.forReference(new AfterBootstrapStylesCssResourceReference(BasePage.class, "full-slider/css/full-slider.css")));
		
		response.render(CssHeaderItem.forReference(new FontAwesomeStylesCssResourceReference()));

		response.render(CssHeaderItem.forReference(new BootstrapStylesCssResourceReference()));
		
		/*JAVASCRIPTS*/
		response.render(new FilteredHeaderItem(JavaScriptHeaderItem.forReference(ApplicationJavaScript.INSTANCE), "footer-container"));
		
	}
	
}

