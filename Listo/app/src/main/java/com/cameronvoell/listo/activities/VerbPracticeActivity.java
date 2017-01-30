package com.cameronvoell.listo.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.fragments.ReviewCardPromptFragment;
import com.cameronvoell.listo.fragments.VerbPracticeConfiguratorFragment;
import com.cameronvoell.listo.fragments.VerbPromptFragment;
import com.cameronvoell.listo.model.VerbPracticeSession;

public class VerbPracticeActivity extends AppCompatActivity {

	VerbPracticeConfiguratorFragment mConfiguratorFragment;
	VerbPromptFragment mVerbPromptFragment;
	VerbPracticeSession mSession;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verb_practice);

		mConfiguratorFragment = new VerbPracticeConfiguratorFragment();
		getFragmentManager().beginTransaction().add(R.id.fragmentContainer,
				mConfiguratorFragment).commit();

	}

	public void selectTenses(View view) {

	}

	public void launchVerbPractice(View view) {
		mSession = mConfiguratorFragment.createVerbPracticeSession();

		mVerbPromptFragment = new VerbPromptFragment();
		Bundle args = new Bundle();
		args.putParcelable(VerbPromptFragment.KEY_VERB_PROMPT, mSession.getCurrentVerbPrompt());
		mVerbPromptFragment.setArguments(args);

		getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mVerbPromptFragment).commit();
	}

	public void submitSentence(View view) {
		boolean succeeded = mVerbPromptFragment.submitSentence();
		if (succeeded) {
			mSession.next();
			if (!mSession.getIsFinished()) {
				mVerbPromptFragment = new VerbPromptFragment();
				Bundle args = new Bundle();
				args.putParcelable(VerbPromptFragment.KEY_VERB_PROMPT, mSession.getCurrentVerbPrompt());
				mVerbPromptFragment.setArguments(args);
				getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mVerbPromptFragment).commit();
			} else {
				finish();
			}
		}
	}
}
