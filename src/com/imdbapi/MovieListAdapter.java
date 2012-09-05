package com.imdbapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import com.imdbapi.Movie;

public class MovieListAdapter extends SimpleAdapter {
	private List<? extends Map<String, String>> movies;
	
	private int[] colors = new int[] { 0x30ffffff, 0x30ff2020, 0x30808080 };

	@SuppressWarnings("unchecked")
	public MovieListAdapter(Context context, List<? extends Map<String, String>> movies, 
	        int resource, 
	        String[] from, 
	        int[] to) {
	  super(context, movies, resource, from, to);
	  this.movies = (List<? extends Map<String, String>>) movies;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	  View view = super.getView(position, convertView, parent);

	  int colorPos = position % colors.length;
	  view.setBackgroundColor(colors[colorPos]);
	  return view;
	}

}
