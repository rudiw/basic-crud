package com.rudiwijaya.grudi;

import java.util.regex.Pattern;

/**
 * Generates ID, screen name, or URI segment.
 * @author ceefour
 */
public class SlugUtils {
	
	public static final Pattern PERSON_USERNAME = Pattern.compile("^[a-z0-9]{6,15}$");
	
	public static final Pattern PERSON_PASSWORD = Pattern.compile("^.{8,16}$");
	
	public static final Pattern TEAM_NAME = Pattern.compile("^[a-z0-9_]{4,15}$");
	
	public static final Pattern PHONE_NUMBER = Pattern.compile("^[0-9]{7,14}$");

}
