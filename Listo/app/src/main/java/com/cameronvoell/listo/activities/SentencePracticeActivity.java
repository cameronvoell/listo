package com.cameronvoell.listo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.fragments.ReviewCardPromptFragment;
import com.cameronvoell.listo.fragments.SentencePracticeConfiguratorFragment;
import com.cameronvoell.listo.fragments.SentencePracticeFragment;
import com.cameronvoell.listo.model.SentencePracticeSession;

public class SentencePracticeActivity extends AppCompatActivity {

	SentencePracticeConfiguratorFragment mConfiguratorFragment;
	SentencePracticeFragment mCurrentSentencePracticeFragment;
	SentencePracticeSession mSession;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sentence_practice);

		mConfiguratorFragment = new SentencePracticeConfiguratorFragment();
		getFragmentManager().beginTransaction().add(R.id.fragmentContainer,
				mConfiguratorFragment).commit();
	}

	public void launchSentencePractice(View view) {
		mSession = mConfiguratorFragment.createSentencePracticeSession();

		if (mSession.isFinished()) {
			Toast.makeText(getApplicationContext(), "no words to review!", Toast.LENGTH_SHORT).show();
			return;
		}

		mCurrentSentencePracticeFragment = new SentencePracticeFragment();
		Bundle args = new Bundle();
		args.putParcelable(SentencePracticeFragment.KEY_SAVED_WORD, mSession.getCurrentSavedWord());
		mCurrentSentencePracticeFragment.setArguments(args);

		getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mCurrentSentencePracticeFragment).commit();
	}

	public void submitSentence(View view) {
		String sentenceContent = mCurrentSentencePracticeFragment.getSentenceText();
		int wordLocation = mCurrentSentencePracticeFragment.getWordLocation();
		mSession.saveSentence(sentenceContent, wordLocation);
		mSession.moveToNextCard();
		continueSentencePractice();
	}

	public void updateNeedsReview(View view) {
		mSession.setCurrentWordNeedsReview();
		mSession.moveToNextCard();
		continueSentencePractice();
	}

	private void continueSentencePractice() {
		if (!mSession.isFinished()) {
			mCurrentSentencePracticeFragment = new SentencePracticeFragment();
			Bundle args = new Bundle();
			args.putParcelable(SentencePracticeFragment.KEY_SAVED_WORD, mSession.getCurrentSavedWord());
			mCurrentSentencePracticeFragment.setArguments(args);
			getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mCurrentSentencePracticeFragment).commit();
		} else {
			finish();
		}
	}

	public void skip(View view) {
		mSession.skipWordAndMoveToNext();
		continueSentencePractice();;

	}
}
