package com.deepak.dci;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Subscribe
extends Activity
implements View.OnClickListener
{
	CheckBox cbAlreadyMem;
	CheckBox cbTermCond;
	Button proceed;
	Spinner sCountry;
	Spinner sPaymentMode;
	TextView termcond;
	public void onClick(View paramView)
	{
		
		
		if(paramView.getId()== R.id.bSubsProceed){
		if (this.cbTermCond.isChecked())
		{
			Intent localIntent = new Intent(this, SubscriptionForm.class);
			localIntent.putExtra("country", this.sCountry.getSelectedItem().toString());
			localIntent.putExtra("member", this.cbAlreadyMem.isChecked());
			localIntent.putExtra("paymentmode", this.sPaymentMode.getSelectedItem().toString());
			startActivity(localIntent);
			return;
		}else
		termcond.setText("Please check terms and condition");
			
		}
	}

	protected void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.subscribe);
		this.sCountry = ((Spinner)findViewById(R.id.sCountry));
		this.sPaymentMode = ((Spinner)findViewById(R.id.sSubscribePaymentmode));
		this.cbAlreadyMem = ((CheckBox)findViewById(R.id.cbMember));
		this.cbTermCond = ((CheckBox)findViewById(R.id.cbTermCond));
		this.proceed = ((Button)findViewById(R.id.bSubsProceed));
		termcond = (TextView)findViewById(R.id.tvsubtermcond);
		this.proceed.setOnClickListener(this);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		cbTermCond.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					termcond.setText("");
				}else
					termcond.setText("Please check terms and conditions");
			}
		});
	}
}


/* Location:              E:\DCIapp\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\com\deepak\dciapp\Subscribe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */