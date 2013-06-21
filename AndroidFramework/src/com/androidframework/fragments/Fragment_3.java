package com.androidframework.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.androidframework.R;
import com.androidframework.constants.AnimationContants;

public class Fragment_3 extends BaseFragment implements OnClickListener{
	LinearLayout contentLayout = null;
	Button back = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contentLayout = (LinearLayout) inflater.inflate(R.layout.layout_fragment_2,container,false );
		return contentLayout;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		doInitializeViews();
		setListeners();
		
	}
	
	@Override
	public void doInitializeViews() {
		back = (Button) contentLayout.findViewById(R.id.back);
		back.setText("BACK 3");
	}

	@Override
	public void setListeners() {
		back.setOnClickListener(this);
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
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			FragmentRemoval();
			break;

		default:
			break;
		}
	}

	private void FragmentRemoval() {
		
		int anim[] = { AnimationContants.SLIDE_IN_RIGHT,  AnimationContants.SLIDE_OUT_LEFT,
				 AnimationContants.SLIDE_IN_LEFT,  AnimationContants.SLIDE_OUT_RIGHT};
		setFragment(new Fragment_4(), true, true, R.id.launcher_container1,
				"Fragment4", anim, -1);
		
	}

}
