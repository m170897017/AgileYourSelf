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
import android.widget.TextView;

import com.example.myays.R;


public class AddNewPlanDialog extends DialogFragment {

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
		LayoutInflater mInflater = getActivity().getLayoutInflater();
		
		View mView = mInflater.inflate(R.layout.frag_add_new_plan_dialog, null);
		TextView mStartTimeTextView = (TextView)mView.findViewById(R.id.tv_add_new_plan_start_time);
		TextView mEndTimeTextView = (TextView)mView.findViewById(R.id.tv_add_new_plan_end_time);
		String currentTimeString = DateFormat.getDateInstance().format(new Date());
		mStartTimeTextView.setText(currentTimeString);
		mEndTimeTextView.setText(currentTimeString);
		
		
		
		
		
		mBuilder.setView(
				mView)
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
