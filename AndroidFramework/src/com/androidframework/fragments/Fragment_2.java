package com.androidframework.fragments;

import com.androidframework.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class Fragment_2 extends BaseFragment implements OnClickListener {

	LinearLayout contentLayout = null;
	Button back = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contentLayout = (LinearLayout) inflater.inflate(
				R.layout.layout_fragment_2, container, false);
		doInitializeViews();
		setListeners();
		return contentLayout;
	}

	@Override
	public void doInitializeViews() {
		// TODO Auto-generated method stub
		back = (Button) contentLayout.findViewById(R.id.back);
	}

	@Override
	public void setListeners() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
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
		// TODO Auto-generated method stub
		if (back != null) {
			back.setOnClickListener(null);
			back = null;
		}
	}

	@Override
	public void onClick(View v) {
		if (v != null) {
			switch (v.getId()) {
			case R.id.back:
				moveToPreviousFragment();
				break;

			default:
				break;
			}
		}
	}

	private void moveToPreviousFragment() {
		getActivity().getSupportFragmentManager().popBackStack();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		doDisableView();
	}

}
