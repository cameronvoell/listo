package com.cameronvoell.listo.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.model.ReviewWordsSession;
import com.cameronvoell.listo.model.SavedWord;

import java.util.ArrayList;

/**
 * Created by cameronvoell on 1/18/17.
 */
public class ReviewWordsConfiguratorFragment extends Fragment {

	private static final String PREF_NUM_REV_WORDS = "PREF_NUM_REV_WORDS";
	EditText mNumWordsEditText;
	DatabaseHelper mDatabaseHelper;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_review_words_configurator, container, false);
		mNumWordsEditText = (EditText) v.findViewById(R.id.numWords);

		mNumWordsEditText.setText("" + getActivity().getSharedPreferences(getString(R.string.listo_shared_preferences),0).getInt(PREF_NUM_REV_WORDS, 10));

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mDatabaseHelper = new DatabaseHelper(getActivity());


	}

	public ReviewWordsSession createReviewWordsSession() {
		int numWords = Integer.valueOf(mNumWordsEditText.getText().toString());
		getActivity().getSharedPreferences(getString(R.string.listo_shared_preferences),0).edit().putInt(PREF_NUM_REV_WORDS, numWords).apply();
		ArrayList<SavedWord> wordsToReview = mDatabaseHelper.getListOfWordsToReview(numWords);
		return new ReviewWordsSession(wordsToReview, mDatabaseHelper);
	}
}
