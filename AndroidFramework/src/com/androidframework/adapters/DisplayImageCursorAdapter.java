package com.androidframework.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidframework.R;
import com.androidquery.AQuery;

public class DisplayImageCursorAdapter extends CursorAdapter {
	/*
	 * public DisplayImageCursorAdapter(Context context, Cursor c) {
	 * super(context, c); // TODO Auto-generated constructor stub }
	 */

	/*
	 * public DisplayImageCursorAdapter() { // TODO Auto-generated constructor
	 * stub }
	 */

	private static final int SCREENSHOT_SCALE_HEIGHT = 150;
	private Bitmap preset;

	public DisplayImageCursorAdapter(Context context, Cursor c,
			boolean autoRequery) {
		super(context, c, autoRequery);
		preset = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.ic_launcher);
	}

	@Override
	public void bindView(View view, Context arg1, Cursor cursor) {
		ImageView image = (ImageView) view.findViewById(R.id.image);
		image.setImageResource(R.drawable.ic_launcher);
		String url = cursor.getString(1);
		Log.d("URL", " url is " + url);
		final AQuery aq = new AQuery(view);
		boolean memCache = true;
		boolean fileCache = true;
		aq.id(image).image(url, memCache, fileCache, SCREENSHOT_SCALE_HEIGHT,
				R.drawable.ic_launcher, preset, 0);
		/*
		 * url, memCache, fileCache, SCrEENSHOT_SCALE_HEIGHT);
		 */

	}

	@Override
	public View newView(Context context, Cursor arg1, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View retView = inflater
				.inflate(R.layout.single_row_item, parent, false);
		return retView;
	}

}
