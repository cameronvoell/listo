package com.cameronvoell.listo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.VerbConjugationRow;

/**
 * Created by cameronvoell on 1/28/17.
 */
public class VerbConjugationsActivity extends AppCompatActivity {

	public static final String VERB_CONJUGATIONS_DATA = "VERB_CONJUGATIONS_DATA";

	TextView mInfinitiveTextView;
	TextView mGerundTextView;
	TextView mParticipleTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verb_conjugations);

		mInfinitiveTextView = (TextView)findViewById(R.id.infinitive);
		mGerundTextView = (TextView)findViewById(R.id.gerund);
		mParticipleTextView = (TextView)findViewById(R.id.participle);

		Intent intent = getIntent();
		VerbConjugationRow verbConjugationData = intent.getParcelableExtra(VERB_CONJUGATIONS_DATA);

		mInfinitiveTextView.setText(verbConjugationData.getInfinitivo());
		mGerundTextView.setText(verbConjugationData.getGerund());
		mParticipleTextView.setText(verbConjugationData.getPastParticiple());
	}

}
