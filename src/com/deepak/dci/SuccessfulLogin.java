package com.deepak.dci;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class SuccessfulLogin extends Activity{
	
	CheckBox rememberme;
	Button directtohome;
	TextView username,userid,valid_till,user_status;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.successful_login);
		
		getIntent().getExtras().get("userid");
		
		
		directtohome = (Button)findViewById(R.id.bsuccessfullogindirecthome);
		username = (TextView)findViewById(R.id.tvSuccessfulLoginRow1Col2);
		userid = (TextView)findViewById(R.id.tvSuccessfulLoginRow2Col2);
		valid_till = (TextView)findViewById(R.id.tvSuccessfulLoginRow3Col2);
		user_status = (TextView)findViewById(R.id.tvSuccessfulLoginRow4Col2);
		
		
		username.setText(getIntent().getExtras().getString("username"));
		userid.setText(getIntent().getExtras().getString("userid"));
		valid_till.setText(getIntent().getExtras().getString("valid_till"));
		user_status.setText(getIntent().getExtras().getString("user_status"));
		
		
		directtohome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
