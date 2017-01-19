package com.cameronvoell.listo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cameronvoell.listo.R;

/**
 * Created by cameronvoell on 1/18/17.
 */
public class ReviewCardAnswerFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_review_card_answer, container, false);


		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		//mDbHelper = new DatabaseHelper(getContext());

	}

}
