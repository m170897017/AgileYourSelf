package com.example.myays;

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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myays.dialogs.AddNewPlanDialog;

public class MainActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	private static final String TAG = "lch";
    private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

	private ViewPager mViewPager;
	private final static int TAB_NUM = 3;
	private final static int MY_PROJECT_LIST_ID = 0;
	private final static int TEAM_PROJECT_LIST_ID = 1;
	private final static int DISCOVER_PROJECT_LIST_ID = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mViewPager = (ViewPager) findViewById(R.id.pager);

		// add tab to action bar here
        MyProjectListAdapter mProjectListAdapter = new MyProjectListAdapter(
                getSupportFragmentManager());

		final ActionBar mActionBar = getSupportActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // add view pager to switch between tabs
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

		// ### now start to set navigation drawer

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set list view action
        String[] mNavigationDrawerListStrings = getResources().getStringArray(
                R.array.navigation_drawer_array);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mNavigationDrawerListStrings));

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // set drawer view action
		mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}

		};
		mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        // ### end of set navigation drawer

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

	}

	// when drawer is open, press back button will lead to main activity rather
	// than quit app
	@Override
	public void onBackPressed() {

		if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
			mDrawerLayout.closeDrawer(Gravity.START);
		} else {
			super.onBackPressed();
		}
	}

    /*
    Use this function to refresh fragments in viewpager
     */
    public void refreshViewPager(){
        mViewPager.getAdapter().notifyDataSetChanged();

    }

    /*
    Do something when item in drawer navigation is pressed.
     */
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
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat
                .getActionProvider(shareItem);

		mShareActionProvider.setShareIntent(getDefaultIntent());
		return super.onCreateOptionsMenu(menu);
	}

	public Intent getDefaultIntent() {
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
			mAddNewPlanDialog.show(getSupportFragmentManager(),
                    "addNewPlanDialog");
			break;
		default:
			break;

		}

        return mActionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

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

        /*
        This method is used to refresh fragment in viewpager
        It can only be triggered by method refreshViewPager() in MainActivity
         */
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
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
