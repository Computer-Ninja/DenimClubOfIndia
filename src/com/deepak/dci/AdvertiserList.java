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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AdvertiserList
extends Activity implements OnClickListener
{
	AdvertiserListBaseAdapter arrayAdapter;
	String[] advertiserString = { "ABCDSSAD", "ABCDSSAD", "ABCDSSAD", "ABCDSSAD" };
	String[] advertiserImage = {""};
	String[] id={""};
	ListView l;
	int page = 1;
	Button nextButton,prevButton;
	TextView pagenoTextView;

	ArrayList<String> Advertiser,advId,advImage;

	



	protected void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.advertiserlistlayout);
		getActionBar().setDisplayHomeAsUpEnabled(true);


		l = ((ListView)findViewById(R.id.lvadvertiserlist));
		nextButton = (Button)findViewById(R.id.badvertiserlistnext);
		prevButton = (Button)findViewById(R.id.badvertiserlistprev);
		pagenoTextView = (TextView)findViewById(R.id.tvadvertiserlistpage);

		
		populate();

		nextButton.setOnClickListener(this);
		prevButton.setOnClickListener(this);



		l.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent i = new Intent(AdvertiserList.this, Advertiser.class);
				i.putExtra("id", advId.get(position)+"");
				
				startActivity(i);
				
			}
		});
		





	}


	private void populate() {
		// TODO Auto-generated method stub
		
		
		new HttpAsyncTask().execute();
	}


	private class HttpAsyncTask extends AsyncTask<String, Void, String>{
		ProgressDialog PD;
		JSONArray jsonArray;
		int lengthjsonArr;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			PD = new ProgressDialog(AdvertiserList.this);
			PD.setTitle("Please wait..");
			PD.setMessage("Loading..");
			PD.setCancelable(false);
			PD.show();

		}

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub


			try{
				String link = "http://www.denimclubindia.com/mapp/tables/advertiserslist.php?category=Advertiser&page="+page;
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
				advImage = new ArrayList<String>();
				Advertiser = new ArrayList<String>();
				advId = new ArrayList<String>();
				lengthjsonArr = jsonArray.length();
				for(int i=0;i<lengthjsonArr;i++){
					Advertiser.add(jsonArray.getJSONObject(i).getString("compname"));
					advId.add(jsonArray.getJSONObject(i).getString("compid"));
					advImage.add(jsonArray.getJSONObject(i).getString("logoimage"));
				}

				return null;
			}catch(Exception e){
				return e.getMessage();
			}
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
//			super.onPostExecute(result);
			
			advertiserString = Advertiser.toArray(new String[Advertiser.size()]);
			advertiserImage =    advImage.toArray(new String[advImage.size()]);
			int blen = advertiserString.length;
			for(int i=0;i<blen;i++){
//				advertiserString[i] = advertiserString[i].replaceAll("&#38;", "&");
				advertiserString[i] = ""+String.valueOf(Html.fromHtml(advertiserString[i]));
			}

			PD.dismiss();
			
			arrayAdapter = new AdvertiserListBaseAdapter(AdvertiserList.this, advertiserString, advertiserImage);
			l.setAdapter(arrayAdapter);
			
//			for(int i=0;i<advertiserImage.length;i++){
//				
//				
//				Toast.makeText(getApplicationContext() ,advertiserImage[i], Toast.LENGTH_LONG).show();;
//				}
			
			if(page==1){
				prevButton.setEnabled(false);
			}else{
				prevButton.setEnabled(true);
			}
			if(advertiserString.length<4){
				nextButton.setEnabled(false);
			}else{
				nextButton.setEnabled(true);
			}

		}


	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if(v.getId()==R.id.badvertiserlistnext){
			page++;
			pagenoTextView.setText(""+page);
			populate();
		}else if(v.getId()==R.id.badvertiserlistprev){
			page--;
			pagenoTextView.setText(""+page);
			populate();
		}

	}




}


