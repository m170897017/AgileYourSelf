package com.example.myays;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.example.myays.databases.MyDBConfiguration;
import com.example.myays.databases.MyDBHelper;

//TODO use listfragment instead of fragment, it should work
public class MyProjectListFrag extends Fragment {

    private final static String TAG = "lch";

    private MyProjectListFragment myProjectListFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

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

        mFragmentManager = getFragmentManager();
        myProjectListFragment = new MyProjectListFragment();
        mFragmentManager
                .beginTransaction()
                .replace(R.id.container_for_my_pro_list, myProjectListFragment,
                        "myProjectListFragment").commit();

    }

    public static class MyProjectListFragment extends ListFragment {

        private final static String TAG = "lch";
        private ArrayList<String> descriptions = new ArrayList<String>();
        private ArrayList<String> startTimes = new ArrayList<String>();
        private ArrayList<String> endTimes = new ArrayList<String>();
        private ArrayList<String> priorities = new ArrayList<String>();
        private ArrayList<String> percentages = new ArrayList<String>();
        private List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();

        private final String[] from = {"description", "percentage", "priority"};
        private final int[] to = {R.id.description, R.id.percentage, R.id.priority};
        private int[] logos = new int[]{R.drawable.ic_action_cloud,
                R.drawable.ic_action_dock, R.drawable.ic_launcher};


        private MyDBHelper myDBHelper;
        private SQLiteDatabase mDatabase;
        private Cursor mCursor;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            resultList = getInfoFromPlanDB();
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), resultList,
                    R.layout.frag_my_project_list_1, from, to);
            setListAdapter(adapter);
            return super.onCreateView(inflater, container, savedInstanceState);
        }




        /*
        This function is used to check all entries from new plan db and return all the results to show them in the myprojectlist frag.
         */
        private List getInfoFromPlanDB() {

            myDBHelper = new MyDBHelper(getActivity(), MyDBConfiguration.AddNewPlanEntry.DB_NAME_STRING);
            mDatabase = myDBHelper.getReadableDatabase();
            mCursor = mDatabase.query(MyDBConfiguration.AddNewPlanEntry.TABLE_NAME, null, null, null, null, null, null);
            mCursor.moveToFirst();
            while (!mCursor.isAfterLast()) {
                descriptions.add(mCursor.getString(MyDBConfiguration.AddNewPlanEntry.COLUMN_NAME_DESCRIPTION_ID));
                startTimes.add(mCursor.getString(MyDBConfiguration.AddNewPlanEntry.COLUMN_NAME_START_TIME_ID));
                endTimes.add(mCursor.getString(MyDBConfiguration.AddNewPlanEntry.COLUMN_NAME_END_TIME_ID));
                priorities.add(mCursor.getString(MyDBConfiguration.AddNewPlanEntry.COLUMN_NAME_PRIORITY_ID));

                mCursor.moveToNext();
            }
            Log.i(TAG, "this is description: " + descriptions + "\nthis is start time: " + startTimes + "\nthis is endtime: " + endTimes + "\nthis is priority: " + priorities);

            int entryNum = mCursor.getCount();
            List<HashMap<String, String>> mList = new ArrayList<HashMap<String, String>>();

            DateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < entryNum; i++) {
                Date startTime = null;
                Date endTime = null;
                int mPercentage;
                try {
                    startTime = mDateFormat.parse(startTimes.get(i));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    endTime = mDateFormat.parse(endTimes.get(i));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long diffSE = endTime.getTime() - startTime.getTime();
                Date currentTime = new Date();
                Long diffCS = currentTime.getTime() - startTime.getTime();
                if (currentTime.before(startTime) || endTime.before(startTime)) {
                    percentages.add("0%");
                } else if (currentTime.after(endTime)) {
                    percentages.add("100%");
                } else {
                    mPercentage = (int) (diffCS * 100 / diffSE);
                    percentages.add(mPercentage + "%");
                }
            }

            for (int i = 0; i < entryNum; i++) {

                HashMap<String, String> mHashMap = new HashMap<String, String>();
                mHashMap.put("description", descriptions.get(i));
                mHashMap.put("percentage", percentages.get(i));
                mHashMap.put("priority", priorities.get(i));
                mList.add(mHashMap);
            }

            return mList;

        }


        /*
         * register should be put here, after view created
         *
         * @see
         * android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
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
