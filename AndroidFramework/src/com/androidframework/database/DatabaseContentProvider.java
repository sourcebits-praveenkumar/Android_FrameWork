package com.androidframework.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class DatabaseContentProvider extends ContentProvider {

	// Uri matcher to decode incoming URIs.
    private final UriMatcher mUriMatcher;

    // The incoming URI matches the main table URI pattern
    private static final int MAIN = 1;
    
 // Handle to a new DatabaseHelper.
    private DatabaseHelper mOpenHelper;

    public DatabaseContentProvider() {
    	mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(DatabaseHelper.AUTHORITY, DatabaseHelper.TABLE_NAME, MAIN);
    	 
	}
    
	@Override
	public boolean onCreate() {
		  mOpenHelper = new DatabaseHelper(getContext());
          // Assumes that any failures will be reported by a thrown exception.
          return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		 // Constructs a new query builder and sets its table name
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DatabaseHelper.TABLE_NAME);

        switch (mUriMatcher.match(uri)) {
            case MAIN:
                // If the incoming URI is for main table.
                SQLiteDatabase db = mOpenHelper.getReadableDatabase();

                Cursor c = qb.query(db, projection, selection, selectionArgs,
                        null /* no group */, null /* no filter */, sortOrder);

                c.setNotificationUri(getContext().getContentResolver(), uri);
                return c;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }


	}

	@Override
	public String getType(Uri uri) {
		 switch (mUriMatcher.match(uri)) {
             case MAIN:
                 return DatabaseHelper.CONTENT_TYPE;
             default:
                 throw new IllegalArgumentException("Unknown URI " + uri);
         }
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		   if (mUriMatcher.match(uri) != MAIN) {
               // Can only insert into to main URI.
               throw new IllegalArgumentException("Unknown URI " + uri);
           }

           ContentValues values;

           if (initialValues != null) {
               values = new ContentValues(initialValues);
           } else {
               values = new ContentValues();
           }
           SQLiteDatabase db = mOpenHelper.getWritableDatabase();

           long rowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);

           // If the insert succeeded, the row ID exists.
           if (rowId > 0) {
               Uri noteUri = ContentUris.withAppendedId(DatabaseHelper.CONTENT_URI, rowId);
               getContext().getContentResolver().notifyChange(DatabaseHelper.CONTENT_URI, null);
               return noteUri;
           }

           throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        int count;

        switch (mUriMatcher.match(uri)) {
            case MAIN:
                // If URI is main table, delete uses incoming where clause and args.
                count = db.delete(DatabaseHelper.TABLE_NAME, where, whereArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {
		 SQLiteDatabase db = mOpenHelper.getWritableDatabase();
         int count;

         switch (mUriMatcher.match(uri)) {
             case MAIN:
                 // If URI is main table, update uses incoming where clause and args.
                 count = db.update(DatabaseHelper.TABLE_NAME, values, where, whereArgs);
                 break;

             default:
                 throw new IllegalArgumentException("Unknown URI " + uri);
         }

         getContext().getContentResolver().notifyChange(uri, null);

         return count;
	}

}
