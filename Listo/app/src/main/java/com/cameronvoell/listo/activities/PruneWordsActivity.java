package com.cameronvoell.listo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.model.SavedWord;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class PruneWordsActivity extends AppCompatActivity {

	ArrayAdapter arrayAdapter;
	ArrayList<SavedWord> wordsToPrune;
	DatabaseHelper mDatabaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prune_words);

		SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.swing_container);


		mDatabaseHelper = new DatabaseHelper(getApplicationContext());
		wordsToPrune = mDatabaseHelper.getListOfManuallySavedWordsToPrune(100);
		ArrayList<SavedWord> frequencyWordsForPruning =
				mDatabaseHelper.getListOfFreqWordsToPrune(100 - wordsToPrune.size());
		wordsToPrune.addAll(frequencyWordsForPruning);


		//choose your favorite adapter
		arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, wordsToPrune);

		//set the listener and the adapter
		flingContainer.setAdapter(arrayAdapter);
		flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
			@Override
			public void removeFirstObjectInAdapter() {
				// this is the simplest way to delete an object from the Adapter (/AdapterView)
				Log.d("LIST", "removed object!");
				wordsToPrune.remove(0);
				arrayAdapter.notifyDataSetChanged();
			}

			@Override
			public void onLeftCardExit(Object dataObject) {
				//Do something on the left!
				//You also have access to the original object.
				//If you want to use it just cast it (String) dataObject
				SavedWord word = (SavedWord)dataObject;
				word.setmMemoryStrength(SavedWord.MEMORIZED_NOT_KNOWN);
				mDatabaseHelper.updatePrunedWord(word);
			}

			@Override
			public void onRightCardExit(Object dataObject) {
				SavedWord word = (SavedWord)dataObject;
				word.setmMemoryStrength(SavedWord.MEMORIZED_MEANING_KNOWN);
				mDatabaseHelper.updatePrunedWord(word);
			}

			@Override
			public void onAdapterAboutToEmpty(int itemsInAdapter) {
				// Ask for more data here
				wordsToPrune.addAll(mDatabaseHelper.getListOfFreqWordsToPrune(20));
				arrayAdapter.notifyDataSetChanged();
			}

			@Override
			public void onScroll(float v) {

			}
		});

		// Optionally add an OnItemClickListener
		flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
			@Override
			public void onItemClicked(int itemPosition, Object dataObject) {
				//makeToast(MyActivity.this, "Clicked!");
			}
		});
	}
}
