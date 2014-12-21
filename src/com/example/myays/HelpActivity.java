package com.example.myays;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class HelpActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	
	
}
