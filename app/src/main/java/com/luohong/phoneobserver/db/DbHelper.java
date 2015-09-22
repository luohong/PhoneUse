package com.luohong.phoneobserver.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "db";

	private static final int DATABASE_VERSION = 1;

	private static DbHelper mDbHelper;
	
	public static DbHelper getInstance(Context context) {
		if (mDbHelper == null) {
			mDbHelper = new DbHelper(context);
		}
		return mDbHelper;
	}

	private DbHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(getTag(), "onCreate");
		createTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(getTag(), "onUpgrade");
//		db.execSQL(CityDb.getDropTableSQL());
		db.execSQL(UseDb.getDropTableSQL());
		
		createTable(db);
	}

	private void createTable(SQLiteDatabase db) {
//		db.execSQL(CityDb.getCreateTableSQL());
		db.execSQL(UseDb.getCreateTableSQL());
	}

	private String getTag() {
		return this.getClass().toString();
	}

}