package com.rudiwijaya.grudi.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda.LaddaAjaxButton;

/**
 * @author rudi
 *
 */
@SuppressWarnings("serial")
public class TermAndConditionsModal extends Modal<Void> {

	public TermAndConditionsModal(String markupId) {
		super(markupId);
		
//		addCloseButton();
		
		final Form<Void> form = new Form<>("form");
		
		final LaddaAjaxButton btnClose = new LaddaAjaxButton("btnClose", new Model<>("Close"), Type.Default){
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				appendCloseDialogJavaScript(target);
			}
		};
		form.add(btnClose);
		
		add(form);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		header(new Model<>("SYARAT & KETENTUAN"));
	}

}
