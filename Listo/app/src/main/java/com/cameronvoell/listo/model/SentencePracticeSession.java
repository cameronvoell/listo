package com.cameronvoell.listo.model;

import com.cameronvoell.listo.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by cameronvoell on 2/11/17.
 */
public class SentencePracticeSession {

	private ArrayList<SavedWord> mPraticeWords;
	private int mCurrentWord;
	private DatabaseHelper mDatabaseHelper;

	public SentencePracticeSession(ArrayList<SavedWord> wordsToPractice, DatabaseHelper databaseHelper) {
		mDatabaseHelper = databaseHelper;

		mPraticeWords = wordsToPractice;
		mCurrentWord = 0;
	}

	public boolean isFinished() {
		return mPraticeWords == null || mPraticeWords.size() == 0 || mCurrentWord >= mPraticeWords.size();
	}

	public SavedWord getCurrentSavedWord() {
		return mPraticeWords.get(mCurrentWord);
	}

	public void setCurrentWordNeedsReview() {
		mDatabaseHelper.updateWordReviewData(getCurrentSavedWord(), 2);
	}

	public void moveToNextCard() {
		mCurrentWord++;
	}

	public void saveSentence(String sentenceContent, int wordLocation) {
		mDatabaseHelper.addNewSentence(getCurrentSavedWord(), sentenceContent, wordLocation);
	}

	public void skipWordAndMoveToNext() {
		mDatabaseHelper.skipWordInSentencePractice(getCurrentSavedWord());
		moveToNextCard();
	}
}
