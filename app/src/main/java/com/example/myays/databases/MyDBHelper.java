package com.example.myays.databases;

import java.util.Hashtable;
import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myays.databases.MyDBConfiguration.AddNewPlanEntry;
import com.example.myays.databases.MyDBConfiguration.RegisEntry;

public class MyDBHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public String mDatabaseName;
	private Map<String, Integer> databaseMap;
	private int selecDB;
	private String cmdCreateDBString;
	private String cmdDeleteDBString;

	public MyDBHelper(Context context, String DATABASE_NAME) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		mDatabaseName = DATABASE_NAME;

		// create database name map to make this class work for different
		// databases
		Map<String, Integer> databaseMap = new Hashtable<String, Integer>();
		databaseMap.put(RegisEntry.DB_NAME_STRING, 0);
		databaseMap.put(AddNewPlanEntry.DB_NAME_STRING, 1);

		selecDB = databaseMap.get(mDatabaseName);
		switch (selecDB) {
		case 0:
			cmdCreateDBString = RegisEntry.SQL_CREATE_ENTRIES;
			cmdDeleteDBString = RegisEntry.SQL_DELETE_ENTRIES;
			break;
		case 1:
			cmdCreateDBString = AddNewPlanEntry.SQL_CREATE_ENTRIES;
			cmdDeleteDBString = AddNewPlanEntry.SQL_DELETE_ENTRIES;
			break;
		default:
			break;
		}

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(cmdCreateDBString);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL(cmdDeleteDBString);
		onCreate(db);
	}



}
