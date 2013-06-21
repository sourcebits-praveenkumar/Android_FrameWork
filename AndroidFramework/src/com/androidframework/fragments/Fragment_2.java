package com.androidframework.fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidframework.R;
import com.androidframework.adapters.DisplayImageCursorAdapter;
import com.androidframework.database.DatabaseHelper;
import com.androidframework.interfaces.OnLoadCompleteListener;


public class Fragment_2 extends BaseFragment
		implements
			OnClickListener,
			OnScrollListener {


	LinearLayout contentLayout = null;
	Button back = null;

	private ListView imageList;

	private CursorAdapter mAdapter;

	private int currentVisibleItemCount = 0;

	private RelativeLayout footerView;

	private boolean isLoading = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contentLayout = (LinearLayout) inflater.inflate(
				R.layout.layout_fragment_2, container, false);
		return contentLayout;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//getActivity().getActionBar().hide();
		doInitializeViews();
		setListeners();
		setAdapters();
		
		initLoader(DatabaseHelper.CONTENT_URI, new OnLoadCompleteListener() {
			
			@Override
			public void loadCompleted(Cursor cursor) {
				if(mAdapter != null) {
					mAdapter.swapCursor(cursor);
				}
				if(footerView != null) {
					imageList.removeFooterView(footerView);
				}
			}
		});

		if (getActivity() != null) {
			footerView = new RelativeLayout(getActivity());
		}
		AbsListView.LayoutParams relParams = new AbsListView.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		footerView.setLayoutParams(relParams);
		if (getActivity() != null) {
			ProgressBar p = new ProgressBar(getActivity(), null,
					android.R.attr.progressBarStyleSmall);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			footerView.addView(p, params);
		}
		imageList.addFooterView(footerView);
		imageList.setAdapter(mAdapter);
		// adapter.setFooterView(footerView);
		imageList.removeFooterView(footerView);
		

	}

	@Override
	public void doInitializeViews() {
		// TODO Auto-generated method stub
		back = (Button) contentLayout.findViewById(R.id.back);
		imageList = (ListView) contentLayout.findViewById(R.id.imagelist);
	}

	@Override
	public void setListeners() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
		imageList.setOnScrollListener(this);
	}

	@Override
	public void setAdapters() {
		mAdapter = new DisplayImageCursorAdapter(getActivity(), null, false);
		imageList.setAdapter(mAdapter);

	}

	@Override
	public void setValues() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFonts() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doDisableView() {
		// TODO Auto-generated method stub
		if (back != null) {
			back.setOnClickListener(null);
			back = null;
		}
	}

	@Override
	public void onClick(View v) {
		if (v != null) {
			switch (v.getId()) {
				case R.id.back :
					moveToPreviousFragment();
					break;

				default :
					break;
			}
		}
	}

	private void moveToPreviousFragment() {
		getActivity().getSupportFragmentManager().popBackStack();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		doDisableView();
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (currentVisibleItemCount > 0 && scrollState == SCROLL_STATE_IDLE) {
			if ((imageList.getLastVisiblePosition() + 1) == mAdapter.getCount()) {
				imageList.addFooterView(footerView);
				if (!isLoading) {
					isLoading = true;
					insertNextDataToDb();
				}
				// isListScrolling = true;
			}
		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		currentVisibleItemCount = visibleItemCount;

	}

	private void insertNextDataToDb() {
		if (mAdapter != null && mAdapter.getCount() < urls.length) {
			AsyncTask<Void, Void, Void> populatingTask = new AsyncTask<Void, Void, Void>() {
				@Override
				protected Void doInBackground(Void... params) {
					int finalSize = mAdapter.getCount() + 10;
					if (finalSize > urls.length) {
						finalSize = urls.length;
					}
					for (int i = mAdapter.getCount(); i < finalSize; i++) {
						ContentValues values = new ContentValues();
						values.put(DatabaseHelper.COLUMN_IMAGE_NAME, urls[i]);
						getActivity().getContentResolver().insert(
								DatabaseHelper.CONTENT_URI, values);
					}
					return null;
				}

				@Override
				protected void onPostExecute(Void result) {
					super.onPostExecute(result);
					isLoading = false;
				}
			};
			/*
			 * mPopulatingTask.executeOnExecutor(
			 * AsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
			 */

			populatingTask.execute((Void) null);

			// getLoaderManager().restartLoader(0, null, this);
		} else {
			imageList.removeFooterView(footerView);
			Toast.makeText(getActivity(), "No more results to load",
					Toast.LENGTH_SHORT).show();
		}
	}


	private String[] urls = {
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
			"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdbVjzJtCvcF8dlEv7X_2rjeT-9X7RGwiilQGusepMbtCdetcY",
			"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTHlBhNmewceo9jSe0zlJy5t3Y-DmmM-nsbw2fPodOO2OJBgRmL",
			"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSEly7B60RBjJYhOd-meefkNWzhO-6e8muFnbrjkwgEo29N2cec",
			"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcR2NCcUZ5kQjxO3fg3bkaqa5CgE3Ojg0229yQmuQ2U-_1eJAKAH",
			"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQPYZvWYNs_m2IWDTAFRXo9qb7gpnaDfqyoa-CkIGCpkCKIRGf_",
			"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRL7s-zPCt-HIkD7PN_hHjFZuFLdzQwvNr2eOQ5Q7cUc-X8IptG",
			"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcShU1sfSoIoqmyETuSz-1vE73ZaZu9qvz2XwNTGH0c99D1sHhn2",
			"http://4.bp.blogspot.com/-jPe9qxWmP_U/UEhWnIkbVyI/AAAAAAAAA1s/s3TyxQW4LMg/s1600/Beautiful-lake-wanaka-New-Zealand-full-HD-nature-background-wallpaper-for-laptop-widescreen.jpg",
			"http://1.bp.blogspot.com/-_6L5L7X4Kk0/UEhhP42zUPI/AAAAAAAABBA/fuK14_MkdQM/s1600/lake-forest-waterfall-full-HD-nature-background-wallpaper-for-laptop-widescreen.jpg",
			"http://3.bp.blogspot.com/-1t_JjhWvHnc/UEhgnSCCnbI/AAAAAAAABAQ/oBrZfqJQGgs/s1600/hd-romantic-place-for-lover-nature-background-wallpaper-for-laptop-widescreen.jpg",
			"http://4.bp.blogspot.com/-5vHbSYnqyHU/UEhgqCnRL6I/AAAAAAAABAY/88A-WyYetZU/s1600/hearty-cloud-beautiful-full-HD-nature-background-wallpaper-for-laptop-widescreen.jpg",
			"http://wallpapers.free-review.net/wallpapers/35/Nature_mysterys_1366x768_laptop.jpg",
			"http://www.hdwallpapersarena.com/wp-content/uploads/2012/05/golden-fish-jumps-out-of-laptop-and-diving-in-the-sea-with-cloudly-sky-background-hd-wallpapers-1920-x-1200.jpg",
			"http://3.bp.blogspot.com/-7xVm2WVbQ8s/UEhiNUEPy3I/AAAAAAAABB4/Zk7Olj0sx0o/s1600/national-geographic-full-HD-nature-background-wallpaper-for-laptop-widescreen.jpg",
			"http://1.bp.blogspot.com/-I9aJ_1uSzSw/T61byA-VNcI/AAAAAAAAAfk/PKP4-ppKVUg/s1600/latest_hd_mountain_hd_background_wallpaper_hd-1920x1080.jpg",
			"http://4.bp.blogspot.com/-yOp_zIMrnH4/UEhh-vQx6tI/AAAAAAAABBw/7XxWot2cAVE/s1600/lush-green-grass-mountain-full-HD-nature-background-wallpaper-for-laptop-widescreen.jpg",
			"http://yunii.com/full/180600-desktop-gallery-nature-yellow-daisies-laptop.jpg",
			"http://www.hdwallpapers1080.com/wp-content/uploads/2012/04/Nature-HD-wallpaper.jpg",
			"http://www.walldesktops.com/wp-content/uploads/2013/05/Abstract-hd-wallpapers-1080p-96.jpg",
			"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1CjtSugiGnDq550SjgQ5tlJokFijcC_jBMLhChRB7siRMy4MH",

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
			"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdbVjzJtCvcF8dlEv7X_2rjeT-9X7RGwiilQGusepMbtCdetcY",
			"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTHlBhNmewceo9jSe0zlJy5t3Y-DmmM-nsbw2fPodOO2OJBgRmL",
			"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSEly7B60RBjJYhOd-meefkNWzhO-6e8muFnbrjkwgEo29N2cec",
			"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcR2NCcUZ5kQjxO3fg3bkaqa5CgE3Ojg0229yQmuQ2U-_1eJAKAH",
			"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQPYZvWYNs_m2IWDTAFRXo9qb7gpnaDfqyoa-CkIGCpkCKIRGf_",
			"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRL7s-zPCt-HIkD7PN_hHjFZuFLdzQwvNr2eOQ5Q7cUc-X8IptG",
			"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcShU1sfSoIoqmyETuSz-1vE73ZaZu9qvz2XwNTGH0c99D1sHhn2",
			"http://4.bp.blogspot.com/-jPe9qxWmP_U/UEhWnIkbVyI/AAAAAAAAA1s/s3TyxQW4LMg/s1600/Beautiful-lake-wanaka-New-Zealand-full-HD-nature-background-wallpaper-for-laptop-widescreen.jpg",
			"http://1.bp.blogspot.com/-_6L5L7X4Kk0/UEhhP42zUPI/AAAAAAAABBA/fuK14_MkdQM/s1600/lake-forest-waterfall-full-HD-nature-background-wallpaper-for-laptop-widescreen.jpg",
			"http://3.bp.blogspot.com/-1t_JjhWvHnc/UEhgnSCCnbI/AAAAAAAABAQ/oBrZfqJQGgs/s1600/hd-romantic-place-for-lover-nature-background-wallpaper-for-laptop-widescreen.jpg",
			"http://4.bp.blogspot.com/-5vHbSYnqyHU/UEhgqCnRL6I/AAAAAAAABAY/88A-WyYetZU/s1600/hearty-cloud-beautiful-full-HD-nature-background-wallpaper-for-laptop-widescreen.jpg",
			"http://wallpapers.free-review.net/wallpapers/35/Nature_mysterys_1366x768_laptop.jpg",
			"http://www.hdwallpapersarena.com/wp-content/uploads/2012/05/golden-fish-jumps-out-of-laptop-and-diving-in-the-sea-with-cloudly-sky-background-hd-wallpapers-1920-x-1200.jpg",
			"http://3.bp.blogspot.com/-7xVm2WVbQ8s/UEhiNUEPy3I/AAAAAAAABB4/Zk7Olj0sx0o/s1600/national-geographic-full-HD-nature-background-wallpaper-for-laptop-widescreen.jpg",
			"http://1.bp.blogspot.com/-I9aJ_1uSzSw/T61byA-VNcI/AAAAAAAAAfk/PKP4-ppKVUg/s1600/latest_hd_mountain_hd_background_wallpaper_hd-1920x1080.jpg",
			"http://4.bp.blogspot.com/-yOp_zIMrnH4/UEhh-vQx6tI/AAAAAAAABBw/7XxWot2cAVE/s1600/lush-green-grass-mountain-full-HD-nature-background-wallpaper-for-laptop-widescreen.jpg",
			"http://yunii.com/full/180600-desktop-gallery-nature-yellow-daisies-laptop.jpg",
			"http://www.hdwallpapers1080.com/wp-content/uploads/2012/04/Nature-HD-wallpaper.jpg",
			"http://www.walldesktops.com/wp-content/uploads/2013/05/Abstract-hd-wallpapers-1080p-96.jpg",
			"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1CjtSugiGnDq550SjgQ5tlJokFijcC_jBMLhChRB7siRMy4MH"};

}
