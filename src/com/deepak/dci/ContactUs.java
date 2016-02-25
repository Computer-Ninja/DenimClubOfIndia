package com.deepak.dci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ContactUs
  extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.contactus_layout);
    
    
  }
  
  @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
	MenuInflater inflator = getMenuInflater();
	inflator.inflate(R.menu.activity_action_bar_menu, menu);
	  
	  
	  return super.onCreateOptionsMenu(menu);
	}
  
  @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
	  switch(item.getItemId()){
	  case R.id.actionbar_home:
		  Intent i = new Intent(ContactUs.this,DenimDirectoryHomeMenu.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		  startActivity(i);
		  break;
	  }
	  
	  return super.onOptionsItemSelected(item);
	}
}


/* Location:              E:\DCIapp\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\com\deepak\dciapp\ContactUs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */