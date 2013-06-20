package com.androidframework.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.androidframework.interfaces.FragmentInterface;

/**
 * This is the base fragment which will be extended by all other fragments.
 * 
 * @author Praveen Kumar
 * 
 */
public abstract class BaseFragment extends Fragment implements
		FragmentInterface {

	/**
	 * Start a new Fragment.
	 * 
	 * @param toFragment
	 *            Fragment to be set.
	 * @param doReplace
	 *            value represents whether the fragment be replaced.
	 * @param doAddToBackStack
	 *            value represents whether the fragment be replaced.
	 * @param containerId
	 *            id of the layout where the toFragment should be added or
	 *            replaced.
	 * @param trasnsactionTag
	 *            tag for the transaction
	 */
	public void setFragment(Fragment toFragment, boolean doReplace,
			boolean doAddToBackStack, int containerId, String transactionTag,
			int[] animationArray, int FragmentTransition) {
		// Get the Fragment Manager.
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		// Get the Fragment Transaction.
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		// Add animation for the transaction.
		if (animationArray != null && animationArray.length > 0) {
			fragmentTransaction.setCustomAnimations(animationArray[0],
					animationArray[1], animationArray[2], animationArray[3]);
		}
		// Check doReplace and add or replace accordingly.
		if (doReplace) {
			fragmentTransaction.replace(containerId, toFragment);
		} else {
			fragmentTransaction.add(containerId, toFragment);
		}
		// Add transition for the transaction.
		fragmentTransaction.setTransition(FragmentTransition);

		// Check doAddToBackStack and add or not add to the backStack
		// accordingly.
		if (doAddToBackStack) {
			if (transactionTag != null) {
				fragmentTransaction.addToBackStack(transactionTag);
			} else {
				fragmentTransaction.addToBackStack(null);
			}
		}
		// Commit the Transaction.
		fragmentTransaction.commitAllowingStateLoss();

	}

}
