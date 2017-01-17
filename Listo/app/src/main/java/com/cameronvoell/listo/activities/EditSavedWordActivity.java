package com.cameronvoell.listo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.model.SavedWord;

/**
 * Created by cameronvoell on 1/16/17.
 */
public class EditSavedWordActivity extends AppCompatActivity {

	public static final String SAVED_WORD = "SAVED_WORD";

	private SavedWord mSavedWord;

	private TextView mTitleTextView;
	private Button mWordTypeButton;
	private EditText mSpanishWordEditText;
	private EditText mEnglishDefinitionEditText;
	private TextView mAddedDateTextView;
	private TextView mFrequencyTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_saved_word);

		Intent intent = getIntent();
		mSavedWord = intent.getParcelableExtra(SAVED_WORD);

		mTitleTextView = (TextView) findViewById(R.id.title);
		mWordTypeButton = (Button) findViewById(R.id.wordType);
		mSpanishWordEditText = (EditText) findViewById(R.id.spanish_word);
		mEnglishDefinitionEditText = (EditText) findViewById(R.id.english_definition);
		mAddedDateTextView = (TextView) findViewById(R.id.added_date);
		mFrequencyTextView = (TextView) findViewById(R.id.freq_rank);

		mTitleTextView.setText(mSavedWord.getmWord());
		mWordTypeButton.setText(mSavedWord.getmType());
		mSpanishWordEditText.setText(mSavedWord.getmWord());
		mEnglishDefinitionEditText.setText(mSavedWord.getmEng());
		mAddedDateTextView.setText(mSavedWord.getmAddedDate());
		mFrequencyTextView.setText("" + mSavedWord.getmFrequency());
	}

	public void delete(View view) {
		new DatabaseHelper(getApplicationContext()).deleteSavedWord(mSavedWord.getmWord());
	}
}
