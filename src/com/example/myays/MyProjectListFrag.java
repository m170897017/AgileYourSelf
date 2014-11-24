package com.example.myays;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyProjectListFrag extends Fragment {
	
	private final static String TAG = "lch";
	
	private MyProjectListFragment myProjectListFragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		FragmentManager mFragmentManager = getFragmentManager();
		myProjectListFragment = new MyProjectListFragment();
		mFragmentManager.beginTransaction().replace(container.getId(), myProjectListFragment, "myProjectListFragment").commit();
		return super.onCreateView(inflater, container, savedInstanceState);

	}

	public static class MyProjectListFragment extends ListFragment {

		private final static String TAG = "lch";
		private String[] numStrings = new String[] { "one", "two", "three" };

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					inflater.getContext(), android.R.layout.simple_list_item_1,
					numStrings);
			setListAdapter(adapter);
			return super.onCreateView(inflater, container, savedInstanceState);

			// return inflater
			// .inflate(R.layout.frag_my_project_list, container, false);

		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			// TODO Auto-generated method stub
			// super.onListItemClick(l, v, position, id);
			Toast.makeText(getActivity(), v.toString(), Toast.LENGTH_LONG)
					.show();

		}

	}

}
