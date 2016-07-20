package com.rudiwijaya.grudi;

import java.text.DecimalFormat;

/**
 * @author rudi
 *
 */
public class FileUtils {

	/**
	 * Source: https://stackoverflow.com/questions/3263892/format-file-size-as-mb-gb-etc
	 * 
	 * @param size in Bytes
	 * @return
	 */
	public static String readableFileSize(long size) {
	    if(size <= 0) return "0";
	    final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
	    return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}

}
