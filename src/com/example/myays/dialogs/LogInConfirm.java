package com.example.myays.dialogs;

import android.R.anim;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class LogInConfirm extends DialogFragment {

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());

		String inforString = getArguments().getString("info");

		mBuilder.setMessage(inforString).setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						// invoke onBackPressed to return to parent activity,
						// just
						// like pressed back button
						if (getArguments().getString("back") != null
								&& getArguments().getString("back")
										.equalsIgnoreCase("true")) {
							getActivity().onBackPressed();
						}

					}
				});
		return mBuilder.create();

	}

}
