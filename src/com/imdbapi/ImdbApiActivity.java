package com.imdbapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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




import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.imdbapi.MovieListAdapter;
import com.imdbapi.Movie;

public class ImdbApiActivity extends ListActivity {
	private ListView lstView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setContentView(R.layout.main);
        try {
        	HttpClient client= new DefaultHttpClient();
        	HttpGet httpGet= new HttpGet("http://www.deanclatworthy.com/imdb/?q=Matrix&yg=0");
        
			HttpResponse response = client.execute(httpGet);
			InputStream content = response.getEntity().getContent();
			String readTwitterFeed = convertStreamToString(content);
			JSONArray jsonArray = new JSONArray(readTwitterFeed);
			List<? extends Map<String, String>> movies = getData2();
			//List<Movie> movies = this.getData2();
			ListAdapter adapter = new SimpleAdapter(getApplicationContext(), movies, android.R.layout.simple_list_item_2, new String[] {
		         Movie.KEY_NAME, Movie.KEY_YEAR }, new int[] {
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
    
    private List<? extends Map<String, String>> getData(JSONObject obj) {
        List<Movie> auxMovies = new ArrayList<Movie>();
        JSONArray m = null;
        Iterator keys = obj.keys();	    
        Log.v("algo asd", obj.toString());
        try {
            // Getting Array of Contacts
        	while (keys.hasNext()) {
    	        String key = (String) keys.next();
    	        String title = obj.getString("title");
                String year = obj.getString("year");
                auxMovies.add(new Movie(title, year));
    	    }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("algo", auxMovies.toString());
        return (List<? extends Map<String, String>>)auxMovies;
      }
    
    private List<? extends Map<String, String>> getData2() {
        List<Movie> cars = new ArrayList<Movie>();
        cars.add(new Movie("Dodge", "Viper"));
        cars.add(new Movie("Chevrolet", "Corvette"));
        cars.add(new Movie("Aston Martin", "Vanquish"));
        cars.add(new Movie("Lamborghini", "Diablo"));
        cars.add(new Movie("Ford", "Pinto"));
        return (List<? extends Map<String, String>>)cars;
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
	
	private Map<String, String> makeMapJson(JSONObject jsonObject){
		try {
		    Iterator keys = jsonObject.keys();
		    Map<String, String> map = new HashMap<String, String>();
		    while (keys.hasNext()) {
		        String key = (String) keys.next();
		        map.put(key, jsonObject.getString(key));
		    }
		    Log.v("Map", map.toString());;// this map will contain your json stuff
		    return map;
		} catch (JSONException e) {
		    e.printStackTrace();
		}
		return null;
	}
}