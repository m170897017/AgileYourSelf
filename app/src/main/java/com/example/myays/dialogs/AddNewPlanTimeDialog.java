package com.example.myays.dialogs;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

public class AddNewPlanTimeDialog extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	private int whichButtonSelected;

	public AddNewPlanTimeDialog(int whichButton) {
		whichButtonSelected = whichButton;
	}

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final Calendar mCalendar = Calendar.getInstance();
		int year = mCalendar.get(Calendar.YEAR);
		int month = mCalendar.get(Calendar.MONTH);
		int day = mCalendar.get(Calendar.DAY_OF_MONTH);

		return new DatePickerDialog(getActivity(), this, year, month, day);

	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		((AddNewPlanDialog) getTargetFragment()).updateTime(year, monthOfYear,
				dayOfMonth, whichButtonSelected);
	}

}
