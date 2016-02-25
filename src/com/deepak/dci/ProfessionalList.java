package com.deepak.dci;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfessionalList extends Activity implements OnClickListener
{
	int lengthjsonArr;
	String[] Row1 = { "Tom", "Dick", "Harry" };
	String[] Row2 = { "Denim Club India", "Denim Club India", "Denim Club India" };
	String[] Row3 = { "India", "USA", "London" };
	String[] ID ={};
	ListView list;
	Button prev,next;
	TextView pagenoTextView;
	int page=0;
	ArrayList<String> plName,alOrganisation,plCountry,plDesig,plCompname,plCity,plEmail;
	
	protected void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.listlayoutonly);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		next = (Button)findViewById(R.id.blistlayoutonlynext);
		prev = (Button)findViewById(R.id.blistlayoutonlyprev);
		pagenoTextView = (TextView)findViewById(R.id.tvlistlayoutonlypage);
		
		next.setOnClickListener(this);
		prev.setOnClickListener(this);
		prev.setEnabled(false);
		
		populate();

		list = (ListView)findViewById(R.id.listviewlayout);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(ProfessionalList.this,ProfessionalDetail.class);
				i.putExtra("name",plName.get(position));
				i.putExtra("compname",plCompname.get(position));
				i.putExtra("address",plCity.get(position)+", "+plCountry.get(position));
				i.putExtra("desig",plDesig.get(position));
				i.putExtra("email",plEmail.get(position));
				
				
				startActivity(i);
				
				
				
				
				
			}
		});
		
		
	}

	private void populate() {
		// TODO Auto-generated method stub

		new HttpAsyncTask().execute(getIntent().getExtras().getString("id"),page+"");
	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String>{
		ProgressDialog PD;
		JSONArray jsonArray;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			PD = new ProgressDialog(ProfessionalList.this);
			PD.setTitle("Please wait..");
			PD.setMessage("Loading..");
			PD.setCancelable(false);
			PD.show();
			

		}

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub

			String id = urls[0];
			try{
				String link = "http://www.denimclubindia.com/mapp/tables/professionallist.php?id="+id+"&page="+page;
				URL url = new URL(link);
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet();
				request.setURI(new URI(link));
				HttpResponse response = client.execute(request);
				BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer sb = new StringBuffer();
				String line = "";

				while((line=in.readLine())!=null){
					sb.append(line);
					break;
				}
				in.close();

				jsonArray  = new JSONArray( sb.toString());

				plCompname= new ArrayList<String>();
				plName = new ArrayList<String>();
				plDesig  = new ArrayList<String>();
				plCountry   = new ArrayList<String>();
				plCity  = new ArrayList<String>();
				plEmail  = new ArrayList<String>();
				lengthjsonArr = jsonArray.length();
				for(int i=0;i<lengthjsonArr;i++){
				plName.add(jsonArray.getJSONObject(i).getString("name"));
				plCompname.add(jsonArray.getJSONObject(i).getString("compname"));
				plCountry.add(jsonArray.getJSONObject(i).getString("country"));
				plDesig.add(jsonArray.getJSONObject(i).getString("desig"));
				plCity.add(jsonArray.getJSONObject(i).getString("city"));
				plEmail.add(jsonArray.getJSONObject(i).getString("email"));
				}

				return null;
			}catch(Exception e){
				return e.getMessage();
			}
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			
			
				
			
			
			
			Row1 = plName.toArray(new String[plName.size()]);
			Row2 = plCompname.toArray(new String[plCompname.size()]);
			Row3 = plCountry.toArray(new String[plCountry.size()]);
			
			PD.dismiss();
			CustomListAdapterForThreeRowTile localCustomListAdapterForThreeRowTile = new CustomListAdapterForThreeRowTile(ProfessionalList.this, Row1, Row2, Row3);
			
			list.setAdapter(localCustomListAdapterForThreeRowTile);

			if(Row1.length<4){
				next.setEnabled(false);
			}else
				next.setEnabled(true);
			
			

					
				}

		



		}

	@Override
	public void onClick(View v) {
		
		if(v.getId()==R.id.blistlayoutonlynext){
			page++;
			pagenoTextView.setText((page+1)+"");
			populate();
			prev.setEnabled(true);
			
		}else
		if(v.getId() == R.id.blistlayoutonlyprev){
			page--;
			pagenoTextView.setText((page+1)+"");
			populate();
			if(page==0){
				prev.setEnabled(false);
			}
		}
		
	}


	}





