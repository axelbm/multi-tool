package model;
 
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Language {
	public static String defaultLanguage = "fr";
	private static Map<String, Language> languages = new HashMap<String, Language>();
	private final static File langDir = new File("languages");
	private final static JSONParser parser = new JSONParser();
	
	
	public static void loadLanguages() throws FileNotFoundException {
		for(File f : langDir.listFiles()) {
			if(!f.isDirectory()) {
				new Language(f);
			}
		}
	}
	
	public static List<String> getLanguagesList() {
		List<String> langList = new ArrayList<String>();

		languages.forEach((String key, Language lang) -> {
			langList.add(lang.getName());
		});
		
		return langList;
	}
	
	public static Map<String, Language> getLanguages() {
		return languages;
	}
	
	public static Language getLanguage(String id) {
		Language lang = languages.get(id);
		
		if (lang == null) {
			System.out.println("[Warning] - the language file " + id + ".json don't exists.");
		}

		return lang;
	}
	
	public static boolean languageExists(String langid) {
		return languages.get(langid) != null;
	}
	
	public static Language getLanguage() {
		String optLang = Option.getLanguage();
		
		if (languageExists(optLang)) {
			return getLanguage(optLang);
		} else {
			return getLanguage(defaultLanguage);
		}
	}
	
	public static Dictionary dictionary(String dictid) {
		return getLanguage().getDictionary(dictid);
	}
	
//	-------------------------
	
	private String name, id;
	private Map<String, Dictionary> dictionaries = new HashMap<String, Dictionary>();
	
	@SuppressWarnings("unchecked")
	private Language(File file) {
		
		try {

			Object obj = parser.parse(new FileReader(file));
			
			JSONObject langJson = (JSONObject) obj;
			
			JSONObject dicts = (JSONObject) langJson.get("Dictionaries");

			dicts.forEach((key, value)->{
				dictionaries.put((String) key, new Dictionary((String) key, this, (Map<String, String>) value));
			});

			
			name = (String) langJson.get("LanguageName");
			id = file.getName().substring(0, 2);
			languages.put(id, this);
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	public String getName() {
		return name;
	}
	
	public String getID() {
		return id;
	}
	
	public boolean dictionaryExists(String dictName) {
		return dictionaries.get(dictName) != null;
	}

	public Dictionary getDictionary(String dictName) {
		Dictionary dict = dictionaries.get(dictName);
		
		if (dict == null) {
			System.out.println("[Warning] - the dictionary \"" + dictName + "\" don't exists.");
			
			Dictionary newdict = new Dictionary(dictName, this, new HashMap<String, String>());
			dictionaries.put(dictName, newdict);
			
			return getDictionary(dictName);
		}
		
		return dict;
	}
	
	public class Dictionary {
		private String name;
		private Map<String, String> dictionary;
		private Language language;
		
		
		public Dictionary(String n, Language lang, Map<String, String> dict) {
			name = n;
			dictionary = dict;
			language = lang;
		}
		
		public String get(String wordid) {
			String word = (String) dictionary.get(wordid);
			
			if(word == null) {
				String wordpath = language.getID().toUpperCase() + "." + name + "." + wordid;
				System.out.println("[Warning] - the word \"" + wordid + "\" is missing from dictionary " + language.getID().toUpperCase() + "." + name + ".");
				return wordpath;
			} else {
				return word;
			}
		}
		
		public Language getLanguage() {
			return language;
		}
		
		public String format(String wordid, Object... args) {
			return String.format(get(wordid), args);
		}
	}
}
