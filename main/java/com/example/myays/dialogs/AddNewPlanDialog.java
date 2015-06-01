package com.example.myays.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myays.MainActivity;
import com.example.myays.MyProjectListFrag;
import com.example.myays.R;
import com.example.myays.databases.MyDBConfiguration.AddNewPlanEntry;
import com.example.myays.databases.MyDBHelper;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;

public class AddNewPlanDialog extends DialogFragment {

    private static final String TAG = "lch";
    private MyDBHelper newPlanDbHelper;
    private Bundle mBundle;

    protected String nameOfPlanString;
    protected String startTimeString;
    protected String endTimeString;
    protected String pointString;
    protected String priorityString;
    protected String statusString;
    protected String assignToString;
    protected String descriptionString;
    protected String noteString;
    protected String alarmString;


    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater mInflater = getActivity().getLayoutInflater();
        newPlanDbHelper = new MyDBHelper(getActivity(), AddNewPlanEntry.DB_NAME_STRING);

        View mView = mInflater.inflate(R.layout.frag_add_new_plan_dialog, null);
        EditText mNameOfPlanET = (EditText) mView.findViewById(R.id.et_add_new_plan_name);
        EditText mPointsET = (EditText) mView.findViewById(R.id.et_add_new_plan_point);
        EditText mAssignToET = (EditText) mView.findViewById(R.id.et_add_new_plan_assign_to);
        EditText mDescriptionET = (EditText) mView.findViewById(R.id.et_add_new_plan_description);
        EditText mNotesET = (EditText) mView.findViewById(R.id.et_add_new_plan_notes);

        Button mStartTimeButton = (Button) mView
                .findViewById(R.id.btn_add_new_plan_start_time);
        Button mEndTimeButton = (Button) mView
                .findViewById(R.id.btn_add_new_plan_end_time);
        Button mAlarmButton = (Button) mView
                .findViewById(R.id.btn_add_new_plan_alarm);
        Button mPriorityButton = (Button) mView
                .findViewById(R.id.btn_add_new_plan_priority);
        Button mStatusButton = (Button) mView
                .findViewById(R.id.btn_add_new_plan_status);


        mBundle = getArguments();
        if (!mBundle.isEmpty()){
            if (mBundle.containsKey("entryName")) {
                nameOfPlanString = mBundle.getString("entryName");
                mNameOfPlanET.setText(nameOfPlanString);
            }
            if (mBundle.containsKey("entryStartTime")) {
                startTimeString = mBundle.getString("entryStartTime");
                mStartTimeButton.setText(startTimeString);
            }
            if (mBundle.containsKey("entryEndTime")) {
                endTimeString = mBundle.getString("entryEndTime");
                mEndTimeButton.setText(endTimeString);
            }
            if (mBundle.containsKey("entryPoints")) {
                pointString = mBundle.getString("entryPoints");
                mPointsET.setText(pointString);
            }
            if (mBundle.containsKey("entryPriority")) {
                priorityString = mBundle.getString("entryPriority");
                mPriorityButton.setText(priorityString);
            }
            if (mBundle.containsKey("entryStatus")) {
                statusString = mBundle.getString("entryStatus");
                mStatusButton.setText(statusString);
            }
            if (mBundle.containsKey("entryAssignTo")) {
                assignToString = mBundle.getString("entryAssignTo");
                mAssignToET.setText(assignToString);
            }
            if (mBundle.containsKey("entryDescription")) {
                descriptionString = mBundle.getString("entryDescription");
                mDescriptionET.setText(descriptionString);
            }
            if (mBundle.containsKey("entryNostes")) {
                noteString = mBundle.getString("entryNotes");
                mNotesET.setText(noteString);
            }
            if (mBundle.containsKey("entryAlarm")) {
                alarmString = mBundle.getString("entryAlarm");
                mAlarmButton.setText(alarmString);
            }

        }



        // update priority of plan
        mPriorityButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                AddNewPlanPriorityDialog mAddNewPlanPriorityDialog = new AddNewPlanPriorityDialog();
                // Use this line to connect these two fragments, result code is
                // used for onActivityResult()
                mAddNewPlanPriorityDialog.setTargetFragment(
                        AddNewPlanDialog.this, 001);
                mAddNewPlanPriorityDialog.show(getActivity()
                                .getSupportFragmentManager(),
                        "addNewPlanPriorityDialog");

            }
        });

        // update status of plan
        mStatusButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                AddNewPlanStatusDialog mAddNewPlanStatusDialog = new AddNewPlanStatusDialog();
                // Use this line to connect these two fragments, result code is
                // used for onActivityResult()
                mAddNewPlanStatusDialog.setTargetFragment(
                        AddNewPlanDialog.this, 001);
                mAddNewPlanStatusDialog.show(getActivity()
                        .getSupportFragmentManager(), "addNewPlanStatusDialog");

            }
        });

        // get current time and assign to three buttons
        // get current time and assign to three buttons
        String currentTimeString = DateFormat.getDateInstance().format(
                new Date());
        mStartTimeButton.setText(currentTimeString);
        mEndTimeButton.setText(currentTimeString);
        mAlarmButton.setText(currentTimeString);

        // update start time/end time/alarm time
        mStartTimeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                AddNewPlanTimeDialog mAddNewPlanTimeDialog = new AddNewPlanTimeDialog(
                        1);
                mAddNewPlanTimeDialog.setTargetFragment(AddNewPlanDialog.this,
                        001);
                mAddNewPlanTimeDialog.show(getActivity()
                                .getSupportFragmentManager(),
                        "addNewPlanStartTimeDialog");

            }
        });

        mEndTimeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                AddNewPlanTimeDialog mAddNewPlanTimeDialog = new AddNewPlanTimeDialog(
                        2);
                mAddNewPlanTimeDialog.setTargetFragment(AddNewPlanDialog.this,
                        002);
                mAddNewPlanTimeDialog
                        .show(getActivity().getSupportFragmentManager(),
                                "addNewPlanEndTimeDialog");
            }
        });

        mAlarmButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                startActivity(mIntent);

            }
        });

        // set final view and final buttons
        AlertDialog.Builder builder = mBuilder.setView(mView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                updateDB();
                                MainActivity mActivity = (MainActivity) getActivity();
                                mActivity.refreshViewPager();
                            }
                        }

                )
                .

                        setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // TODO Auto-generated method stub

                                    }
                                }

                        );

        return mBuilder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }

    private ContentValues getContentFromPage(){

        EditText mNameEditText = (EditText) getDialog().findViewById(R.id.et_add_new_plan_name);
        nameOfPlanString = mNameEditText.getText().toString();
//		Log.i(TAG, "we get name: "+nameOfPlanString);

        Button mStartTimeButton = (Button) getDialog().findViewById(R.id.btn_add_new_plan_start_time);
        startTimeString = mStartTimeButton.getText().toString();
//		Log.i(TAG, "we get start time: "+startTimeString);

        Button mEndTimeButton = (Button) getDialog().findViewById(R.id.btn_add_new_plan_end_time);
        endTimeString = mEndTimeButton.getText().toString();
//		Log.i(TAG, "we get end time: "+endTimeString);

        EditText mPointEditText = (EditText) getDialog().findViewById(R.id.et_add_new_plan_point);
        pointString = mPointEditText.getText().toString();
//		Log.i(TAG, "we get point: "+pointString);

        Button mPriorityButton = (Button) getDialog().findViewById(R.id.btn_add_new_plan_priority);
        priorityString = mPriorityButton.getText().toString();
//		Log.i(TAG, "we get priority: "+priorityString);

        Button mStatusButton = (Button) getDialog().findViewById(R.id.btn_add_new_plan_status);
        statusString = mStatusButton.getText().toString();
//		Log.i(TAG, "we get status: "+statusString);

        EditText mAssignToEditText = (EditText) getDialog().findViewById(R.id.et_add_new_plan_assign_to);
        assignToString = mAssignToEditText.getText().toString();
//		Log.i(TAG, "we get assign to: "+assignToString);

        EditText mDescriptionEditText = (EditText) getDialog().findViewById(R.id.et_add_new_plan_description);
        descriptionString = mDescriptionEditText.getText().toString();
//		Log.i(TAG, "we get description: "+descriptionString);

        EditText mNotesEditText = (EditText) getDialog().findViewById(R.id.et_add_new_plan_notes);
        noteString = mNotesEditText.getText().toString();
//		Log.i(TAG, "we get notes: "+noteString);

        Button mAlarmButton = (Button) getDialog().findViewById(R.id.btn_add_new_plan_alarm);
        alarmString = mAlarmButton.getText().toString();
//		Log.i(TAG, "we get alarm: "+alarmString);

        ContentValues mContentValues = new ContentValues();

        mContentValues.put(AddNewPlanEntry.COLUMN_NAME_NAME, nameOfPlanString);
        mContentValues.put(AddNewPlanEntry.COLUMN_NAME_START_TIME, startTimeString);
        mContentValues.put(AddNewPlanEntry.COLUMN_NAME_END_TIME, endTimeString);
        mContentValues.put(AddNewPlanEntry.COLUMN_NAME_POINT, pointString);
        mContentValues.put(AddNewPlanEntry.COLUMN_NAME_PRIORITY, priorityString);
        mContentValues.put(AddNewPlanEntry.COLUMN_NAME_STATUS, statusString);
        mContentValues.put(AddNewPlanEntry.COLUMN_NAME_ASSIGN_TO, assignToString);
        mContentValues.put(AddNewPlanEntry.COLUMN_NAME_DESCRIPTION, descriptionString);
        mContentValues.put(AddNewPlanEntry.COLUMN_NAME_NOTES, noteString);
        mContentValues.put(AddNewPlanEntry.COLUMN_NAME_ALARM, alarmString);

        return mContentValues;

    }

    /*
     * Update database with input
     */
    protected void updateDB() {

        ContentValues mContentValues = getContentFromPage();
        SQLiteDatabase newPlanDatabase = newPlanDbHelper.getWritableDatabase();
        newPlanDatabase.insert(AddNewPlanEntry.TABLE_NAME, null, mContentValues);


        /*
        // any record in database can be achieved here
        SQLiteDatabase readNewPlanDatabase = newPlanDbHelper.getReadableDatabase();

        Cursor mCursor = readNewPlanDatabase.query(AddNewPlanEntry.TABLE_NAME, null, null, null, null, null, null);
        mCursor.moveToFirst();
        int total = mCursor.getColumnCount();
        Log.i(TAG, "we have column in total: " + total);
        int totalRows = mCursor.getCount();
        Log.i(TAG, "we have rows: " + totalRows);
        String resString = mCursor.getString(1);
        Log.i(TAG, "we get column 1: " + resString);
        mCursor.close();
*/


    }


    /*
     * To update content of priority button in addNewPlanDialog fragment
     */
    public void updatePriority(String selection) {

        Button mButton = (Button) getDialog().findViewById(
                R.id.btn_add_new_plan_priority);
        mButton.setText(selection);

    }

    /*
     * Callback interface used by addNewPlanStatusDialog to update status of plan
     */
    public void updateStatus(String status) {
        Button mButton = (Button) getDialog().findViewById(
                R.id.btn_add_new_plan_status);
        mButton.setText(status);
    }

    /*
     * update start time/end time/alarm time 1 - Start Time 2 - End Time 3 -
     * Alarm
     */
    public void updateTime(int year, int month, int day, int whichButton) {

        String currentDateString = year + "-" + ++month + "-" + day;

        switch (whichButton) {
            case 1:
                Button mButton = (Button) getDialog().findViewById(
                        R.id.btn_add_new_plan_start_time);
                mButton.setText(currentDateString);
                break;

            case 2:
                Button mEndTimeButton = (Button) getDialog().findViewById(
                        R.id.btn_add_new_plan_end_time);
                mEndTimeButton.setText(currentDateString);
                break;
            case 3:
                Button mAlarmButton = (Button) getDialog().findViewById(
                        R.id.btn_add_new_plan_alarm);
                mAlarmButton.setText(currentDateString);
                break;
            default:
                break;
        }

    }

}
