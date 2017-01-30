package com.cameronvoell.listo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.model.VerbPracticeSession;


public class VerbPracticeConfiguratorFragment extends Fragment {

	private static final String PREF_NUM_PRACTICE_SENTENCES = "PREF_NUM_PRACTICE_SENTENCES";

	DatabaseHelper mDatabaseHelper;
	private EditText mNumSentences;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_verb_practice_configurator, container, false);

		mNumSentences = (EditText) v.findViewById(R.id.numSentences);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mDatabaseHelper = new DatabaseHelper(getActivity());


	}

	public VerbPracticeSession createVerbPracticeSession() {
		int numSentences = Integer.parseInt(mNumSentences.getText().toString());

		return new VerbPracticeSession(numSentences, mDatabaseHelper,
				VerbPracticeSession.TENSE_PRESENTE, getActivity());
	}
}
