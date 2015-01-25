package com.example.myays.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.example.myays.R;

public class AddNewPlanPriorityDialog extends DialogFragment {

	private static final String TAG = "lch";

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
		mBuilder.setTitle(R.string.pick_priority).setItems(
				R.array.add_new_plan_priority_array,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						String[] priorityStrings = getResources().getStringArray(R.array.add_new_plan_priority_array);
						
						((AddNewPlanDialog)getTargetFragment()).updatePriority(priorityStrings[which]);
					}
				});

		return mBuilder.create();
	}

}
