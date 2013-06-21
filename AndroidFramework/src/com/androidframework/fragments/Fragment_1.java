package com.androidframework.fragments;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.androidframework.R;
import com.androidframework.activity.SubActivity1;
import com.androidframework.constants.AnimationContants;

public class Fragment_1 extends BaseFragment implements OnClickListener {
	LinearLayout contentLayout = null;
	Button nextFragment = null;
	Button nextActivity = null;
	Button toggleActionBar = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		contentLayout = (LinearLayout) inflater.inflate(
				R.layout.layout_fragment_1, container, false);
		doInitializeViews();
		setListeners();

		if (!getActivity().getActionBar().isShowing()) {
			getActivity().getActionBar().show();
		}
		return contentLayout;
	}
	
	

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
	}

	@Override
	public void doInitializeViews() {
		// TODO Auto-generated method stub
		nextFragment = (Button) contentLayout.findViewById(R.id.nextFragment);
		nextActivity = (Button) contentLayout.findViewById(R.id.nextActivity);
		toggleActionBar = (Button) contentLayout
				.findViewById(R.id.toggleActionBar);
	}

	@Override
	public void setListeners() {
		// TODO Auto-generated method stub
		nextFragment.setOnClickListener(this);
		nextActivity.setOnClickListener(this);
		toggleActionBar.setOnClickListener(this);
	}

	@Override
	public void setAdapters() {

	}

	@Override
	public void setValues() {

	}

	@Override
	public void setFonts() {

	}

	@Override
	public void doDisableView() {
		if (nextFragment != null) {
			nextFragment.setOnClickListener(null);
			nextFragment = null;
		}
		if (nextActivity != null) {
			nextActivity.setOnClickListener(null);
			nextActivity = null;
		}
		if (toggleActionBar != null) {
			toggleActionBar.setOnClickListener(null);
			toggleActionBar = null;
		}
	}

	@Override
	public void onClick(View v) {
		if (v != null) {
			switch (v.getId()) {
			case R.id.nextFragment:
				setFragment();
				break;
			case R.id.nextActivity:
				setActivity();
				break;
			case R.id.toggleActionBar:
				toggleActionBar();
				break;

			default:
				break;
			}
		}
	}

	private void setActivity() {
		startActivity(new Intent(getActivity(), SubActivity1.class));
		getActivity().overridePendingTransition(R.anim.slide_in_right,
				R.anim.fade_out_slow);
	}

	private void toggleActionBar() {
		boolean isVisible = getActivity().getActionBar().isShowing();
		if (isVisible) {
			getActivity().getActionBar().hide();
		} else {
			getActivity().getActionBar().show();
		}
	}

	private void setFragment() {
		/*int anim[] = { R.anim.slide_in_right, R.anim.slide_out_left,
				R.anim.slide_in_left, R.anim.slide_out_right };*/
		int anim[] = { AnimationContants.SLIDE_IN_RIGHT,  AnimationContants.SLIDE_OUT_LEFT,
				 AnimationContants.SLIDE_IN_LEFT,  AnimationContants.SLIDE_OUT_RIGHT};
		setFragment(new Fragment_2(), true, true, R.id.launcher_container1,
				"Fragment2", anim, -1);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		doDisableView();
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}
