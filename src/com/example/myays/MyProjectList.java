package com.example.myays;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ShareActionProvider;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyProjectList extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{
	
	private final static String TAG_STRING = "lch";
	private ShareActionProvider mShareActionProvider;
	
	// This is the Adapter being used to display the list's data
    SimpleCursorAdapter mAdapter;

    // These are the Contacts rows that we will retrieve
    static final String[] PROJECTION = new String[] {ContactsContract.Data._ID,
            ContactsContract.Data.DISPLAY_NAME};

    // This is the select criteria
    static final String SELECTION = "((" + 
            ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" +
            ContactsContract.Data.DISPLAY_NAME + " != '' ))";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this method only suits for API 11+
        final ActionBar mActionBar = getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mActionBar.show();
        
        // Create a progress bar to display while the list loads
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);

        // For the cursor adapter, specify which columns go into which views
        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME};
        int[] toViews = {android.R.id.text1}; // The TextView in simple_list_item_1

        // Create an empty adapter we will use to display the loaded data.
        // We pass null for the cursor, then update it in onLoadFinished()
        mAdapter = new SimpleCursorAdapter(this, 
                android.R.layout.simple_list_item_1, null,
                fromColumns, toViews, 0);
        setListAdapter(mAdapter);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_my_projectlist, menu);
        
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider)shareItem.getActionProvider();
        mShareActionProvider.setShareIntent(getDefaultIntent());
        
        return true;
    }
    
    
    /** Defines a default (dummy) share intent to initialize the action provider.
     * However, as soon as the actual content to be used in the intent
     * is known or changes, you must update the share intent by again calling
     * mShareActionProvider.setShareIntent()
     */
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        
        switch (item.getItemId()) {
		case R.id.action_search:
			Toast.makeText(this, "you pressed search", Toast.LENGTH_LONG).show();
			break;
		case R.id.action_share:
			Toast.makeText(this, "you pressed share", Toast.LENGTH_LONG).show();
			break;
		case R.id.action_settings:
			Toast.makeText(this, "you pressed settings", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
        
        return super.onOptionsItemSelected(item);
    }

	// Called when a previously created loader has finished loading
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(data);
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
    }

    // Called when a new Loader needs to be created
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
    	// Now create and return a CursorLoader that will take care of
    	// creating a Cursor for the data being displayed.
    	return new CursorLoader(this, ContactsContract.Data.CONTENT_URI,
    			PROJECTION, SELECTION, null, null);
    }
    @Override 
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked
    	
    	TextView tView = (TextView)v;
    	String mString = tView.getText().toString();
    	Log.i(TAG_STRING, mString);
    			
    	
    }

}
