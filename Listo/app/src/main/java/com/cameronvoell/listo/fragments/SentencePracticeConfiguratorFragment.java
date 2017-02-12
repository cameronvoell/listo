package com.cameronvoell.listo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.model.SavedWord;
import com.cameronvoell.listo.model.SentencePracticeSession;

import java.util.ArrayList;

/**
 * Created by cameronvoell on 2/11/17.
 */
public class SentencePracticeConfiguratorFragment extends Fragment {

	private static final String PREF_NUM_REV_SENTENCES = "PREF_NUM_REV_SENTENCES";
	EditText mNumSentencesEditText;
	DatabaseHelper mDatabaseHelper;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_practice_sentences_configurator, container, false);
		mNumSentencesEditText = (EditText) v.findViewById(R.id.numSentences);

		mNumSentencesEditText.setText("" + getActivity().getSharedPreferences(
				getString(R.string.listo_shared_preferences),0).getInt(PREF_NUM_REV_SENTENCES, 10));

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mDatabaseHelper = new DatabaseHelper(getActivity());

	}

	public SentencePracticeSession createSentencePracticeSession() {
		int numSentences = Integer.valueOf(mNumSentencesEditText.getText().toString());
		getActivity().getSharedPreferences(getString(R.string.listo_shared_preferences),0).edit().putInt(PREF_NUM_REV_SENTENCES, numSentences).apply();
		ArrayList<SavedWord> wordsToPractice = mDatabaseHelper.getListOfWordsToPractice(numSentences);
		return new SentencePracticeSession(wordsToPractice, mDatabaseHelper);
	}
}
