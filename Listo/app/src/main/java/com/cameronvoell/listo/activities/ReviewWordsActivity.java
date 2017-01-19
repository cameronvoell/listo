package com.cameronvoell.listo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.cameronvoell.listo.R;
import com.cameronvoell.listo.fragments.ReviewCardAnswerFragment;
import com.cameronvoell.listo.fragments.ReviewCardPromptFragment;
import com.cameronvoell.listo.fragments.ReviewWordsConfiguratorFragment;
import com.cameronvoell.listo.model.ReviewWordsSession;

/**
 * Created by cameronvoell on 1/17/17.
 */
public class ReviewWordsActivity extends AppCompatActivity {

	ReviewWordsConfiguratorFragment mConfiguratorFragment;
	ReviewWordsSession mSession;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review_words);


		mConfiguratorFragment = new ReviewWordsConfiguratorFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,
				mConfiguratorFragment).commit();

	}

	public void updateReviewWordsPrioritization(View view) {

	}

	public void launchReviewSession(View view) {
		mSession = mConfiguratorFragment.createReviewWordsSession();


		ReviewCardPromptFragment cardPromptFragment = new ReviewCardPromptFragment();
		Bundle args = new Bundle();
		args.putParcelable(ReviewCardPromptFragment.KEY_SAVED_WORD, mSession.getCurrentSavedWord());
		cardPromptFragment.setArguments(args);

		getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, cardPromptFragment).commit();
	}

	public void flipCard(View view) {
		ReviewCardAnswerFragment cardAnswerFragment = new ReviewCardAnswerFragment();
		Bundle args = new Bundle();
		args.putParcelable(ReviewCardAnswerFragment.KEY_SAVED_WORD, mSession.getCurrentSavedWord());
		cardAnswerFragment.setArguments(args);

		getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,
				cardAnswerFragment).commit();
	}

	public void correctCard(View view) {
		mSession.setCurrentCardCorrect();
		continueReviewSession();
	}

	public void incorrectCard(View view) {
		mSession.setCurrentCardIncorrect();
		continueReviewSession();
	}

	public void markWordMemorized(View view) {
		mSession.setCurrentCardMemorized();
		continueReviewSession();
	}

	private void continueReviewSession() {
		if (!mSession.isFinished()) {
			ReviewCardPromptFragment cardPromptFragment = new ReviewCardPromptFragment();
			Bundle args = new Bundle();
			args.putParcelable(ReviewCardPromptFragment.KEY_SAVED_WORD, mSession.getCurrentSavedWord());
			cardPromptFragment.setArguments(args);
			getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, cardPromptFragment).commit();
		} else {
			finish();
		}
	}
}
