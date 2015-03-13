package com.example.myays.databases;

import android.provider.BaseColumns;

public final class MyDBConfiguration {

	public void MyProjectListFrag() {
	};

	public static abstract class RegisEntry implements BaseColumns {

		public static final String DB_NAME_STRING = "RegisInfo.db";
		public static final String TABLE_NAME = "regisinfo";
		public static final String COLUMN_NAME_ENTRY_ID = "id";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_CONTENT = "content";
		public static final String TEXT_TYPE = " TEXT";
		public static final String COMA_SEP = ",";

		public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
				+ TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY,"
				// + COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMA_SEP
				+ COLUMN_NAME_TITLE + TEXT_TYPE + COMA_SEP
				+ COLUMN_NAME_CONTENT + TEXT_TYPE + " )";

		public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
				+ TABLE_NAME;

	}

	public static abstract class AddNewPlanEntry implements BaseColumns {

		public static final String DB_NAME_STRING = "NewPlanInfo.db";
		public static final String TABLE_NAME = "newplaninfo";
		public static final String COLUMN_NAME_NAME = "name";
        public static final int COLUMN_NAME_NAME_ID = 1;
		public static final String COLUMN_NAME_START_TIME = "starttime";
        public static final int COLUMN_NAME_START_TIME_ID = 2;
		public static final String COLUMN_NAME_END_TIME = "endtime";
        public static final int COLUMN_NAME_END_TIME_ID = 3;
		public static final String COLUMN_NAME_POINT = "point";
        public static final int COLUMN_NAME_POINT_ID = 4;
		public static final String COLUMN_NAME_PRIORITY = "priority";
        public static final int COLUMN_NAME_PRIORITY_ID = 5;
		public static final String COLUMN_NAME_STATUS = "status";
        public static final int COLUMN_NAME_STATUS_ID = 6;
		public static final String COLUMN_NAME_ASSIGN_TO = "assignto";
        public static final int COLUMN_NAME_ASSIGN_TO_ID = 7;
		public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final int COLUMN_NAME_DESCRIPTION_ID = 8;
		public static final String COLUMN_NAME_NOTES = "notes";
        public static final int COLUMN_NAME_NOTES_ID = 9;
		public static final String COLUMN_NAME_ALARM = "alarm";
        public static final int COLUMN_NAME_ALARM_ID = 10;
		public static final String TEXT_TYPE = " TEXT";
		public static final String COMA_SEP = ",";

		public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
				+ TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY,"
				+ COLUMN_NAME_NAME + TEXT_TYPE + COMA_SEP
				+ COLUMN_NAME_START_TIME + TEXT_TYPE + COMA_SEP
				+ COLUMN_NAME_END_TIME + TEXT_TYPE + COMA_SEP
				+ COLUMN_NAME_POINT + TEXT_TYPE + COMA_SEP
				+ COLUMN_NAME_PRIORITY + TEXT_TYPE + COMA_SEP
				+ COLUMN_NAME_STATUS + TEXT_TYPE + COMA_SEP
				+ COLUMN_NAME_ASSIGN_TO + TEXT_TYPE + COMA_SEP
				+ COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMA_SEP
				+ COLUMN_NAME_NOTES + TEXT_TYPE + COMA_SEP 
				+ COLUMN_NAME_ALARM + TEXT_TYPE + " )";

		public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
				+ TABLE_NAME;

	}

}
