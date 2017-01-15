package com.cameronvoell.listo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.model.FrequencyWord;

import org.w3c.dom.Text;

/**
 * Created by cameronvoell on 1/15/17.
 */

public class ManualWordCaptureFragment extends Fragment {

	DatabaseHelper mDbHelper;
	FrequencyWord mWord;

	EditText mSpanishWordEntry;
	EditText mEnglishDefinitionEntry;
	TextView mFrequencyTextView;
	TextView mWordTypeTextView;

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
		mSpanishWordEntry = (EditText) v.findViewById(R.id.spanish_word_entry);
		mEnglishDefinitionEntry = (EditText) v.findViewById(R.id.eng_def_entry);
		mFrequencyTextView = (TextView)v.findViewById(R.id.frequency);
		mWordTypeTextView = (TextView)v.findViewById(R.id.word_type);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mDbHelper = new DatabaseHelper(getContext());

	}

	public void captureWord() {
		String spanishWord = mSpanishWordEntry.getText().toString();
		String englishDef = mEnglishDefinitionEntry.getText().toString();
		String wordType = mWordTypeTextView.getText().toString();
		String freqString = mFrequencyTextView.getText().toString();

		if (mDbHelper.contains(spanishWord)) {
			Toast.makeText(getContext(), spanishWord + " is already saved!", Toast.LENGTH_SHORT).show();
			return;
		} else if (TextUtils.isEmpty(spanishWord)){
			Toast.makeText(getContext(), spanishWord + "empty field", Toast.LENGTH_SHORT).show();
			return;
		}


		int freq = 9999;
		if (!TextUtils.isEmpty(freqString)) {
			freq = Integer.parseInt(freqString);
		}
		mDbHelper.saveVocabWord(spanishWord, englishDef, wordType, freq);
		Toast.makeText(getContext(), spanishWord + " saved!", Toast.LENGTH_SHORT).show();
		getActivity().finish();
	}

	public void autoFill() {
		//search Freq Data
		String spanishWord = mSpanishWordEntry.getText().toString();
		String englishWord = mEnglishDefinitionEntry.getText().toString();

		if (spanishWord.length() > englishWord.length()) {
			mWord = mDbHelper.searchForWord(spanishWord);
			if (mWord != null) {
				mWordTypeTextView.setText(mWord.getmType());
				mFrequencyTextView.setText("" + mWord.getmFrequency());
				mEnglishDefinitionEntry.setText(mWord.getmEng().trim());
			}
		} else {
			mWord = mDbHelper.searchForWordWithEnglishDef(englishWord);
			if (mWord != null) {
				mSpanishWordEntry.setText(mWord.getmWord());
				mWordTypeTextView.setText(mWord.getmType());
				mFrequencyTextView.setText("" + mWord.getmFrequency());
				mEnglishDefinitionEntry.setText(mWord.getmEng().trim());
			}
		}

		if (mWord != null) {
			mWordTypeTextView.setText(mWord.getmType());
			mFrequencyTextView.setText("" + mWord.getmFrequency());
			mEnglishDefinitionEntry.setText(mWord.getmEng().trim());
		}
	}
}
