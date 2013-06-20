package com.androidframework.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SearchView;

import com.androidframework.R;
import com.androidframework.fragments.Fragment_1;

public class LauncherActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		setFragment();
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.launcher, menu);
		SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();
		searchView.setOnClickListener(this);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			moveToPreviousFragment();
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Sets the Fragment1
	 */
	private void setFragment() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		Fragment_1 fragment1 = new Fragment_1();
		fragmentTransaction.add(R.id.launcher_container1, fragment1);
		fragmentTransaction.commitAllowingStateLoss();
	}

	private void moveToPreviousFragment() {
		Log.e("888", "COUNT---->>>"
				+ LauncherActivity.this.getSupportFragmentManager()
						.getBackStackEntryCount());
		if (LauncherActivity.this.getSupportFragmentManager()
				.getBackStackEntryCount() !=0) {
			getSupportFragmentManager().popBackStack();
		} else {
			this.finish();
		}
	}

	@Override
	public void doInitializeViews() {
	}

	@Override
	public void setListeners() {
	}

	@Override
	public void setAdapters() {
		// TODO Auto-generated method stub

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

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
