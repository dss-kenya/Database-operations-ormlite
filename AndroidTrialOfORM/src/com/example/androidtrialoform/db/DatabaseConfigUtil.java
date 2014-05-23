package com.example.androidtrialoform.db;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

/**
 * Writes the orm config into the ormlite_config file created
 * and stored in the raw folder
 * @author user
 *
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil{
	public static void main(String[] args) throws Exception {
		writeConfigFile("ormlite_config.txt");
	}
}
