package com.example.myays.dialogs;

import java.text.DateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.myays.R;

public class AddNewPlanDialog extends DialogFragment {

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
		Button mPriorityButton = (Button)mView.findViewById(R.id.btn_add_new_plan_priority);
		
		
		//update priority of plan
		mPriorityButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AddNewPlanPriorityDialog mAddNewPlanPriorityDialog = new AddNewPlanPriorityDialog();
				mAddNewPlanPriorityDialog.show(getActivity().getSupportFragmentManager(), "addNewPlanPriorityDialog");
				
			}
		});
		
		
		// get current time and assign to three buttons
		String currentTimeString = DateFormat.getDateInstance().format(
				new Date());
		mStartTimeButton.setText(currentTimeString);
		mEndTimeButton.setText(currentTimeString);
		mAlarmButton.setText(currentTimeString);

		
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
}
