package com.cameronvoell.listo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.model.SavedWord;

/**
 * Created by cameronvoell on 1/18/17.
 */
public class ReviewCardPromptFragment extends Fragment {


	public static final String KEY_SAVED_WORD = "saved_word";

	private SavedWord mSavedWord;

	private TextView mMainPromptTextView;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_review_card_prompt, container, false);
		mMainPromptTextView = (TextView) v.findViewById(R.id.mainPrompt);

		Bundle args = getArguments();
		mSavedWord = args.getParcelable(KEY_SAVED_WORD);
		mMainPromptTextView.setText(mSavedWord.getmEng());

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

}
