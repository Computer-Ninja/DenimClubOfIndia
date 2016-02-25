package com.deepak.dci;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;



import extras.TabPageAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WhosWho  extends FragmentActivity implements  android.app.ActionBar.TabListener{

	
	private ViewPager viewPager;
	private android.app.ActionBar actionBar;
	private TabPageAdapter tabPageAdapter;
	private String[] Tabs;
	int totalNoOfPage = 1;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.viewpagerlayout);
		viewPager = (ViewPager)findViewById(R.id.vpPager);
		actionBar = getActionBar();
		
		new HttpAsyncTask().execute("");
		
		
		
	}
	
	
	@Override
	public void onTabSelected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) {
		 viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String>{
		ProgressDialog PD;
		JSONArray jsonArray;
		int lengthjsonArr;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
//			PD = (LinearLayout)view.findViewById(R.id.linlaHeaderProgress);
//			PD.setVisibility(View.VISIBLE);
//			PD = new ProgressDialog(getApplicationContext());
//			PD.setTitle("Please wait..");
//			PD.setMessage("Loading..");
//			PD.setCancelable(false);
//			PD.show();

		}

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub

			
			try{
				String link = "http://www.denimclubindia.com/mapp/tables/whoswhopagecounter.php";
				URL url = new URL(link);
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet();
				request.setURI(new URI(link));
				HttpResponse response = client.execute(request);
				BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer sb = new StringBuffer();
				String line = "";

				while((line=in.readLine())!=null){
					sb.append(line);
					break;
				}
				in.close();

				jsonArray  = new JSONArray( sb.toString());

				totalNoOfPage = jsonArray.getJSONObject(0).getInt("total");
				return null;
			}catch(Exception e){
				return e.getMessage();
			}
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

//			PD.dismiss();
			
			enable();

		}

		

	}
	
	private void enable() {
		// TODO Auto-generated method stub
		 
		tabPageAdapter = new TabPageAdapter(getSupportFragmentManager(),totalNoOfPage);
		viewPager.setAdapter(tabPageAdapter);
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		for(int i=1;i<=totalNoOfPage;i++){
			actionBar.addTab(actionBar.newTab().setText("Page "+i).setTabListener(this));
		}
		
		
		
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				actionBar.setSelectedNavigationItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		
		
	}
	
}
