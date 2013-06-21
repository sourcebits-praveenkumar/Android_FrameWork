package com.androidframework.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.androidframework.R;
import com.androidframework.activity.BaseActivity;

public class Fragment_4 extends BaseFragment implements OnClickListener {
	LinearLayout contentLayout = null;
	Button back = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contentLayout = (LinearLayout) inflater.inflate(
				R.layout.layout_fragment_2, container, false);
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
		back.setText("BACK 4");
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
		List<String> list = ((BaseActivity) getActivity()).getBackStackList();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Log.e("888", "ENTRY at " + i + "  ----->>>" + list.get(i));
			}
		}
		
		((BaseActivity)getActivity()).popBackStackTill("Fragment2", FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}

}
