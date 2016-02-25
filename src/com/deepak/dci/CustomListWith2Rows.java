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
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListWith2Rows extends ArrayAdapter<String>
{
  Activity context;
  String[] row1;
  String[] row2;
  String[] images;
  ImageLoader imageLoader;
  public CustomListWith2Rows(Activity paramActivity, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    super(paramActivity, R.layout.custom_layout_with_2rows, paramArrayOfString2);
    this.context = paramActivity;
    this.row1 = paramArrayOfString1;
    this.row2 = paramArrayOfString2;
    imageLoader = new ImageLoader(context.getApplicationContext());
  }
  
  public CustomListWith2Rows(Activity paramActivity, String[] paramArrayOfString1, String[] paramArrayOfString2,String[] images)
  {
    super(paramActivity, R.layout.custom_layout_with_2rows, paramArrayOfString1);
    this.context = paramActivity;
    this.row1 = paramArrayOfString1;
    this.row2 = paramArrayOfString2;
    this.images = images;
    imageLoader = new ImageLoader(context.getApplicationContext());
  }
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = this.context.getLayoutInflater().inflate(R.layout.custom_layout_with_2rows, null, true);
    TextView localTextView1 = (TextView)localView.findViewById(R.id.tvCustomLayoutWith2Row1);
    TextView localTextView2 = (TextView)localView.findViewById(R.id.tvCustomLayoutWith2Row2);
    final ImageView imgView = (ImageView)localView.findViewById(R.id.ivCustomLayout);
    localTextView1.setText(this.row1[paramInt]);
    localTextView2.setText(this.row2[paramInt]);
   
    imageLoader.DisplayImage("http://www.denimclubindia.com/images/advert_logo/"+images[paramInt], imgView);
    
    
//    switch(paramInt){
//    case 0:localView.setBackgroundColor(Color.rgb(102, 178, 255));
//    break;
//    case 1:localView.setBackgroundColor(Color.rgb(255, 215, 0));
//    break;
//    case 2:localView.setBackgroundColor(Color.rgb(192, 192, 192));
//    break;
//    case 3:localView.setBackgroundColor(Color.rgb(192, 192, 192));
//    break;
//    }
//    	Thread asyimage = new Thread() {
//    		
//    		@Override
//    		public void run() {
//    			// TODO Auto-generated method stub
//    			try {
//    		        URL url = new URL("http://www.denimclubindia.com/images/advert_logo/"+images[paramInt]);
//    		        
//    		        HttpGet httpRequest = null;
//
//    		        httpRequest = new HttpGet(url.toURI());
//
//    		        HttpClient httpclient = new DefaultHttpClient();
//    		        HttpResponse response = (HttpResponse) httpclient
//    		                .execute(httpRequest);
//
//    		        HttpEntity entity = response.getEntity();
//    		        BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
//    		        InputStream input = b_entity.getContent();
//
//    		        Bitmap bitmap = BitmapFactory.decodeStream(input);
//
//    		        imgView.setImageBitmap(bitmap);
//
//    		    } catch (Exception ex) {
//
//    		    }
//    		}
//    	};
//    	asyimage.start();
    
    return localView;
  }
}


/* Location:              E:\DCIapp\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\com\deepak\dciapp\CustomListWith2Rows.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */