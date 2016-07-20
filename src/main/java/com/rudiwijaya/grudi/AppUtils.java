package com.rudiwijaya.grudi;

import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.joda.time.DateTimeZone;

/**
 * @author clutax
 *
 */
public class AppUtils {
	
	public static final String APP_NAME = "grudi";
	
	public static final String APP_TITLE = "Garena Rudi";
	
	public static final String APP_URI = "garena.rudiwijaya.com";

	public static DateTimeZone getDefaultDateTimeZone() {
		return DateTimeZone.forID("Asia/Jakarta");
	}
	
	public static Locale getDefaultLocale() {
		return Locale.forLanguageTag("id-ID");
	}
	
	public static CurrencyUnit getDefaultCurrency() {
		return Monetary.getCurrency("IDR");
	}


}
