package com.androidframework.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.androidframework.R;
import com.androidframework.interfaces.ActivityInterface;

/**
 * This activity is the base activity, which will be extended by other
 * activities. Any generic functionalities will be defined here.
 * 
 * @author Praveen Kumar
 * 
 *  */
public abstract class BaseActivity extends FragmentActivity implements ActivityInterface{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.base, menu);
		return true;
	}

}
