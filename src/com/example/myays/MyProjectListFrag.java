package com.example.myays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

//TODO use listfragment instead of fragment, it should work
public class MyProjectListFrag extends Fragment {

	private final static String TAG = "lch";

	private MyProjectListFragment myProjectListFragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater
				.inflate(R.layout.frag_my_project_list, container, false);
	}

	/*
	 * Put fragment update in this function and return view in onCreateView in
	 * case of error in no view found for fragment
	 * 
	 * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		FragmentManager mFragmentManager = getFragmentManager();
		myProjectListFragment = new MyProjectListFragment();
		mFragmentManager
				.beginTransaction()
				.replace(R.id.container_for_my_pro_list, myProjectListFragment,
						"myProjectListFragment").commit();

	}

	public static class MyProjectListFragment extends ListFragment {

		private final static String TAG = "lch";
		private String[] titles = new String[] { "title0", "title1", "title2" };
		private String[] times = new String[] { "time0", "time1", "time2" };
		private String[] infos = new String[] { "info0", "info1", "info2" };
		private int[] logos = new int[] { R.drawable.ic_action_cloud,
				R.drawable.ic_action_dock, R.drawable.ic_launcher };

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			// ArrayAdapter<String> adapter = new ArrayAdapter<String>(
			// inflater.getContext(), android.R.layout.simple_list_item_1,
			// numStrings);
			// setListAdapter(adapter);
			// return super.onCreateView(inflater, container,
			// savedInstanceState);

			List<HashMap<String, String>> mList = new ArrayList<HashMap<String, String>>();

			for (int i = 0; i < 3; i++) {

				HashMap<String, String> mHashMap = new HashMap<String, String>();
				mHashMap.put("nickname", titles[i]);
				mHashMap.put("rightnickname", times[i]);
				mHashMap.put("imageview_selector", Integer.toString(logos[i]));
				mHashMap.put("rightnickname2", infos[i]);
				mList.add(mHashMap);
			}

			String[] from = { "nickname", "rightnickname",
					"imageview_selector", "rightnickname2" };
			int[] to = { R.id.nickname, R.id.rightnickname,
					R.id.imageview_selector, R.id.rightnickname2 };

			SimpleAdapter adapter = new SimpleAdapter(getActivity(), mList,
					R.layout.frag_my_project_list_1, from, to);
			setListAdapter(adapter);
			return super.onCreateView(inflater, container, savedInstanceState);
		}
		
		

		/*
		 * register should be put here, after view created
		 * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
		 */
		@Override
		public void onActivityCreated(@Nullable Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			registerForContextMenu(getListView());
			
			
		}



		/*
		 * This method will be invoked when item in listfragment is long pressed
		 * to show menu
		 * 
		 * @see
		 * android.support.v4.app.Fragment#onCreateContextMenu(android.view.
		 * ContextMenu, android.view.View,
		 * android.view.ContextMenu.ContextMenuInfo)
		 */
		@Override
		public void onCreateContextMenu(ContextMenu menu, View v,
				ContextMenuInfo menuInfo) {
			
			
			Log.i(TAG, "long press!! start to show context menu!!");
			super.onCreateContextMenu(menu, v, menuInfo);
			MenuInflater menuInflater = getActivity().getMenuInflater();
			menuInflater.inflate(R.menu.menu_for_item_in_my_project_list, menu);

		}

		/*
		 * This method will be invoked when item in the menu is selected
		 * 
		 * @see
		 * android.support.v4.app.Fragment#onContextItemSelected(android.view
		 * .MenuItem)
		 */
		@Override
		public boolean onContextItemSelected(MenuItem item) {
			String infoString = item.getTitle().toString();

			Toast.makeText(getActivity(), infoString, Toast.LENGTH_LONG).show();

			// you can do something here with switch statement

			return super.onContextItemSelected(item);
		}

	}

}
