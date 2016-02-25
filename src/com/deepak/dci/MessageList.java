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
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MessageList extends Activity{
	
	ListView list;
	MessageListAdapter adapter;
	ArrayList<String> id,name,comp,briefmsg,imageurl,desig;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listlayout);
		list = (ListView)findViewById(R.id.listview);
		TextView intro = (TextView)findViewById(R.id.tvlistlayoutintro);
		intro.setText( Html.fromHtml("<h1>Blessings & Support from the Leaders</h1>"+
		"<p>We are thankful and indebted to the pioneers and thought-leaders for blessing us, supporting us and encouraging us all the way!</p>"));
		
		
		
		populate();
		
		
	}
	
	private void populate() {
		// TODO Auto-generated method stub

		new HttpAsyncTask().execute();
	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String>{
		ProgressDialog PD;
		JSONArray jsonArray;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			PD = new ProgressDialog(MessageList.this);
			PD.setTitle("Please wait..");
			PD.setMessage("Loading..");
			PD.setCancelable(false);
			PD.show();
			

		}

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub

			
			try{
				String link = "http://denimclubindia.com/mapp/tables/messages.php";
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

				name= new ArrayList<String>();
				comp = new ArrayList<String>();
				briefmsg  = new ArrayList<String>();
				imageurl   = new ArrayList<String>();
				id = new ArrayList<String>();
				int lengthjsonArr = jsonArray.length();
				desig = new ArrayList<String>();
				
				for(int i=0;i<lengthjsonArr;i++){
					id.add(jsonArray.getJSONObject(i).getString("id"));
					name.add(jsonArray.getJSONObject(i).getString("name"));
					comp.add(jsonArray.getJSONObject(i).getString("comp"));
					briefmsg.add(jsonArray.getJSONObject(i).getString("briefmsg"));
					imageurl.add(jsonArray.getJSONObject(i).getString("imageurl"));
					desig.add(jsonArray.getJSONObject(i).getString("desig"));
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
			
			PD.dismiss();
			adapter = new MessageListAdapter(MessageList.this, imageurl.toArray(new String[imageurl.size()]), name.toArray(new String[imageurl.size()]), briefmsg.toArray(new String[imageurl.size()]),desig.toArray(new String[imageurl.size()]));
			list.setAdapter(adapter);
		

			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					Intent i = new Intent(MessageList.this,Message.class);
					i.putExtra("id", MessageList.this.id.get(position));
					i.putExtra("name", MessageList.this.name.get(position));
					i.putExtra("comp", MessageList.this.comp.get(position));
					i.putExtra("imageurl", MessageList.this.imageurl.get(position));
					
					startActivity(i);
					
				}
			});

					
		}

		



		}
	
}
