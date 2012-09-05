package com.imdbapi;

import java.util.HashMap;

public class Tweet extends HashMap<String, String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String text;
	public String id;
	public static String KEY_TEXT = "text";
	public static String KEY_ID = "id";
	
	public Tweet(String text, String id){
		this.text = text;
		this.id = id;
	}
	
	
	@Override
	public String get(Object k){
	  String key = (String) k;
	  if (KEY_TEXT.equals(key))
	    return text;
	  else if (KEY_ID.equals(key))
	    return id;
	  return null;
	}
}
