package com.deepak.dci;
import android.app.Activity;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageListAdapter extends ArrayAdapter<String>{

	Activity context;
	String[] image,title,msg,desig;
	ImageLoader imageLoader;
	
	public MessageListAdapter(Activity context,String[] image,String[] title,String[] msg, String[] desig) {
		super(context, R.layout.briefmessagelayout,title);
		this.context = context;
		this.image = image;
		this.title = title;
		this.msg = msg;
		this.desig = desig;
		imageLoader = new ImageLoader(this.context.getApplicationContext());
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View rowView = this.context.getLayoutInflater().inflate(R.layout.briefmessagelayout, null,false);
		TextView title = (TextView)rowView.findViewById(R.id.tvbreifmessagetitle);
		TextView text = (TextView)rowView.findViewById(R.id.tvbriefmessagetext);
		TextView desig = (TextView)rowView.findViewById(R.id.tvbreifmessagedesig);
		ImageView iv = (ImageView)rowView.findViewById(R.id.ivbriefmessageimage);
	
		msg[position] = msg[position].replaceAll("<p>", "");
		msg[position] = msg[position].replaceAll("</p>", "");
		
		title.setText(this.title[position]);
		text.setText(Html.fromHtml(this.msg[position]));
		desig.setText(this.desig[position]+"");
		imageLoader.DisplayImage(image[position], iv);
		return rowView;
	}

}
