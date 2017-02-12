package com.cameronvoell.listo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.model.SavedWord;
import com.cameronvoell.listo.util.ColorUtil;

/**
 * Created by cameronvoell on 2/11/17.
 */
public class SentencePracticeFragment extends Fragment {

	public static final String KEY_SAVED_WORD = "saved_word";

	private SavedWord mSavedWord;
	private int mWordLocation;

	private TextView mMainPromptTextView;
	private Button mShowWordButton;
	private TextView mWordAnswerTextView;
	private EditText mSentenceEditText;
	private Button mDoneButton;
	private Button mSaveButton;
	private TextView mReadySentenceTextView;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_sentence_practice, container, false);
		mMainPromptTextView = (TextView) v.findViewById(R.id.mainPrompt);
		mWordAnswerTextView = (TextView) v.findViewById(R.id.wordAnswer);
		mReadySentenceTextView = (TextView) v.findViewById(R.id.sentenceReadyToSave);
		mShowWordButton = (Button) v.findViewById(R.id.showWord);
		mShowWordButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mWordAnswerTextView.setVisibility(View.VISIBLE);
			}
		});
		mSentenceEditText = (EditText) v.findViewById(R.id.sentenceEntry);
		mDoneButton = (Button) v.findViewById(R.id.doneEditingButton);
		mSaveButton = (Button) v.findViewById(R.id.saveButton);
		mDoneButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String[] words = mSentenceEditText.getText().toString().split(" ");
				int wordLocation = -1;
				for (int i = 0; i < words.length; i++){
					String word = words[i];
					if (word.equalsIgnoreCase(mSavedWord.getmWord())) {
						wordLocation = i;
					} else if (i == words.length - 1 && word.length() > 1
							&& word.substring(0, word.length() - 1).equals(mSavedWord.getmWord())) {
						wordLocation = i;
					}
				}
				if (wordLocation != -1) {
					mWordLocation = wordLocation;
					updateReadyForSave(wordLocation);
				} else {
					updateSelectWordLocation();
				}
			}
		});

		v.setBackground(new ColorUtil(getActivity()).getDarkColorDrawable());
		mMainPromptTextView.setTextColor(new ColorUtil(getActivity()).getLightColorTwoResource());

		Bundle args = getArguments();
		mSavedWord = args.getParcelable(KEY_SAVED_WORD);
		mWordAnswerTextView.setText(mSavedWord.getmWord());
		mMainPromptTextView.setText("Use Spanish word for [" + mSavedWord.getmEng()
									+ "] in a sentence:");

		return v;
	}

	private void updateSelectWordLocation() {
		Toast.makeText(getActivity(), "Word is missing from the sentence!",
				Toast.LENGTH_SHORT).show();
	}

	private void updateReadyForSave(int location) {
		mSentenceEditText.setVisibility(View.GONE);
		mReadySentenceTextView.setVisibility(View.VISIBLE);
		String[] words = mSentenceEditText.getText().toString().split(" ");
		String text = "<font color=#333333>";
		for (int i = 0; i < words.length; i++) {
			if (i == location) {
				text += "</font><font color=#C84C42>" + words[i] + "</font><font color=#333333>";
			} else {
				text += words[i];
			}
			text += " ";
		}
		mReadySentenceTextView.setText(Html.fromHtml(text));
		mDoneButton.setVisibility(View.GONE);
		mSaveButton.setEnabled(true);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	public String getSentenceText() {
		return mSentenceEditText.getText().toString();
	}

	public int getWordLocation() {
		return mWordLocation;
	}
}
