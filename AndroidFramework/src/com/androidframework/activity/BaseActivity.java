package com.androidframework.activity;

import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;

import com.androidframework.interfaces.ActivityInterface;
import com.androidframework.interfaces.FragmentManagerInterface;

/**
 * This activity is the base activity, which will be extended by other
 * activities. Any generic functionalities will be defined here.
 * 
 * @author Praveen Kumar.
 * 
 * */
public abstract class BaseActivity extends FragmentActivity implements
		ActivityInterface, FragmentManagerInterface {

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.activity_base);
	}

	/**
	 * Returns Top fragment from fragment back stack.
	 * 
	 * @return
	 */
	public String getTopFragmentName() {
		BackStackEntry backStackEntry = this.getSupportFragmentManager()
				.getBackStackEntryAt(getBackStackCount() - 1);
		if (backStackEntry != null) {
			return backStackEntry.getName();
		}
		return null;
	}

	public Fragment getFragmentByTag(String tag) {
		if (tag != null) {
			return this.getSupportFragmentManager().findFragmentByTag(tag);
		}
		return null;
	}

	/**
	 * Return the fragment back stack entry count.
	 * 
	 * @return
	 */
	public int getBackStackCount() {
		try {
			return this.getSupportFragmentManager().getBackStackEntryCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Returns Fragment name at a particular position in the back stack.
	 * 
	 * @param position
	 *            position of the fragment. Position value of 1st corresponds to
	 *            the 0th position in stack.
	 * @return
	 */
	public String getFragmentNameAtPosition(int position) {
		if (position <= getBackStackCount()) {
			return this.getSupportFragmentManager()
					.getBackStackEntryAt(position - 1).getName();
		}
		return null;
	}

	/**
	 * Returns list of fragment back stack entries.
	 * 
	 * @return
	 */
	public List<String> getBackStackList() {
		List<String> backStackList = new LinkedList<String>();
		int backstackEntryCount = this.getBackStackCount() - 1;
		for (int entryPosition = backstackEntryCount; entryPosition >= 0; entryPosition--) {
			backStackList.add(this.getSupportFragmentManager()
					.getBackStackEntryAt(entryPosition).getName());
		}
		return backStackList;

	}

	/**
	 * Goes one step in the fragment back stack.
	 */
	@Override
	public boolean popBackStack() {
		return this.getSupportFragmentManager().popBackStackImmediate();
	}

	/**
	 * Pop back stack till tag name. if flag is POP_BACK_STACK_INCLUSIVE pop the
	 * fragment including the fragment with tagName. else pop just before the
	 * fragment with tag tagName.
	 * 
	 */
	@Override
	public boolean popBackStackTill(String tagName, int flag) {
		getSupportFragmentManager();
		if (flag != 0 && flag != FragmentManager.POP_BACK_STACK_INCLUSIVE) {
			flag = 0;
		}
		if (tagName != null && tagName.trim().length() > 0) {
			return this.getSupportFragmentManager().popBackStackImmediate(
					tagName, flag);
		}
		return false;

	}

}
