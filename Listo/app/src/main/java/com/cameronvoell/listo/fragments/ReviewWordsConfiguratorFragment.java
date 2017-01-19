package com.cameronvoell.listo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;

/**
 * Created by cameronvoell on 1/18/17.
 */
public class ReviewWordsConfiguratorFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_review_words_configurator, container, false);


		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		//mDbHelper = new DatabaseHelper(getContext());

	}
}
