package com.example.myays;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyProjectListFrag extends Fragment {

	
	private final static String TAG = "lch";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		return super.onCreateView(inflater, container, savedInstanceState);
		
		Log.i(TAG, "create view for my project list");
		return inflater.inflate(R.layout.frag_my_project_list, container, false);
		
		
	}
	
	
	

}
