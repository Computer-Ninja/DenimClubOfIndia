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

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

@TargetApi(16)
public class ProfessionalMenu
  extends Activity
{
	ProgressDialog PD;
	ArrayAdapter<String> localArrayAdapter;
	ArrayList<String> BusinessSeg,segId;
	String[] BusinessSegments = { "Fibre and Yarn", "Trim & Accessories", "Display & Packaging", "Brands", "Association", "Design", "Spares & Consumables", "Dyes & Chemicals", "Cutting & Sewing", "Retail", "Testing", "IT/ Technology Solutions", "Institute", "Garments", "Fabric", "Machinery", "Washing & Laundry", "Consultant", "Allied Services", "Buying Houses" };
	
	ListView l;





	protected void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.listlayout);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		this.l = ((ListView)findViewById(R.id.listview));

		TextView intro = (TextView)findViewById(R.id.tvlistlayoutintro);
		intro.setText( Html.fromHtml("<h1>Explore the Denim Business Directory</h1>"+
				"The lists of profesionals in the Denim Business Directory are grouped under the various value chain segments for easier and quicker reference. Please click on any of the segments below to explore the persons listed in that segment."));
		
		new HttpAsyncTask().execute("");
		
		
		l.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
			{
				Intent localIntent = new Intent(getApplicationContext(),ProfessionalList.class);
				localIntent.putExtra("id",segId.get(paramAnonymousInt).toString() );
				startActivity(localIntent);
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu paramMenu)
	{
		return false;
	}

	public boolean onOptionsItemSelected(MenuItem paramMenuItem)
	{
		return false;
	}


	private class HttpAsyncTask extends AsyncTask<String, Void, String>{

		JSONArray jsonArray;
		int lengthjsonArr;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			PD = new ProgressDialog(ProfessionalMenu.this);
			PD.setTitle("Please wait..");
			PD.setMessage("Loading..");
			PD.setCancelable(false);
			PD.show();

		}

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub

			
			try{
				String link = "http://www.denimclubindia.com/mapp/tables/segments.php";
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

				BusinessSeg = new ArrayList<String>();
				segId = new ArrayList<String>();
				lengthjsonArr = jsonArray.length();
				for(int i=0;i<lengthjsonArr;i++){
					BusinessSeg.add(jsonArray.getJSONObject(i).getString("segment"));
					segId.add(jsonArray.getJSONObject(i).getString("id"));

				}

				return null;
			}catch(Exception e){
				return e.getMessage();
			}
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			BusinessSegments = BusinessSeg.toArray(new String[BusinessSeg.size()]);
			
			int blen = BusinessSegments.length;
			for(int i=0;i<blen;i++){
				BusinessSegments[i] = BusinessSegments[i].replaceAll("&#38;", "&");
			}
			
			PD.dismiss();
			localArrayAdapter = new ArrayAdapter<String>(ProfessionalMenu.this,  android.R.layout.simple_list_item_1, BusinessSegments);
			l.setAdapter(localArrayAdapter);
		}


	}
}

