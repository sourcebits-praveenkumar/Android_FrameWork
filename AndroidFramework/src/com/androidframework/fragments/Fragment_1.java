package com.androidframework.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.androidframework.R;

public class Fragment_1 extends BaseFragment implements OnClickListener {
	LinearLayout contentLayout = null;
	Button next = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		contentLayout = (LinearLayout) inflater.inflate(
				R.layout.layout_fragment_1, container, false);
		doInitializeViews();
		setListeners();
		return contentLayout;
	}

	@Override
	public void doInitializeViews() {
		// TODO Auto-generated method stub
		next = (Button) contentLayout.findViewById(R.id.nextFragment);
	}

	@Override
	public void setListeners() {
		// TODO Auto-generated method stub
		next.setOnClickListener(this);
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
		if (next != null) {
			next.setOnClickListener(null);
			next = null;
		}
	}

	@Override
	public void onClick(View v) {
		if (v != null) {
			switch (v.getId()) {
			case R.id.nextFragment:
				setFragment();
				break;

			default:
				break;
			}
		}
	}

	private void setFragment() {
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		Fragment_2 fragment1 = new Fragment_2();
		fragmentTransaction.replace(R.id.launcher_container1, fragment1);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commitAllowingStateLoss();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		doDisableView();
	}

}
