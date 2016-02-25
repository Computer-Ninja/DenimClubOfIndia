package com.deepak.dci;

import java.util.TimerTask;

import com.deepak.dci.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends Activity{
	boolean isRunning = true;
	ImageView logo;
	TextView title,denim,tvskip;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();

		setContentView(R.layout.splash_layout);

		logo = (ImageView)findViewById(R.id.ivSplashbutton);
		title = (TextView)findViewById(R.id.tvSplashText);
		denim = (TextView)findViewById(R.id.tvSplashDenim);
		tvskip = (TextView)findViewById(R.id.tvSplashScreenSkip);
		tvskip.setAlpha(0);


		final Animation a1 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
		final Animation a2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.uphalftransition);
		title.startAnimation(a2);
		denim.startAnimation(a2);
		logo.startAnimation(a1);



		a1.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub

				startTimer();


			}
		});
	}

	public void startTimer(){

		tvskip.setAlpha(1);

		tvskip.setText("Click here to continue");
		tvskip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isRunning = false;
				startMainActivity();
			}
		});
		Handler handler = new Handler();

		handler.postDelayed(new Runnable() {
			public void run() {
				if(isRunning)
					startMainActivity();
			}
		}, 20000);



	}

	private void startMainActivity(){
		startActivity(new Intent(getApplicationContext(),DenimHome.class));
		finish();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		isRunning = false;
	}
}
