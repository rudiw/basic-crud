package com.rudiwijaya.grudi;

import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;

/**
 * A simple stylesheet to fix some styles for the demo page.
 *
 * @author miha
 * @version 1.0
 */
public class AfterBootstrapStylesCssResourceReference extends CssResourceReference {

	private static final long serialVersionUID = 1L;
	
    /**
     * Construct.
     */
    public AfterBootstrapStylesCssResourceReference(Class<?> scope, String name) {
        super(scope, name);
    }
    
	@Override
	public List<HeaderItem> getDependencies() {
		final List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(CssHeaderItem.forReference(BootstrapCssReference.instance()));
        
		return dependencies;
	}
    
}
