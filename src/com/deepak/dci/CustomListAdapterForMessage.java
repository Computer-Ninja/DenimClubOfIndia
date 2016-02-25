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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class CustomListAdapterForMessage
  extends ArrayAdapter<String>
{
  private String[] Designation;
  private String[] MessageString;
  private String[] Name;
  Activity context;
  private String[] image;
  ImageLoader imageLoader;
  
  public CustomListAdapterForMessage(Activity paramActivity, String[] paramArrayOfInt, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3)
  {
    super(paramActivity, R.layout.messageunit, paramArrayOfString1);
    this.context = paramActivity;
    this.image = paramArrayOfInt;
    this.Name = paramArrayOfString1;
    this.Designation = paramArrayOfString2;
    this.MessageString = paramArrayOfString3;
    
    imageLoader = new com.deepak.dci.ImageLoader(this.context.getApplicationContext());
// // UNIVERSAL IMAGE LOADER SETUP
// 		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
// 				.cacheOnDisc(true).cacheInMemory(true)
// 				.imageScaleType(ImageScaleType.EXACTLY)
// 				.displayer(new FadeInBitmapDisplayer(300)).build();
//
// 		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
// 				this.context)
// 				.defaultDisplayImageOptions(defaultOptions)
// 				.memoryCache(new WeakMemoryCache())
// 				.discCacheSize(100 * 1024 * 1024).build();
//
// 		ImageLoader.getInstance().init(config);
// 		// END - UNIVERSAL IMAGE LOADER SETUP
 	
    
  }
  


public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = this.context.getLayoutInflater().inflate(R.layout.messageunit, null, true);
    ImageView localImageView = (ImageView)localView.findViewById(R.id.IVmessagePic);
    TextView localTextView1 = (TextView)localView.findViewById(R.id.tvLeadername);
    TextView localTextView2 = (TextView)localView.findViewById(R.id.tvLeaderDesig);
    TextView localTextView3 = (TextView)localView.findViewById(R.id.tvLeaderMessage);
    final ImageView ivImage = (ImageView)localView.findViewById(R.id.IVmessagePic);
    
    imageLoader.DisplayImage(image[paramInt], ivImage);
    
//    int fallback = R.drawable.dci_button;
//    
//  //your image url
//    String url ="http://www.denimclubindia.com/images/who_is_who/Arun_Jaitley_sm.jpg" ;
//    
//    ImageLoader imageLoader = ImageLoader.getInstance();
//    DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
//    				.cacheOnDisc(true).resetViewBeforeLoading(true)
//    				.showImageForEmptyUri(fallback)
//    				.showImageOnFail(fallback)
//    				.showImageOnLoading(fallback).build();
//    		
//    //initialize image view
//    	
//
//    //download and display image from url
//    imageLoader.displayImage(url, ivImage, options);
//    
    
    
    
//    Thread asyimage = new Thread() {
//		
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			try {
////		        URL url = new URL("http://www.denimclubindia.com/images/who_is_who/Arun_Jaitley_sm.jpg");
//		       URL url = new URL(image[paramInt]);
//				//try this url = "http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg"
//		        HttpGet httpRequest = null;
//
//		        httpRequest = new HttpGet(url.toURI());
//
//		        HttpClient httpclient = new DefaultHttpClient();
//		        HttpResponse response = (HttpResponse) httpclient
//		                .execute(httpRequest);
//
//		        HttpEntity entity = response.getEntity();
//		        BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
//		        InputStream input = b_entity.getContent();
//
//		        Bitmap bitmap = BitmapFactory.decodeStream(input);
//
//		        ivImage.setImageBitmap(bitmap);
//
//		    } catch (Exception ex) {
//
//		    }
//		}
//	};
//	asyimage.start();
    
    
//    Picasso.with(getContext()).load("http://www.denimclubindia.com/images/who_is_who/Arun_Jaitley_sm.jpg")
//    .into(ivImage);
    
//    localImageView.setImageResource(R.drawable.ic_launcher);
    localTextView1.setText(this.Name[paramInt]);
    localTextView2.setText(this.Designation[paramInt]);
    localTextView3.setText(this.MessageString[paramInt]);
    
    
    return localView;
  }
}


/* Location:              E:\DCIapp\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\com\deepak\dciapp\CustomListAdapterForMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */