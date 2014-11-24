package com.example.myays;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	private static final String TAG = "lch";
	private MyProjectListAdapter mProjectListAdapter;
	private ShareActionProvider mShareActionProvider;

	private ViewPager mViewPager;
	private final static int TAB_NUM = 3;
	private final static int MY_PROJECT_LIST_ID = 0;
	private final static int TEAM_PROJECT_LIST_ID = 1;
	private final static int DISCOVER_PROJECT_LIST_ID = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mProjectListAdapter = new MyProjectListAdapter(
				getSupportFragmentManager());
		final ActionBar mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//		mActionBar.show();
		// Set up the ViewPager, attaching the adapter and setting up a listener
		// for when the
		// user swipes between sections.
		// mViewPager = (ViewPager) findViewById(R.id.pager);

		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);
		setContentView(mViewPager);
		mViewPager.setAdapter(mProjectListAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						// When swiping between different app sections, select
						// the corresponding tab.
						// We can also use ActionBar.Tab#select() to do this if
						// we have a reference to the
						// Tab.
						mActionBar.setSelectedNavigationItem(position);
					}
				});

		mActionBar.addTab(mActionBar.newTab().setText("My Plan")
				.setTabListener(this));
		mActionBar.addTab(mActionBar.newTab().setText("Team")
				.setTabListener(this));
		mActionBar.addTab(mActionBar.newTab().setText("Discover")
				.setTabListener(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_for_my_projectlist, menu);

		MenuItem shareItem = menu.findItem(R.id.action_share);
		mShareActionProvider = (ShareActionProvider) MenuItemCompat
				.getActionProvider(shareItem);

		mShareActionProvider.setShareIntent(getDefaultIntent());
		return super.onCreateOptionsMenu(menu);
	}

	private Intent getDefaultIntent() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("image/*");
		return intent;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		switch (item.getItemId()) {
		case R.id.action_search:
			Toast.makeText(this, "you pressed search", Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.action_share:
			Toast.makeText(this, "you pressed share", Toast.LENGTH_LONG).show();
			break;
		case R.id.action_settings:
			Toast.makeText(this, "you pressed settings", Toast.LENGTH_LONG)
					.show();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	public static class MyProjectListAdapter extends FragmentPagerAdapter {

		public MyProjectListAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int item_num) {

			Log.i(TAG, "enter getItem");
			switch (item_num) {
			case MY_PROJECT_LIST_ID:
				return new MyProjectListFrag();
			case TEAM_PROJECT_LIST_ID:
				return new TeamProjectListFrag();
			case DISCOVER_PROJECT_LIST_ID:
				return new DiscoverProjectListFrag();
			default:
				return new MyProjectListFrag();
			}
		}

		@Override
		public int getCount() {
			return TAB_NUM;
		}

	}
}
