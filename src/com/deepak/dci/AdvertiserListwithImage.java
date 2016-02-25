package com.deepak.dci;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdvertiserListwithImage extends ArrayAdapter<String> {

	Activity context;
	String[] compname;
	String[] images;
	
	  public ImageLoader imageLoader; 
	
	
	public AdvertiserListwithImage(Activity context, String[] CompName,String[] Images) {
		super(context,R.layout.itemwithimage,CompName);
//		super(context,android.R.layout.simple_list_item_1,CompName);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.compname = CompName;
		this.images = Images;
	}

	public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
	  {
		if(paramView==null){
		 View localView = this.context.getLayoutInflater().inflate(R.layout.itemwithimage, null,false);
//		View localView = this.context.getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null,false);
		    TextView tvCompName = (TextView)localView.findViewById(R.id.firstLine);
			final ImageView ivCompLogo = (ImageView)localView.findViewById(R.id.icon);
		
			tvCompName.setText(compname[paramInt]);
		
			 imageLoader.DisplayImage("http://www.denimclubindia.com/images/advert_logo/"+images[paramInt], ivCompLogo);
	    
//		Thread asyimage = new Thread() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
////			       
//			       URL url = new URL("http://www.denimclubindia.com/images/advert_logo/"+images[paramInt]);
//					HttpGet httpRequest = null;
//
//			        httpRequest = new HttpGet(url.toURI());
//
//			        HttpClient httpclient = new DefaultHttpClient();
//			        HttpResponse response = (HttpResponse) httpclient
//			                .execute(httpRequest);
//
//			        HttpEntity entity = response.getEntity();
//			        BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
//			        InputStream input = b_entity.getContent();
//
//			        Bitmap bitmap = BitmapFactory.decodeStream(input);
//
//			        ivCompLogo.setImageBitmap(bitmap);
//
//			    } catch (Exception ex) {
//
//			    }
//			}
//		};
//		asyimage.start();
	    
			
		
		return localView;
		}
		else return paramView;
	  }
	
	
	
	
}
