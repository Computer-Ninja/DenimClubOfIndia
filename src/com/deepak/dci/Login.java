package com.deepak.dci;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity implements View.OnClickListener
{
	TextView forgetpassword;
	Button login;
	EditText password;
	Button subscribe;
	EditText userName;
	TextView uiUpdata;
	String username12,valid_till,user_status;
	ProgressDialog PD;
	boolean isValid=false;
	protected void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.login);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		this.subscribe = ((Button)findViewById(R.id.bSubscribe));
		this.login = ((Button)findViewById(R.id.bLogin));
		this.forgetpassword = ((TextView)findViewById(R.id.tvForgotPassword));
		this.userName = (EditText)findViewById(R.id.etUserId);
		this.password = (EditText)findViewById(R.id.etPassword);

		this.subscribe.setOnClickListener(this);
		this.login.setOnClickListener(this);
		this.forgetpassword.setOnClickListener(this);
		uiUpdata = (TextView) findViewById(R.id.uiUpdate);
		
		
		password.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				emailValidation(userName.getText().toString());
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		userName.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

				emailValidation(userName.getText().toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
//				emailValidation(s.toString());
			}
		});
		
	}






	public void onClick(View paramView)
	{
		
		if(paramView.getId()==R.id.bSubscribe){
		 
			startActivity(new Intent(getApplicationContext(),Subscribe.class));
			}
		if(paramView.getId()==R.id.bLogin){
			
			boolean isValid = true; 
			
			if(userName.getText().toString().length()==0){
				userName.setError("Please enter your User ID");
				isValid = false;
			}
			if(password.getText().toString().length()==0){
				password.setError("Please enter your Password");
				isValid = false;
			}
			
			if(isValid){
				new LongOperation().execute(userName.getText().toString(),password.getText().toString());
			}
			
			
			
			
		}
		if(paramView.getId()==R.id.tvForgotPassword){
			 
			
			startActivity(new Intent(getApplicationContext(),ForgetPassword.class));
		}
		
	}




	private class LongOperation extends AsyncTask<String, Void, String> {

		String success;
		Date validtill,sysdate;

		@Override
		protected void onPreExecute() {
			
			super.onPreExecute();
			PD = new ProgressDialog(Login.this);
			PD.setTitle("Signing in..");
			PD.setMessage("Please wait..");
			PD.show();
		}


		@Override
		protected String doInBackground(String... params) {
			

			try{
				String username = (String)params[0];
				String password = (String)params[1];
				String link = "http://www.denimclubindia.com/mapp/login_final.php?userid="+username+"&password="+password;
				//				String link = "http://www.denimclubindia.com/mapp/login_final.php?userid=design@therealtime.com&password=Dsahu@12345";
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

				JSONArray	jsonArray  = new JSONArray( sb.toString());


				//				int lengthjsonArr = jsonArray.length();

				success     = jsonArray.getJSONObject(0).getString("success");
				if(success.equals("1")){
					username12 = jsonArray.getJSONObject(0).getString("username");
					valid_till = jsonArray.getJSONObject(0).getString("valid_till");
					user_status = jsonArray.getJSONObject(0).getString("user_status");
				}


				return sb.toString();

			}catch(Exception e){
				return e.getMessage();
			}







		}


		@Override
		protected void onPostExecute(String result) {


			PD.dismiss();

			if(success.equals("1")){




				String validtill[] = valid_till.split(" ");
				SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				String strDate = dt.format(new Date());
				String cD[] = strDate.split("-");
				String dat[] = cD[2].split(" ");
				int y,m,d;

				y = Integer.parseInt(cD[0]);
				m = Integer.parseInt(cD[1]);
				d = Integer.parseInt(dat[0]);


				String vD[] = validtill[0].split("-");
				int vy,vm,vd;

				vy = Integer.parseInt(vD[0]);
				vm = Integer.parseInt(vD[1]);
				vd = Integer.parseInt(vD[2]);
				boolean isValid=false;

				if(vy>y)	isValid = true;
				else if(vy==y) {
					if(vm>m) isValid = true;
					else if(vm==m)	{
						if(vd>=d) isValid = true;
					}


				}

				if(isValid){
					Intent i = new Intent(getApplicationContext(), SuccessfulLogin.class);
					i.putExtra("username", username12);
					i.putExtra("userid",userName.getText().toString());
					i.putExtra("valid_till", valid_till);
					i.putExtra("user_status", user_status);

					startActivity(i);
					return;
				}else{



					AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
					alertDialog.setTitle("Sorry"); 
					alertDialog.setMessage("Your subscription expired on " + valid_till ); 
					alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", 
							new DialogInterface.OnClickListener() { 
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						} 
					}); 
					alertDialog.show(); 

					
				}
				
			}
				else{
					AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
					alertDialog.setTitle("Sorry"); 
					alertDialog.setMessage("User Id or Password entered by you is incorrect"); 
					alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", 
							new DialogInterface.OnClickListener() { 
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						} 
					}); 
					alertDialog.show(); 
				}

			}
		}



	public boolean emailValidation(String email){
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;

		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (!matcher.matches()) {
			userName.setError("Please enter valid email address");
			return false;
		} 
		return true;
	}
	
	public boolean passwordLength(String pwd){
		if(pwd.length()<8){
			password.setError("Please enter at least 8 characters");
			
			return false;
		}
		return true;
	}






	
	}


