package com.androidframework.interfaces;

import java.util.List;

import android.support.v4.app.Fragment;

public interface FragmentManagerInterface {

	
	String getTopFragmentName();

	Fragment getFragmentByTag(String tag);

	int getBackStackCount();

	String getFragmentNameAtPosition(int position);

	List<String> getBackStackList();
	
	boolean popBackStack();
	
	boolean popBackStackTill(String tagName, int flag);
}
