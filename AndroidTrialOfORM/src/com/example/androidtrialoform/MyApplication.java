package com.example.androidtrialoform;

import android.app.Application;

import com.example.androidtrialoform.db.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public class MyApplication extends Application {
	private static DatabaseHelper databaseHelper;
	private static MyApplication mApplication;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = this;
	}
	
	public DatabaseHelper getHelper() {
	    if (databaseHelper == null) {
	        databaseHelper =
	            OpenHelperManager.getHelper(mApplication, DatabaseHelper.class);
	    }
	    return databaseHelper;
	}
	
	public static MyApplication getInstance() {
		return mApplication;
	}
}
