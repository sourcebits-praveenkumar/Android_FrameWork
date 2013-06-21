package com.androidframework.fragments;

import java.util.Hashtable;
import java.util.Map.Entry;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidframework.activity.CustomClass;
import com.androidframework.interfaces.FragmentInterface;
import com.androidframework.interfaces.OnLoadCompleteListener;

/**
 * This is the base fragment which will be extended by all other fragments.
 * 
 * @author Praveen Kumar.
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
	

	// Handler object to send use runnable object and handle messages.
	protected Handler handler = null;

	// Parent view group of the fragment.
	protected ViewGroup contentLayout = null;

	// Runnable List contains list of API being called.
	protected Hashtable<String, CustomClass> runnableList = null;

	// Boolean indicates whether the runnable can be cancelled when the
	// fragment goes to onDestroyView().
	protected boolean doCancelRunnables = false;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	/**
	 * Initialize the necessary objects here.
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		handler = new Handler();
		runnableList = new Hashtable<String, CustomClass>();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		// Cancel list of API call runnable from the runnable list.
		if(doCancelRunnables && runnableList!=null){
			for (Entry<String, CustomClass> entry : runnableList.entrySet()) {
				CustomClass customClassObClass = entry.getValue();
				customClassObClass.printValue();
			}
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}

	public Handler getHandler() {
		return handler;
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
