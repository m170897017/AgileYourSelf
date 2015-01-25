package com.example.myays.dialogs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.R.integer;
import android.R.raw;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.myays.R;

public class AddNewPlanDialog extends DialogFragment {

	private static final String TAG = "lch";

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
		LayoutInflater mInflater = getActivity().getLayoutInflater();

		View mView = mInflater.inflate(R.layout.frag_add_new_plan_dialog, null);
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
				AddNewPlanTimeDialog mAddNewPlanTimeDialog = new AddNewPlanTimeDialog(
						3);
				mAddNewPlanTimeDialog.setTargetFragment(AddNewPlanDialog.this,
						003);
				mAddNewPlanTimeDialog.show(getActivity()
						.getSupportFragmentManager(),
						"addNewPlanAlarmTimeDialog");
			}
		});

		// set final view and final buttons
		mBuilder.setView(mView)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});

		return mBuilder.create();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		Log.i(TAG, "ON ACTIVITY CALLED!!!");
		super.onActivityResult(requestCode, resultCode, data);
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
	 * To update status of plan
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

		month++;
		String currentDateString = year + "-" + month + "-" + day;
		// SimpleDateFormat mSimpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd");
		// Date selectionDateString =
		// mSimpleDateFormat.parse(currentDateString);

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
