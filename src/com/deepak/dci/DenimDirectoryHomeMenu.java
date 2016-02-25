package com.deepak.dci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class DenimDirectoryHomeMenu
extends Activity
{
	ExpandableListView expListView;
	ExpandableListAdapter listAdapter;
	HashMap<String, List<String>> listDataChild;
	List<String> listDataHeader;

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		this.listDataHeader.add("Home");
		this.listDataHeader.add("Explore");
		this.listDataHeader.add("Contact");
		this.listDataHeader.add("Guest User");
		ArrayList<String> localArrayList1 = new ArrayList<String>();
		localArrayList1.add("Messages");
		localArrayList1.add("Who's Who");
		localArrayList1.add("Business");
		localArrayList1.add("Professionals");
		localArrayList1.add("Sponsors");
		localArrayList1.add("Advertiser");
		ArrayList<String> localArrayList2 = new ArrayList<String>();
		localArrayList2.add("Login");
		localArrayList2.add("Subscribe");
		this.listDataChild.put((String)this.listDataHeader.get(0), new ArrayList<String>());
		this.listDataChild.put((String)this.listDataHeader.get(1), localArrayList1);
		this.listDataChild.put((String)this.listDataHeader.get(2), new ArrayList<String>());
		this.listDataChild.put((String)this.listDataHeader.get(3), localArrayList2);
	}

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandablemenu);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		TextView intro = (TextView)findViewById(R.id.tvexpandablemenuintro);
//		intro.setText(Html.fromHtml("<h1>Explore the Denim Business Directory</h1>"+
//		"<p>The Denim Business Directory is the one and only compilation of data related to denim businesses and professionals. You can explore the contents of various sections of the directory very easily.</p>"+
//				"<p>The directory comprises of various sections, as mentioned below. Please click on any of the section links to explore the contents of that section.</p>"));
//		
		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);


		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				switch(groupPosition){
					
				
				case 1:
						switch(childPosition){
						case 0:
							startActivity(new Intent(getApplicationContext(), MessageList.class));
							break;
						case 1:
							startActivity(new Intent(getApplicationContext(), WhosWho.class));
							break;
						case 2:
							startActivity(new Intent(getApplicationContext(), BusinessMenu.class));
							break;
						case 3:
							startActivity(new Intent(getApplicationContext(), ProfessionalMenu.class));
							break;
						case 4:
							startActivity(new Intent(getApplicationContext(), Sponsors.class));
							break;
						case 5:
							startActivity(new Intent(getApplicationContext(), AdvertiserList.class));
							break;
						}
					break;
					
				case 3:
					switch(childPosition){
					case 0:
						startActivity(new Intent(getApplicationContext(), Login.class));
						break;
					case 1:
						startActivity(new Intent(getApplicationContext(), Subscribe.class));
						break;
					
					}
					
					
					break;
				}


				return false;
			}
			

			
		});
		
		expListView.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// TODO Auto-generated method stub
				switch(groupPosition){
				case 0:
					finish();
					break;
				case 2:
					startActivity(new Intent(getApplicationContext(), ContactUs.class));
					break;
				}
				
				return false;
			}
		});
	}
}


	/* Location:              E:\DCIapp\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\com\deepak\dciapp\DenimDirectoryHomeMenu.class
	 * Java compiler version: 6 (50.0)
	 * JD-Core Version:       0.7.1
	 */