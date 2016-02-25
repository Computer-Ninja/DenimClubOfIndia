package com.deepak.dci;

import java.io.InputStream;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class WhosWhoListAdapter extends ArrayAdapter<String>{

	Activity context;
	String[] text,images;
	ImageLoader imageLoader;
	
	public WhosWhoListAdapter(Activity context,String[] text,String[] images) {
		super(context, R.layout.grid_item_layout,text);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.text = text;
		this.images = images;
		imageLoader = new ImageLoader(context.getApplicationContext());
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		TextView tv = null;
		 
		
			convertView = inflator.inflate(R.layout.grid_item_layout, null);
			tv = (TextView) convertView.findViewById(R.id.tvGridTextItem);
			final ImageView	iv = (ImageView) convertView.findViewById(R.id.ivGridImageItem);
			
		
		tv.setText(text[position]);
		
		final String imagePath = images[position];

		imageLoader.DisplayImage(imagePath, iv);
		
//		Thread asyimage = new Thread() {
//
//			@Override
//			public void run()
//			{
//				// TODO Auto-generated method stub
//				try {
//					//					        
//					//											URL url = new URL("http://www.denimclubindia.com/images/who_is_who/Arun_Jaitley_sm.jpg");
//					URL url = new URL(imagePath);
//					//try this url = "http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg"
//					HttpGet httpRequest = null;
//
//					httpRequest = new HttpGet(url.toURI());
//
//					HttpClient httpclient = new DefaultHttpClient();
//					HttpResponse response = (HttpResponse) httpclient
//							.execute(httpRequest);
//
//					HttpEntity entity = response.getEntity();
//					BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
//					InputStream input = b_entity.getContent();
//
//					Bitmap bitmap = BitmapFactory.decodeStream(input);
//
//					iv.setImageBitmap(bitmap);
//
//				} catch (Exception ex) {
//
//				}
//			}
//			
//				};
//		asyimage.start();	
		
		
		return convertView;
	}
	
}