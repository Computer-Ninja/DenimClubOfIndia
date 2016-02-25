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

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Sponsors
  extends Activity
{
  ListView l;
  String[] sponsorName = { "MRS Fashion", "VIKMANS", "Itema S.P.A", "Mehala" };
  String[] sponsorType = { "Platinum", "Gold", "Silver", "Silver" };
  String[] SponsorImage ={"http://denimclubindia.com/images/advert_logo/must_logo_1.jpg","http://denimclubindia.com/images/advert_logo/vikmans_logo_1.jpg",
		  "http://denimclubindia.com/images/advert_logo/itema_logo_1.jpg","http://denimclubindia.com/images/advert_logo/mehala_logo_1.jpg"};
public String[] advertiserString;
public String[] advertiserImage;
public ArrayList<String> advImage;
public ArrayList<String> Advertiser;
public ArrayList<String> advId;


  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.listlayoutonly);
  
    new HttpAsyncTask().execute();
    
//    l = ((ListView)findViewById(R.id.lvadvertiserlist));
    
//    this.l.setOnItemClickListener(new AdapterView.OnItemClickListener()
//    {
//      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
//      {
//        Toast.makeText(Sponsors.this.getApplicationContext(), Sponsors.this.sponsorName[paramAnonymousInt], 1).show();
//      }
//    });
  }
  
  
  private class HttpAsyncTask extends AsyncTask<String, Void, String>{
	  ProgressDialog PD;
		JSONArray jsonArray;
		int lengthjsonArr;
		@Override
		protected void onPreExecute() {
			
			super.onPreExecute();
			
			PD = new ProgressDialog(Sponsors.this);
			PD.setTitle("Please wait..");
			PD.setMessage("Loading..");
			PD.setCancelable(false);
			PD.show();

		}

		@Override
		protected String doInBackground(String... urls) {
		

			
			try{
				String link = "http://www.denimclubindia.com/mapp/tables/advertiserslist.php?category=Sponsor&page="+1;
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
				advImage = new ArrayList<String>();
				Advertiser = new ArrayList<String>();
				advId = new ArrayList<String>();
				lengthjsonArr = jsonArray.length();
				for(int i=0;i<lengthjsonArr;i++){
					Advertiser.add(jsonArray.getJSONObject(i).getString("compname"));
					advId.add(jsonArray.getJSONObject(i).getString("id"));
					advImage.add(jsonArray.getJSONObject(i).getString("logoimage"));
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
			advertiserString = Advertiser.toArray(new String[Advertiser.size()]);
			advertiserImage =    advImage.toArray(new String[advImage.size()]);
			int blen = advertiserString.length;
			for(int i=0;i<blen;i++){
				advertiserString[i] = advertiserString[i].replaceAll("&#38;", "&");
			}
			
			PD.dismiss();
//			ArrayAdapter<String> localArrayAdapter;
//			localArrayAdapter = new ArrayAdapter<String>(Sponsors.this,  android.R.layout.simple_list_item_1, advertiserString);
//			l.setAdapter(localArrayAdapter);
			
//			for(int i=0;i<advertiserString.length;i++){
//				
//			
//			Toast.makeText(getApplicationContext(), advertiserString[i], Toast.LENGTH_LONG).show();;
//			}
			CustomListWith2Rows localCustomListWith2Rows = new CustomListWith2Rows(Sponsors.this, advertiserString, advertiserString,advertiserImage);
		    l = ((ListView)findViewById(R.id.listviewlayout));
		   l.setAdapter(localCustomListWith2Rows);
			
		}


	}


	
  
}


