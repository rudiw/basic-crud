package com.rudiwijaya.grudi;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import javax.annotation.Nullable;
import javax.measure.unit.Unit;
import javax.measure.unit.UnitFormat;

import com.google.common.base.Optional;

/**
 * Helper methods for {@link Unit}.
 * @author ceefour
 * @todo Use {@link UnitFormat}.
 */
public class Measures {

	public static String format(@Nullable BigDecimal number, @Nullable Unit<?> unit, @Nullable Locale locale) {
		if (number == null) {
			return "";
		}
		final NumberFormat format = NumberFormat.getNumberInstance(Optional.fromNullable(locale).or(Locale.getDefault()));
		String str = format.format(number.doubleValue());
		if (unit != null) {
			if (unit == Unit.ONE) {
				str += number.compareTo(BigDecimal.ONE) == 0 ? " pc" : " pcs";
			} else {
				str += " " + unit;
			}
		}
		return str;
	}

	/**
	 * Format measure number as HTML following Bootstrap conventions (<small> and class="text-muted").
	 * @param number
	 * @param unit
	 * @param locale
	 * @return
	 */
	public static String formatHtml(@Nullable BigDecimal number, @Nullable Unit<?> unit, @Nullable Locale locale) {
		if (number == null) {
			return "";
		}
		final NumberFormat format = NumberFormat.getNumberInstance(Optional.fromNullable(locale).or(Locale.getDefault()));
		String str = format.format(number.doubleValue());
		if (unit != null) {
			if (unit == Unit.ONE) {
				str += number.compareTo(BigDecimal.ONE) == 0 ? " <small class=\"text-muted\">pc</small>" : " <small class=\"text-muted\">pcs</small>";
			} else {
				str += " <small class=\"text-muted\">" + unit + "</small>";
			}
		}
		return str;
	}

}
