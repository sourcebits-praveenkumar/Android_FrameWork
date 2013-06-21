package com.androidframework.activity;

import android.util.Log;

public class CustomClass {

	int i = 0;

	public CustomClass() {
		// TODO Auto-generated constructor stub
	}

	public CustomClass(int i) {
		this.i = i;
	}

	public void printValue() {
		Log.e("888", "Value of i is ---->>>" + i);
	}

}
