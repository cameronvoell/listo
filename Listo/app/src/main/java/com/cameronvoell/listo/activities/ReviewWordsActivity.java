package com.cameronvoell.listo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.cameronvoell.listo.R;
import com.cameronvoell.listo.fragments.ReviewCardAnswerFragment;
import com.cameronvoell.listo.fragments.ReviewCardPromptFragment;
import com.cameronvoell.listo.fragments.ReviewWordsConfiguratorFragment;

/**
 * Created by cameronvoell on 1/17/17.
 */
public class ReviewWordsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review_words);

		getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,
				new ReviewWordsConfiguratorFragment()).commit();

	}

	public void updateReviewWordsPrioritization(View view) {

	}

	public void launchReviewSession(View view) {
		getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,
				new ReviewCardPromptFragment()).commit();
	}

	public void flipCard(View view) {
		getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,
				new ReviewCardAnswerFragment()).commit();
	}

	public void correctCard(View view) {
	}

	public void incorrectCard(View view) {
	}
}
