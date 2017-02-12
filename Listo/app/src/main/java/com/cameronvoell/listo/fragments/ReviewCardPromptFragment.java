package com.cameronvoell.listo.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.model.SavedWord;
import com.cameronvoell.listo.util.ColorUtil;

/**
 * Created by cameronvoell on 1/18/17.
 */
public class ReviewCardPromptFragment extends Fragment {


	public static final String KEY_SAVED_WORD = "saved_word";

	private SavedWord mSavedWord;

	private TextView mMainPromptTextView;
	private Button mShowSentenceButton;
	private TextView mSentenceHintTextView;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_review_card_prompt, container, false);
		mMainPromptTextView = (TextView) v.findViewById(R.id.mainPrompt);

		v.setBackground(new ColorUtil(getActivity()).getDarkColorDrawable());
		mMainPromptTextView.setTextColor(new ColorUtil(getActivity()).getLightColorTwoResource());

		mShowSentenceButton = (Button) v.findViewById(R.id.showSentence);
		mSentenceHintTextView = (TextView)v.findViewById(R.id.sentenceHint);

		Bundle args = getArguments();
		mSavedWord = args.getParcelable(KEY_SAVED_WORD);
		mMainPromptTextView.setText(mSavedWord.getmEng());

		if (mSavedWord.getmNumSentences() > 0) {
			mShowSentenceButton.setVisibility(View.VISIBLE);
		}
		mShowSentenceButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSentenceHintTextView.setVisibility(View.VISIBLE);
				mShowSentenceButton.setVisibility(View.GONE);
				mSentenceHintTextView.setText(new DatabaseHelper(getActivity()).getSentenceText(mSavedWord).getStylizedSentence());
			}
		});

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

}
