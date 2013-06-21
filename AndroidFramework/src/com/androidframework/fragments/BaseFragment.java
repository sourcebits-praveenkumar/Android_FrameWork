package com.androidframework.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuInflater;

import com.androidframework.interfaces.FragmentInterface;
import com.androidframework.interfaces.OnLoadCompleteListener;

/**
 * This is the base fragment which will be extended by all other fragments.
 * 
 * @author Praveen Kumar
 * 
 */
public abstract class BaseFragment extends Fragment implements
		FragmentInterface, LoaderCallbacks<Cursor> {
	
	private OnLoadCompleteListener mNotifyLoad = null;
	
	private Uri uri;

	public void initLoader(Uri uri, OnLoadCompleteListener notify) {
		this.uri = uri;
		this.mNotifyLoad = notify;
		getLoaderManager().initLoader(0, null, this);
	}
	
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

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		if(uri != null) {
		CursorLoader cl = new CursorLoader(getActivity(),
				uri, null, null, null, null);

		cl.setUpdateThrottle(20000); // update at most every 20 seconds.
		return cl;
		}
		return null;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor newCursor) {
		mNotifyLoad.loadCompleted(newCursor);
	}
	
	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		mNotifyLoad.loadCompleted(null);
	}
}
