package com.cameronvoell.listo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.model.VerbPracticeSession;
import com.cameronvoell.listo.util.ColorUtil;

import org.w3c.dom.Text;

/**
 * Created by cameronvoell on 1/30/17.
 */
public class VerbPromptFragment extends Fragment{

	public static final String KEY_VERB_PROMPT = "KEY_VERB_PROMPT";

	private VerbPracticeSession.VerbPracticePrompt mVerbPracticePrompt;

	private TextView mMainPromptTextView;
	private TextView mTenseTitleTextView;
	private EditText mSentenceEntry;
	private TextView mConjugationTextView;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_verb_prompt, container, false);
		mMainPromptTextView = (TextView) v.findViewById(R.id.mainPrompt);
		mTenseTitleTextView = (TextView) v.findViewById(R.id.tenseTitle);
		mSentenceEntry = (EditText) v.findViewById(R.id.sentenceEntry);
		mConjugationTextView = (TextView) v.findViewById(R.id.conjugation);

		v.setBackground(new ColorUtil(getActivity()).getDarkColorDrawable());
		mMainPromptTextView.setTextColor(new ColorUtil(getActivity()).getLightColorTwoResource());
		mTenseTitleTextView.setTextColor(new ColorUtil(getActivity()).getLightColorTwoResource());


		Bundle args = getArguments();
		mVerbPracticePrompt = args.getParcelable(KEY_VERB_PROMPT);

		mMainPromptTextView.setText(getSubjectText(mVerbPracticePrompt.getmSubject()) + " "
				+ mVerbPracticePrompt.getmWord().getmWord());
		mTenseTitleTextView.setText(getTenseText(mVerbPracticePrompt.getTense()));
		mConjugationTextView.setText(mVerbPracticePrompt.getCorrectConjugation());

		return v;
	}

	private String getTenseText(int tense) {
		switch (tense) {
			case VerbPracticeSession.TENSE_PRESENTE:
				return "PRESENTE";
			default:
				return "BABOON";
		}
	}

	private String getSubjectText(int subject) {
		switch (subject) {
			case VerbPracticeSession.SUBJECT_YO:
				return "Yo";
			case VerbPracticeSession.SUBJECT_TU:
				return "Tu";
			case VerbPracticeSession.SUBJECT_USTED:
				return "Usted";
			case VerbPracticeSession.SUBJECT_NOSOTROS:
				return "Nosotros";
			case VerbPracticeSession.SUBJECT_VOSOTROS:
				return "Vosotros";
			case VerbPracticeSession.SUBJECT_USTEDES:
				return "Ustedes";
			default:
				return "Cameron Voell";
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	public boolean submitSentence() {
		String sentence = mSentenceEntry.getText().toString();
		if (sentence.contains(mVerbPracticePrompt.getCorrectConjugation())) {
			Toast.makeText(getActivity(), "Success!", Toast.LENGTH_SHORT).show();
			return true;
		} else {
			Toast.makeText(getActivity(), "Incorrect!", Toast.LENGTH_SHORT).show();
			mConjugationTextView.setVisibility(View.VISIBLE);
			return false;
		}
	}
}
