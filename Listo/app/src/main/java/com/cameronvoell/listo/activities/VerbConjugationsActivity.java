package com.cameronvoell.listo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.VerbConjugationRow;
import com.cameronvoell.listo.views.VerbConjugationsView;

/**
 * Created by cameronvoell on 1/28/17.
 */
public class VerbConjugationsActivity extends AppCompatActivity {

	public static final String VERB_CONJUGATIONS_DATA = "VERB_CONJUGATIONS_DATA";

	VerbConjugationsView mVerbConjugationsView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verb_conjugations);

		mVerbConjugationsView = (VerbConjugationsView) findViewById(R.id.verbConjugationInfo);

		Intent intent = getIntent();
		VerbConjugationRow verbConjugationData = intent.getParcelableExtra(VERB_CONJUGATIONS_DATA);

		mVerbConjugationsView.setVerbConjugations(verbConjugationData);
	}

}
