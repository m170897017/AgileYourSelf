package com.example.myays;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myays.MyDBConfiguration.RegisEntry;

public class MyDBHelper extends SQLiteOpenHelper {
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "RegisInfo.db";
	
	public MyDBHelper(Context context){
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(RegisEntry.SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL(RegisEntry.SQL_DELETE_ENTRIES);
		onCreate(db);
	}

}
