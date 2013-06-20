package com.androidframework.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

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

}
