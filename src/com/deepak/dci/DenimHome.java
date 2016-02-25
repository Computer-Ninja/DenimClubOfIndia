package com.deepak.dci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class DenimHome extends Activity implements OnClickListener {
	Button bDenimDirectory;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.denimhome);
		bDenimDirectory = ((Button)findViewById(R.id.bdenimHomeDirectory));
	    bDenimDirectory.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(), DenimDirectoryHomeMenu.class));
	}

   
}
