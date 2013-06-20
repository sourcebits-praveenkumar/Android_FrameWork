package com.androidframework.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper {
	 private static final String DATABASE_NAME = "imageurls.db";
     private static final int DATABASE_VERSION = 2;
     protected static final String TABLE_NAME = "imageurl";
     public static final String COLUMN_IMAGE_NAME = "image_name";
     public static final String AUTHORITY = "com.androidframework.database.ImageUrls";
     public static final Uri CONTENT_URI =  Uri.parse("content://" + AUTHORITY + "/"+TABLE_NAME);
	public static final String CONTENT_TYPE ="com.example.loaders.ImageUrls";
     
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION );
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		 db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                 + BaseColumns._ID + " INTEGER PRIMARY KEY,"
                 + COLUMN_IMAGE_NAME + " TEXT"
                 + ");");
		 
		 insertDataToDatabase(db);

	}

	private void insertDataToDatabase(SQLiteDatabase db) {
		String[] urls = {
				"http://t2.gstatic.com/images?q=tbn:ANd9GcQ4Fd4RGHi6hH5TxHN5XwxcEkr4Iw7lRPjcVjSEejiInDetGrgD",
				"http://hidefwalls.com/wp-content/g/2_scenery_wallpaper_01/flower-scenery-wallpaper-flower-scenery-wallpapers_1024x768_28728.jpg",
				"http://1.bp.blogspot.com/-DAzgAPnOe8c/URGF3Hl4yNI/AAAAAAAAAcY/TdhExTQj7AY/s1600/Nature-Wallpapers-4.jpg",
				"http://t1.gstatic.com/images?q=tbn:ANd9GcSItLaUA6QyxNB3VSLTyrQOlOyDDq4rU_GPvQozcQxPwggNWH95bQ",
				"http://www.hdwallpapersbest.com/wp-content/uploads/2013/05/waterfall-nature-wallpaper.jpg",
				"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSbdV58hU_CluhhDxLPxHn79-BTPUR9UmTW3dVwf-arWNwR4g8aFw",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZw4-kUbWAdXdEq1RFhKGUfc7kQTosIKQpV_ldRNHXfBsmTxc1",
				"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQjN31FwUhVAwLOlMxg6f2PeGCVaIpOVkyjobIO0qxguEz_I_XG",
				"http://www.hdwallpapers3d.com/wp-content/uploads/2013/04/nature-wallpaper-hd-www-wallpapere-eu-punch_bowl_falls_eagle_creek_wilderness_area_california.jpg",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2CUoIXw30Zj-ZEFNAjnvO0wXawtj2H_NumLhJpMDBBDj5kWOQ",
				};

		for (int i = 0; i < urls.length; i++) {
			ContentValues values = new ContentValues();
			values.put(DatabaseHelper.COLUMN_IMAGE_NAME, urls[i]);
			db.insert(DatabaseHelper.TABLE_NAME, null, values);
		}		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 // Kills the table and existing data
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);

        // Recreates the database with a new version
        onCreate(db);
	}
}
