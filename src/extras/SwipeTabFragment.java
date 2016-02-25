package extras;

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

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deepak.dci.R;
import com.deepak.dci.WhosWhoDetail;
import com.deepak.dci.WhosWhoListAdapter;


public class SwipeTabFragment extends Fragment{
	
	private String tab;
	private int color;
	GridView l;
	String[] BusinessSegments = {
			"Tom", "Dick", "Harry", "Peter",
	};
	View view;
	WhosWhoListAdapter localArrayAdapter;
	
	private String pageno="0";
//	ProgressDialog PD;
	int totalPage = 0;
	ArrayList<String> wid,images;

//	ArrayAdapter<String> localArrayAdapter;
	
//	String[] Img;

	ArrayList<String> BusinessSeg;
//	ListView l;
	
	Button wNext,wPrev;
	TextView wpage;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		pageno = bundle.getInt("page")+"";
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.gridviewlayout, null);
		l = ((GridView)view.findViewById(R.id.gridView));
		
		populateList();
		return view;
	}
	
	private void populateList() {
		// TODO Auto-generated method stub
		new HttpAsyncTask().execute(pageno);


	}
	
	private class HttpAsyncTask extends AsyncTask<String, Void, String>{
		LinearLayout PD;
		JSONArray jsonArray;
		int lengthjsonArr;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			PD = (LinearLayout)view.findViewById(R.id.linlaHeaderProgress);
			PD.setVisibility(View.VISIBLE);
//			PD = new ProgressDialog(WhosWho.this);
//			PD.setTitle("Please wait..");
//			PD.setMessage("Loading..");
//			PD.setCancelable(false);
//			PD.show();

		}

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub

			String page = (String)urls[0];
			try{
				String link = "http://www.denimclubindia.com/mapp/tables/whoswho.php?page="+page;
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

				BusinessSeg = new ArrayList<String>();
				wid = new ArrayList<String>();
				images = new ArrayList<String>();
				lengthjsonArr = jsonArray.length();
				
				
				
				for(int i=0;i<lengthjsonArr;i++){
					BusinessSeg.add(jsonArray.getJSONObject(i).getString("title")+" "+jsonArray.getJSONObject(i).getString("firstname")+" "+jsonArray.getJSONObject(i).getString("lastname"));
					wid.add(jsonArray.getJSONObject(i).getString("id"));
					images.add(jsonArray.getJSONObject(i).getString("image"));

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

			BusinessSegments = BusinessSeg.toArray(new String[BusinessSeg.size()]);
			String[] Img = images.toArray(new String[images.size()]);
//			String[] Img = {"sdaf","asdf","asdf","asdf","sadf","asdf"};
//			PD.dismiss();
			PD.setVisibility(View.GONE);
//			for(int i=0;i<images.size();i++){
//				Toast.makeText(getApplicationContext(), images.get(i),Toast.LENGTH_LONG).show();
//			}
			
			for(int i=0;i<Img.length;i++){
				Img[i] = "http://www.denimclubindia.com"+Img[i];
				
			}
			
			localArrayAdapter = new WhosWhoListAdapter(getActivity(),  BusinessSegments, Img);
			
			l.setAdapter(localArrayAdapter);
			
			
			l.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent i = new Intent(getActivity(), WhosWhoDetail.class);

					i.putExtra("id", wid.get(position));
					i.putExtra("name", BusinessSeg.get(position));
					startActivity(i);
				}
			});
			
			
//			wpage.setText((Integer.parseInt(pageno)+1)+"");
//			if(pageno.equals("0")){
//				wPrev.setEnabled(false);
//			}else{
//				wPrev.setEnabled(true);
//			}
//			if(BusinessSegments.length<6){
//				wNext.setEnabled(false);
//			}else{
//				wNext.setEnabled(true);
//			}

		}

	}
	
	
}
