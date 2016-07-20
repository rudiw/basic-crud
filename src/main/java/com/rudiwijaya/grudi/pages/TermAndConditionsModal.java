package com.rudiwijaya.grudi.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;

/**
 * @author rudi
 *
 */
@SuppressWarnings("serial")
public class TermAndConditionsModal extends Modal<Void> {

	public TermAndConditionsModal(String markupId) {
		super(markupId);
		
		addCloseButton();
		
		add(new Form<>("form"));
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		header(new Model<>("SYARAT & KETENTUAN"));
	}

}
