package com.deepak.dci;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Business extends Activity {

	TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
	ImageView image;
	ImageLoader imageLoader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advertiser_profile_layout);
		
		t1 = (TextView)findViewById(R.id.tvadvertiserCompname);
		t2 = (TextView)findViewById(R.id.tvadvertiserCompSegment);
		t3 = (TextView)findViewById(R.id.tvadvertiserCompWebsite);
		t4 = (TextView)findViewById(R.id.tvadvertiserCompRefName);
		t5 = (TextView)findViewById(R.id.tvadvertiserCompRefDesig);
		t6 = (TextView)findViewById(R.id.tvadvertiserCompRefAddress);
		t7 = (TextView)findViewById(R.id.tvadvertiserCompRefContactNo);
		t8 = (TextView)findViewById(R.id.tvadvertiserCompRefEmailId);
		t9 = (TextView)findViewById(R.id.tvadvertiserCompProfile);
		image = (ImageView)findViewById(R.id.ivadvertiserCompImage);
		
		new HttpAsyncTask().execute(getIntent().getExtras().get("id").toString()+"");
		
//		Toast.makeText(getApplicationContext(),getIntent().getExtras().get("id").toString() , Toast.LENGTH_SHORT).show();
		
		imageLoader = new ImageLoader(getApplicationContext());
		t3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
				startActivity(browserIntent);
			}
		});
		
	}
	
	private class HttpAsyncTask extends AsyncTask<String, Void, String>{
		ProgressDialog PD;
		
		JSONArray jsonArray;
		int lengthjsonArr;

		String s1,s2,s3,s4,s5,s6,s7,s8,s9,imageurl;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			PD = new ProgressDialog(Business.this);
			PD.setTitle("Please wait..");
			PD.setMessage("Loading..");
			PD.setCancelable(false);
			PD.show();

		}

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub

			String id = (String)urls[0];
			try{
				String link = "http://www.denimclubindia.com/mapp/tables/businessdetail.php?id="+id;
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

				lengthjsonArr = jsonArray.length();


				s1 = (jsonArray.getJSONObject(0).getString("compname"));
				s2= (jsonArray.getJSONObject(0).getString("busType"));
				s3 = (jsonArray.getJSONObject(0).getString("website"));
				s4 = (jsonArray.getJSONObject(1).getString("name"));
				s5 = (jsonArray.getJSONObject(1).getString("desig"));
				s6 = (jsonArray.getJSONObject(1).getString("address"));
				s7 = (jsonArray.getJSONObject(1).getString("phone"));
				s8 = (jsonArray.getJSONObject(1).getString("email"));
				s9 = (jsonArray.getJSONObject(0).getString("compProfile"));
				imageurl =(jsonArray.getJSONObject(0).getString("image"));


				
				
				return null;
			}catch(Exception e){
				return e.getMessage();
			}
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			
//			s1 = s1.replaceAll("&#38;", "&");
			t1.setText(Html.fromHtml(s1));
			
			t2.setText(Html.fromHtml(s2));
		
			t3.setText(Html.fromHtml(s3));
			t4.setText(Html.fromHtml(s4));
			t5.setText(Html.fromHtml(s5));
			t6.setText(Html.fromHtml(s6));
			t7.setText(Html.fromHtml("<strong>Contact no. : </strong>"+s7));
			t8.setText(Html.fromHtml("<strong>Email id : </strong>"+s8));
			t9.setText(Html.fromHtml(s9));
			imageurl = (Html.fromHtml(imageurl)).toString();
			t3.setTextColor(Color.BLUE);
			t3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Html.fromHtml(s3)+""));
					startActivity(browserIntent);
				}
			});
			
			final String imagePath = imageurl;

			imageLoader.DisplayImage(imageurl, image);
			
			
//			Thread asyimage = new Thread() {
//
//				@Override
//				public void run()
//				{
//					// TODO Auto-generated method stub
//					try {
//						//					        
//						//											URL url = new URL("http://www.denimclubindia.com/images/who_is_who/Arun_Jaitley_sm.jpg");
//						URL url = new URL(imagePath);
//						//try this url = "http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg"
//						HttpGet httpRequest = null;
//
//						httpRequest = new HttpGet(url.toURI());
//
//						HttpClient httpclient = new DefaultHttpClient();
//						HttpResponse response = (HttpResponse) httpclient
//								.execute(httpRequest);
//						
//						HttpEntity entity = response.getEntity();
//						BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
//						InputStream input = b_entity.getContent();
//
//						Bitmap bitmap = BitmapFactory.decodeStream(input);
//
//						image.setImageBitmap(bitmap);
//						
//						Toast.makeText(getApplicationContext(), imagePath, Toast.LENGTH_LONG).show();
//
//					} catch (Exception ex) {
//
//					}
//				}
//				
//				
//			};
//			asyimage.start();		
			
			
			PD.dismiss();
			
		}


	}
	
	
}
