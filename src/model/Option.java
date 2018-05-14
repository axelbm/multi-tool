package model;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import vue.MultiTool;

public class Option {
	private static Map<String, String> settings = new HashMap<String, String>();

	private static Font textFont = new Font("Tahoma", Font.PLAIN, 13);
	private static Font buttonFont = new Font("Tahoma", Font.BOLD, 14);
	private static Font titleFont = new Font("Tahoma", Font.BOLD, 20);
	private static Font vanityFont = new Font("Trebuchet MS", Font.BOLD, 30);
	
	public static Font font(String font) {
		switch (font) {
		case "text":
			return textFont;
		case "button":
			return buttonFont;
		case "title":
			return titleFont;
		case "vanity":
			return vanityFont;
		default:
			return textFont;
		}
	}
	
	public static void load() {
		
	}
	
	public static String getLanguage() {
		String lang = settings.get("language");

		if (lang == null) {
			lang = Language.defaultLanguage;
		}
		
		return lang;
	}
	
	public static void changeLanguage(Language lang) {
		settings.put("language", lang.getID());
		
		
		MultiTool frame = MultiTool.getInstance();
		
		frame.changeLanguage(lang);
	}
}
