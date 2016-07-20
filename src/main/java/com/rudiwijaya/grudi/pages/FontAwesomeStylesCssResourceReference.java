package com.rudiwijaya.grudi.pages;

import com.rudiwijaya.grudi.AfterBootstrapStylesCssResourceReference;

/**
 * A simple stylesheet to fix some styles for the demo page.
 *
 * @author miha
 * @version 1.0
 */
public class FontAwesomeStylesCssResourceReference extends AfterBootstrapStylesCssResourceReference {

	private static final long serialVersionUID = 1L;
	
    /**
     * Construct.
     */
    public FontAwesomeStylesCssResourceReference() {
        super(FontAwesomeStylesCssResourceReference.class, "font-awesome-4.5.0/css/font-awesome.min.css");
    }
    
}
