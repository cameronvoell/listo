package com.cameronvoell.listo.model;

import com.cameronvoell.listo.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by cameronvoell on 1/18/17.
 */

public class ReviewWordsSession {

	private ArrayList<SavedWord> mReviewWords;
	private HashSet<Integer> mWordsFinished = new HashSet<>();
	private HashMap<Integer, Integer> mNumAttempts = new HashMap<>();
	private int mCurrentCard;
	private DatabaseHelper mDatabaseHelper;

	public ReviewWordsSession(ArrayList<SavedWord> wordsToReview, DatabaseHelper databaseHelper) {
		mDatabaseHelper = databaseHelper;

		mReviewWords = wordsToReview;
		for (int i = 0; i < mReviewWords.size(); i++) {
			mNumAttempts.put(i, 0);
		}
		mCurrentCard = 0;
	}

	public SavedWord getCurrentSavedWord() {
		return mReviewWords.get(mCurrentCard);
	}

	public void setCurrentCardCorrect() {
		mWordsFinished.add(mCurrentCard);
		mNumAttempts.put(mCurrentCard, mNumAttempts.get(mCurrentCard) + 1);
		mDatabaseHelper.updateWordReviewData(mReviewWords.get(mCurrentCard), mNumAttempts.get(mCurrentCard));
		if (!isFinished()) {
			iterateUntilNextCard();
		}
	}

	public void setCurrentCardIncorrect() {
		mNumAttempts.put(mCurrentCard, mNumAttempts.get(mCurrentCard) + 1);

		if (mCurrentCard + 1 < mReviewWords.size()) {
			mCurrentCard++;
		} else {
			mCurrentCard = 0;
		}

		if (!isFinished()) {
			iterateUntilNextCard();
		}
	}

	public void setCurrentCardMemorized() {
		mWordsFinished.add(mCurrentCard);
		mNumAttempts.put(mCurrentCard, mNumAttempts.get(mCurrentCard) + 1);
		mDatabaseHelper.updateWordReviewDataMemorized(mReviewWords.get(mCurrentCard), mNumAttempts.get(mCurrentCard));
		if (!isFinished()) {
			iterateUntilNextCard();
		}
	}

	public boolean isFinished() {
		return mReviewWords.size() == mWordsFinished.size();
	}

	private void iterateUntilNextCard() {
		while (mWordsFinished.contains(mCurrentCard)) {
			if (mCurrentCard + 1 < mReviewWords.size()) {
				mCurrentCard++;
			} else {
				mCurrentCard = 0;
			}
		}
	}

}
