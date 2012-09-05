package com.imdbapi;

import java.util.HashMap;

public class Movie extends HashMap{
	public String year;
	public String name;
	public static String KEY_YEAR = "year";
	public static String KEY_NAME = "name";
	
	public Movie(String name, String year){
		this.year = year;
		this.name = name;
	}
	
	
	@Override
	public String get(Object k){
	  String key = (String) k;
	  if (KEY_YEAR.equals(key))
	    return year;
	  else if (KEY_NAME.equals(key))
	    return name;
	  return null;
	}
}
