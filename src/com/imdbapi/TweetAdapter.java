package com.imdbapi;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class TweetAdapter extends SimpleAdapter {
	private List<? extends Map<String, String>> tweets;
	
	private int[] colors = new int[] { 0x30ffffff, 0x30ff2020, 0x30808080 };

	@SuppressWarnings("unchecked")
	public TweetAdapter(Context context, List<? extends Map<String, String>> tweets, 
	        int resource, 
	        String[] from, 
	        int[] to) {
	  super(context, tweets, resource, from, to);
	  this.tweets = (List<? extends Map<String, String>>) tweets;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	  View view = super.getView(position, convertView, parent);

	  int colorPos = position % colors.length;
	  view.setBackgroundColor(colors[colorPos]);
	  return view;
	}

}
