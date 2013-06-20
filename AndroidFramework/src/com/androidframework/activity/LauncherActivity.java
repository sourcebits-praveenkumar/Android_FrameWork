package com.androidframework.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.androidframework.R;
import com.androidframework.fragments.Fragment_1;

public class LauncherActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		setFragment();
	}

	/**
	 * Sets the Fragment1
	 */
	private void setFragment() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		Fragment_1 fragment1  = new Fragment_1();
		fragmentTransaction.add(R.id.launcher_container1, fragment1);
		fragmentTransaction.commitAllowingStateLoss();
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
