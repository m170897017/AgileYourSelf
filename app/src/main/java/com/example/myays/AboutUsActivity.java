package com.example.myays;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class AboutUsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

}
