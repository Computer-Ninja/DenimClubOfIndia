package com.deepak.dci;

import java.util.zip.Inflater;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends Fragment{

	private String title;
	private int page;
	
	public static FirstFragment newInstance(int page, String title){
		FirstFragment firstFragment = new FirstFragment();
//		firstFragment.title = title;
//		firstFragment.page = page;
		
		Bundle args = new Bundle();
		args.putInt("someInt", page);
		args.putString("someTitle", title);
		firstFragment.setArguments(args);
		return firstFragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		page = getArguments().getInt("someInt", 0);
		title = getArguments().getString("someTitle");
		
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
		View view = inflater.inflate(R.layout.viewpagerlayout,container, false);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	
	
	}
	
}
