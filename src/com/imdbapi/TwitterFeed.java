package com.imdbapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class TwitterFeed extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setContentView(R.layout.main);
        try {
        	HttpClient client= new DefaultHttpClient();
        	HttpGet httpGet = new HttpGet("http://twitter.com/statuses/user_timeline/DanielZlot.json");
        	
			HttpResponse response = client.execute(httpGet);
			InputStream content = response.getEntity().getContent();
			String readTwitterFeed = this.convertStreamToString(content);
			JSONArray jsonArray = new JSONArray(readTwitterFeed);
			List<? extends Map<String, String>> movies = this.getData(jsonArray);
			ListAdapter adapter = new SimpleAdapter(getApplicationContext(), movies, android.R.layout.simple_list_item_2, new String[] {
		         Tweet.KEY_TEXT, Tweet.KEY_ID }, new int[] {
		          android.R.id.text1, android.R.id.text2 });
			this.setListAdapter(adapter);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private List<? extends Map<String, String>> getData(JSONArray jsonArray) {
    	List<Tweet> auxT= new ArrayList<Tweet>();
    	for (int i = 0; i < jsonArray.length(); i++) {
    		try {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
    	        String text = jsonObject.getString("text");
                String id = jsonObject.getString("id");
                auxT.add(new Tweet(text, id));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
    		
    	}
    	return (List<? extends Map<String, String>>)auxT;
    }
	
	private String convertStreamToString(InputStream content) {
		// TODO Auto-generated method stub
		try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(content,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
            	sb.append(line + "\n");
            }
            content.close();
            return(sb.toString());
        }catch(Exception e){
        	//Log.e("log_tag", "Error converting result "+e.toString());
        	return null;
        }
		
	}

}
