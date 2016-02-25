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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

public class WhosWhoDetail
extends Activity
{

	ProgressDialog PD;
	String  imagePath;
	ImageView image;
	TextView row1;
	TextView row2;
	TextView row3;
	TextView row4;
	TextView row5;
	ImageLoader imageLoader;
	protected void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.whoswho_detail_layout);
		this.image = ((ImageView)findViewById(R.id.ivWhosWho));
		this.row1 = ((TextView)findViewById(R.id.tvWhosWhoName));
		this.row2 = ((TextView)findViewById(R.id.tvWhosWhoDesig));
		this.row3 = ((TextView)findViewById(R.id.tvWhosWhoCompany));
		this.row4 = ((TextView)findViewById(R.id.tvWhosWhoSegment));
		this.row5 = ((TextView)findViewById(R.id.tvWhosWhoDescrip));

		new HttpAsyncTask().execute(getIntent().getExtras().getString("id"));



		this.row1.setText(getIntent().getExtras().getString("name"));
		imageLoader = new ImageLoader(getApplicationContext());

		
	}


	
	

	private class HttpAsyncTask extends AsyncTask<String, Void, String>{

		JSONArray jsonArray;
		int lengthjsonArr;

		String s1,s2,s3,s4,s5;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			PD = new ProgressDialog(WhosWhoDetail.this);
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
				String link = "http://www.denimclubindia.com/mapp/tables/whoswhodetail.php?id="+id;
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


				s1 = (jsonArray.getJSONObject(0).getString("wDesig"));
				s2= (jsonArray.getJSONObject(0).getString("wComp"));
				s3 = (jsonArray.getJSONObject(0).getString("vc_segment"));
				s4 =(jsonArray.getJSONObject(0).getString("wBioProf"));
				s5 = ""+jsonArray.getJSONObject(0).getString("wImgFile");



				
				
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
			row2.setText(Html.fromHtml(s1));
			
			row3.setText(Html.fromHtml(s2));
		
			row4.setText(Html.fromHtml(s3));
//			s4 = s4.replaceAll("<p>", "");
//			s4 = s4.replaceAll("</p>", "");
//			s4 = s4.replaceAll("&#38;", "&");
//			
//			
//			
//			s4 = s4.replaceAll("&ldquo;", "\"");
//			s4 = s4.replaceAll("&rdquo;", "\"");
//			s4 = s4.replaceAll("&lsquo;", "\'");
//			s4 = s4.replaceAll("&rsquo;", "\'");
			
			row5.setText(Html.fromHtml(s4));
			imagePath = "http://www.denimclubindia.com"+s5;

			imageLoader.DisplayImage(imagePath, image);
			
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
//					} catch (Exception ex) {
//
//					}
//				}
//				
//					};
//			asyimage.start();		
			
			
			PD.dismiss();
			
		}


	}
}

/* Location:              E:\DCIapp\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\com\deepak\dciapp\WhosWhoDetail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */