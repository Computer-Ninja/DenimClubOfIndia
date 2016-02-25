package com.deepak.dci;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfessionalDetail extends Activity{
	TextView tvName,tvOrganisation,tvDesig,tvLocation,tvEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.professionaldetaillayout);
		
		tvName = (TextView)findViewById(R.id.tvprofessionalname);
		tvOrganisation = (TextView)findViewById(R.id.tvprofessionalorganisation);
		tvDesig = (TextView)findViewById(R.id.tvprofessionaldesignation);
		tvLocation = (TextView)findViewById(R.id.tvprofessionallocation);
		tvEmail = (TextView)findViewById(R.id.tvprofessionalemail);
		
		tvName.setText(getIntent().getExtras().getString("name"));
		tvOrganisation.setText(getIntent().getExtras().getString("compname"));
		tvDesig.setText(getIntent().getExtras().getString("desig"));
		tvLocation.setText(getIntent().getExtras().getString("address"));
		tvEmail.setText(getIntent().getExtras().getString("email"));
//		
		setTitle("Display Details "+getIntent().getExtras().getString("name")+" ::Professional Listing");
//		
	}
}
