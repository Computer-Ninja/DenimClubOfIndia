package com.deepak.dci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubscriptionForm
  extends Activity
{
  LinearLayout l1;
  LinearLayout l2;
  TextView row1;
  TextView row2;
  TextView row3;
  TextView row4;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.subscriptiondetail_layout);
    this.row1 = ((TextView)findViewById(R.id.tvRow1Col2));
    this.row2 = ((TextView)findViewById(R.id.tvRow2Col2));
    this.row3 = ((TextView)findViewById(R.id.tvRow3Col2));
    this.row4 = ((TextView)findViewById(R.id.tvRow4Col2));
    this.l1 = ((LinearLayout)findViewById(R.id.llSubsForm));
    this.l2 = ((LinearLayout)findViewById(R.id.llSubsLogin));
    this.row1.setText(getIntent().getExtras().getString("paymentmode"));
    if (getIntent().getExtras().getBoolean("country"))
    {
      this.row2.setText("India");
    }else
    	this.row2.setText("Other Country");
      if (!getIntent().getExtras().getBoolean("member")) {
        

      this.row3.setText("Yes");
      this.l2.removeAllViewsInLayout();
    }else{
      this.row3.setText("No");
      this.l1.removeAllViewsInLayout();
      
    }
    {
      this.row4.setText("12345");

      }
  }
}


/* Location:              E:\DCIapp\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\com\deepak\dciapp\SubscriptionForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */