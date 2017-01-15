package com.cameronvoell.listo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cameronvoell.listo.R;

/**
 * Created by cameronvoell on 1/15/17.
 */

public class ManualWordCaptureFragment extends Fragment {

	public static ManualWordCaptureFragment newInstance() {
		ManualWordCaptureFragment fragment = new ManualWordCaptureFragment();
		return fragment;
	}

	public ManualWordCaptureFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_manual_word_capture, container, false);
		return v;
	}
}
