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

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListWithCustomTiles
extends Activity
{
	String[] Row1 = { "Tom", "Dick", "Harry" };
	String[] Row2 = { "Denim Club India", "Denim Club India", "Denim Club India" };
	String[] Row3 = { "India", "USA", "London" };
	String[] ID ={};
	ListView list;

	ArrayList<String> alId,alCompanyname,alCountry,alBustype;

	protected void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.listlayoutonly);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		populate();

		
		
		
		
	}

	private void populate() {
		// TODO Auto-generated method stub

		new HttpAsyncTask().execute(getIntent().getExtras().getString("id"));
	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String>{
		ProgressDialog PD;
		JSONArray jsonArray;
		int lengthjsonArr;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			PD = new ProgressDialog(ListWithCustomTiles.this);
			PD.setTitle("Please wait..");
			PD.setMessage("Loading..");
			PD.setCancelable(false);
			PD.show();

		}

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub

			String id = urls[0];
			try{
				String link = "http://www.denimclubindia.com/mapp/tables/businesscompany.php?id="+id;
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

				alBustype= new ArrayList<String>();
				alCompanyname = new ArrayList<String>();
				alCountry  = new ArrayList<String>();
				alId   = new ArrayList<String>();

				lengthjsonArr = jsonArray.length();
				for(int i=0;i<lengthjsonArr;i++){
					alCompanyname.add(jsonArray.getJSONObject(i).getString("compname"));
					alCountry.add(jsonArray.getJSONObject(i).getString("country"));
					alBustype.add(jsonArray.getJSONObject(i).getString("busType"));
					alId.add(jsonArray.getJSONObject(i).getString("id"));

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
			Row1 = alCompanyname.toArray(new String[alCompanyname.size()]);
			Row2 = alCountry.toArray(new String[alCountry.size()]);
			Row3 = alBustype.toArray(new String[alBustype.size()]);
			ID = alId.toArray(new String[alId.size()]);
			
			PD.dismiss();
			CustomListAdapterForThreeRowTile localCustomListAdapterForThreeRowTile = new CustomListAdapterForThreeRowTile(ListWithCustomTiles.this, Row1, Row2, Row3);
			list = ((ListView)findViewById(R.id.listviewlayout));
			list.setAdapter(localCustomListAdapterForThreeRowTile);
			
			list.setOnItemClickListener(new AdapterView.OnItemClickListener()
			{
				public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
				{
					Intent i = new Intent(getApplicationContext(),Business.class);
					i.putExtra("compname", Row1[paramAnonymousInt]);
					i.putExtra("bustype", Row3[paramAnonymousInt]);
					i.putExtra("id",ID[paramAnonymousInt] );
					
					startActivity(i);
				}
			});

		}


	}





}


/* Location:              E:\DCIapp\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\com\deepak\dciapp\ListWithCustomTiles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */