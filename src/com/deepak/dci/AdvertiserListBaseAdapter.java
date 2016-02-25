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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdvertiserListBaseAdapter extends BaseAdapter{

	Activity context;
	String[] CompName,Images;
	
	ImageLoader imageLoader;
	
	public AdvertiserListBaseAdapter(Activity context, String[] CompName,String[] Images){
		this.context = context;
		this.CompName = CompName;
		this.Images = Images;
		  imageLoader = new ImageLoader(context.getApplicationContext());
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return CompName.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
		 View localView = this.context.getLayoutInflater().inflate(R.layout.itemwithimage, null,false);
//			View localView = this.context.getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null,false);
			    TextView tvCompName = (TextView)localView.findViewById(R.id.firstLine);
				final ImageView ivCompLogo = (ImageView)localView.findViewById(R.id.icon);
			
				tvCompName.setText(CompName[position]);
			
//				new Thread(){
//					public void run() {
				
						 imageLoader.DisplayImage("http://www.denimclubindia.com/images/advert_logo/"+Images[position], ivCompLogo);
//					};
//				}.start();
				
				
//			Thread asyimage = new Thread() {
//				
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					try {
////				       
//				       URL url = new URL("http://www.denimclubindia.com/images/advert_logo/"+Images[position]);
//						HttpGet httpRequest = null;
//
//				        httpRequest = new HttpGet(url.toURI());
//
//				        HttpClient httpclient = new DefaultHttpClient();
//				        HttpResponse response = (HttpResponse) httpclient
//				                .execute(httpRequest);
//
//				        HttpEntity entity = response.getEntity();
//				        BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
//				        InputStream input = b_entity.getContent();
//
//				        Bitmap bitmap = BitmapFactory.decodeStream(input);
//
//				        ivCompLogo.setImageBitmap(bitmap);
//
//				    } catch (Exception ex) {
//
//				    }
//				}
//			};
//			asyimage.start();
		
		return localView;
	}

}
