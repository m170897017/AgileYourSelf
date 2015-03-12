package com.example.myays.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.example.myays.R;

public class AddNewPlanStatusDialog extends DialogFragment {

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
		mBuilder.setTitle(R.string.pick_status).setItems(R.array.add_new_plan_status_array, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String[] statusStrings = getResources().getStringArray(R.array.add_new_plan_status_array);
				((AddNewPlanDialog)getTargetFragment()).updateStatus(statusStrings[which]);
				
			}
		});
		
		return mBuilder.create();
		
		
	}
	
	
	

}
