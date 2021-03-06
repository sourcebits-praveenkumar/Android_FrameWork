package com.androidframework.interfaces;

import android.support.v4.app.Fragment;

public interface FragmentInterface {

	void doInitializeViews(); // initialize the view of the fragment.

	void setListeners();// Set Listeners for the views of the fragment.

	void setAdapters();// Set Adapters for the views of the fragment.

	void setValues();// Set Values for the views of the fragment.

	void setFonts();// Set Fonts for the views of the fragment.

	void doDisableView();// Disable or clear the views of the fragment. Opposite
							// of doInitializeViews().
	
	//void setFragment(Fragment toFragment,Boolean doReplace,Boolean doAddToBackStack,int containerId,String trasnsactionTag);
}
