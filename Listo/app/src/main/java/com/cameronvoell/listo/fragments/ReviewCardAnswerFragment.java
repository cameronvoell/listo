package com.cameronvoell.listo.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.model.SavedWord;
import com.cameronvoell.listo.util.ColorUtil;

/**
 * Created by cameronvoell on 1/18/17.
 */
public class ReviewCardAnswerFragment extends Fragment {

	public static final String KEY_SAVED_WORD = "saved_word";

	SavedWord mSavedWord;

	TextView mAnswerTextView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_review_card_answer, container, false);
		mAnswerTextView = (TextView) v.findViewById(R.id.answerText);

		v.setBackground(new ColorUtil(getActivity()).getLightColorTwoDrawable());
		mAnswerTextView.setTextColor(new ColorUtil(getActivity()).getDarkColorResource());

		Bundle args = getArguments();
		mSavedWord = args.getParcelable(KEY_SAVED_WORD);

		mAnswerTextView.setText(mSavedWord.getmWord());

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

}
