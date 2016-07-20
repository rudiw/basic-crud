package com.rudiwijaya.grudi.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author clutax
 *
 */
@SuppressWarnings("serial")
public class HomePage extends BasePage {
	
	public HomePage(PageParameters params) {
		super(params);
		
		add( new HomeForm("form") );
	}
	
	private class HomeForm extends Form<Void> {
		
		public HomeForm(final String id) {
			super(id);
			add( new BookmarkablePageLink<>("linkRegisterPersonPage", RegisterPersonPage.class) );
			add( new BookmarkablePageLink<>("linkDedicatedLoginPage", DedicatedLoginPage.class) );
		}
		
	}
	
}
