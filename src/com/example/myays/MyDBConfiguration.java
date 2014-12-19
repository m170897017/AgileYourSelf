package com.example.myays;

import android.provider.BaseColumns;

public final class MyDBConfiguration {

	public void MyProjectListFrag() {
	};

	public static abstract class RegisEntry implements BaseColumns {

		public static final String TABLE_NAME = "regisinfo";
		public static final String COLUMN_NAME_ENTRY_ID = "id";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_CONTENT = "content";
		public static final String TEXT_TYPE = " TEXT";
		public static final String COMA_SEP = ",";

		public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
				+ TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY,"
//				+ COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMA_SEP
				+ COLUMN_NAME_TITLE + TEXT_TYPE + COMA_SEP
				+ COLUMN_NAME_CONTENT + TEXT_TYPE + " )";

		public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
				+ TABLE_NAME;

	}

}
