package com.example.myays;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

	

public class DiscoverProjectListFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//		return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.frag_discover_project_list, container, true);
	}

	
}
