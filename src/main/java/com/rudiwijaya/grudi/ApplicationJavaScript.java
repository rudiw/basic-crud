package com.rudiwijaya.grudi;

import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.core.Bootstrap;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ApplicationJavaScript extends JavaScriptResourceReference {

	private static final long serialVersionUID = 1L;
	
	public static final ApplicationJavaScript INSTANCE = new ApplicationJavaScript();

    private ApplicationJavaScript() {
        super(ApplicationJavaScript.class, "application.js");
    }
    
    @Override
    public List<HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));
        dependencies.add(JavaScriptHeaderItem.forReference(Bootstrap.getSettings().getJsResourceReference()));

        return dependencies;
    }
}
