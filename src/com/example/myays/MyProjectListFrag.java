package com.example.myays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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
		// TODO Auto-generated method stub
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

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {

			Toast.makeText(getActivity(), v.toString(), Toast.LENGTH_LONG)
					.show();

		}
	}

}
