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
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Message
  extends Activity
{
  String Designation;
  String MessageString;
  String Name;
  String image;
  String id;
  ListView list;
  ImageView iv;
  TextView tvname,tvdesig,tvmessage;
  ImageLoader imageLoader;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.messageunit);
    imageLoader = new ImageLoader(Message.this);
    
    Name = getIntent().getExtras().getString("name");
    Designation = getIntent().getExtras().getString("comp");
    image = getIntent().getExtras().getString("imageurl");
    id = getIntent().getExtras().getString("id");
    
    tvname = (TextView)findViewById(R.id.tvLeadername);
    tvdesig = (TextView)findViewById(R.id.tvLeaderDesig);
    tvmessage = (TextView)findViewById(R.id.tvLeaderMessage);
    iv = (ImageView)findViewById(R.id.IVmessagePic);
    
    tvname.setText(Html.fromHtml(Name));
    tvdesig.setText(Html.fromHtml(Designation));
    
    imageLoader.DisplayImage(image, iv);
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

			PD = new ProgressDialog(Message.this);
			PD.setTitle("Please wait..");
			PD.setMessage("Loading..");
			PD.setCancelable(false);
			PD.show();

		}

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub

			
			try{
				String link = "http://denimclubindia.com/mapp/tables/messagefull.php?id="+id;
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

				MessageString =jsonArray.getJSONObject(0).getString("fullmsg");
					

				

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
		
			tvmessage.setText(Html.fromHtml(MessageString));
		}


	}
  
}


/* Location:              E:\DCIapp\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\com\deepak\dciapp\Message.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */