package com.example.myays;

import com.example.myays.dialogs.AddNewPlanDialog;

import android.R.integer;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	private static final String TAG = "lch";
	private MyProjectListAdapter mProjectListAdapter;
	private ShareActionProvider mShareActionProvider;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mActionBarDrawerToggle;
	private String[] mNavigationDrawerListStrings;
	private CharSequence mTitle = "this is mTitle";
	private CharSequence mDrawerTitle = "this is mDrawerTitle";
	private AddNewPlanDialog mAddNewPlanDialog;
	private AddNewPlanDialog mAddNewPlanDialogForPriority;
	private Button mPriorityButton;

	private ViewPager mViewPager;
	private final static int TAB_NUM = 3;
	private final static int MY_PROJECT_LIST_ID = 0;
	private final static int TEAM_PROJECT_LIST_ID = 1;
	private final static int DISCOVER_PROJECT_LIST_ID = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();
		mViewPager = (ViewPager) findViewById(R.id.pager);

		// add tab to action bar here
		mProjectListAdapter = new MyProjectListAdapter(
				getSupportFragmentManager());

		final ActionBar mActionBar = getSupportActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

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

		// now start to set navigation drawer
		mNavigationDrawerListStrings = getResources().getStringArray(
				R.array.navigation_drawer_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mNavigationDrawerListStrings));

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getSupportActionBar().setTitle(mTitle);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle(mDrawerTitle);
			}

		};
		mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

	}

	
	// when drawer is open, press back button will lead to main activity rather than quit app
	@Override
	public void onBackPressed() {
		
		if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {	
			mDrawerLayout.closeDrawer(Gravity.LEFT);
		}
		else {
			super.onBackPressed();
		}
		
	}



	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {

		switch (position) {
		case 0:
			Intent intent = new Intent(this, LogInActivity.class);
			startActivity(intent);
			break;
		case 1:
			Intent intentHelp = new Intent(this, HelpActivity.class);
			startActivity(intentHelp);
			break;
		case 2:
			Intent intentAboutUs = new Intent(this, AboutUsActivity.class);
			startActivity(intentAboutUs);
			break;

		default:
			Intent intentDefault = new Intent(this, LogInActivity.class);
			startActivity(intentDefault);
			break;
		}

	}

	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mActionBarDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mActionBarDrawerToggle.onConfigurationChanged(newConfig);
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
		case R.id.action_add_new_plan:
			AddNewPlanDialog mAddNewPlanDialog = new AddNewPlanDialog();
			mAddNewPlanDialog.show(getSupportFragmentManager(), "addNewPlanDialog");
			break;
		default:
			break;

		}

		if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	
	public void updatePriority(int selection){
		
		Log.i(TAG, "enter update priority");
		mAddNewPlanDialogForPriority = (AddNewPlanDialog) getSupportFragmentManager().findFragmentByTag("addNewPlanDialog");
		mPriorityButton = (Button) mAddNewPlanDialogForPriority.getView().findViewById(R.id.btn_add_new_plan_priority);
		mPriorityButton.setText("pick"+selection);
		Log.i(TAG, "finish update priority");
		// TODO: getSupportFragmentManager().beginTransaction().replace(arg0, arg1)
		
	}
	
	

	/*
	 * FragmentPagerAdapter has to use android.support.v4.app.FragmentManager as
	 * its parameter
	 */
	public static class MyProjectListAdapter extends FragmentPagerAdapter {

		public MyProjectListAdapter(FragmentManager fm) {
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

	@Override
	public void onTabReselected(android.support.v7.app.ActionBar.Tab arg0,
			android.support.v4.app.FragmentTransaction arg1) {
		// Auto-generated method stub

	}

	@Override
	public void onTabSelected(android.support.v7.app.ActionBar.Tab arg0,
			android.support.v4.app.FragmentTransaction arg1) {
		mViewPager.setCurrentItem(arg0.getPosition());

	}

	@Override
	public void onTabUnselected(android.support.v7.app.ActionBar.Tab arg0,
			android.support.v4.app.FragmentTransaction arg1) {
		// Auto-generated method stub

	}
}
