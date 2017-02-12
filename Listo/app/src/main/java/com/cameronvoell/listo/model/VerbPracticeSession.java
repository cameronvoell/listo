package com.cameronvoell.listo.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.fragments.VerbPracticeConfiguratorFragment;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by cameronvoell on 1/30/17.
 */
public class VerbPracticeSession {

	private static final String PREF_NUM_WORDS_MOSTLY_COVERED_PRESENTE = "PREF_NUM_WORDS_MOSTLY_COVERED_PRESENTE";
	private static final String PREF_NUM_WORDS_MOSTLY_COVERED_PRETERITO = "PREF_NUM_WORDS_MOSTLY_COVERED_PRETERITO";
	private static final String PREF_NUM_WORDS_MOSTLY_COVERED_IMPERFECTO = "PREF_NUM_WORDS_MOSTLY_COVERED_IMPERFECTO";

	public static final int SUBJECT_YO = 0;
	public static final int SUBJECT_TU = 1;
	public static final int SUBJECT_USTED = 2;
	public static final int SUBJECT_NOSOTROS = 3;
	public static final int SUBJECT_VOSOTROS = 4;
	public static final int SUBJECT_USTEDES = 5;

	private int mTense = VerbPracticeConfiguratorFragment.TENSE_OPTION_PRESENTE;
	private int mNumSentences;
	private int mNumDifferentVerbs = 6;
	public ArrayList<VerbPracticePrompt> mVerbPracticePrompts;
	private int mNumWordsWithFourOrMoreSubjectsCoveredAlready = 0;
	private Context mContext;
	private VerbPracticePrompt mCurrentVerbPracticePrompt;
	int mCurrentPromptNum = 0;
	boolean mIsFinished = false;

	private DatabaseHelper mDatabaseHelper;

	public VerbPracticeSession(int numSentences, DatabaseHelper databaseHelper, int tense, Context context) {
		mNumSentences = numSentences;
		mDatabaseHelper = databaseHelper;
		mTense = tense;

		mVerbPracticePrompts = mDatabaseHelper.getVerbPracticeSessionPrompts(mTense, mNumSentences, mNumDifferentVerbs);
		mContext = context;
		mCurrentVerbPracticePrompt = mVerbPracticePrompts.get(0);

		switch(mTense) {
			case VerbPracticeConfiguratorFragment.TENSE_OPTION_PRESENTE:
				mNumWordsWithFourOrMoreSubjectsCoveredAlready = mContext.getSharedPreferences(
						mContext.getString(R.string.listo_shared_preferences), 0).getInt(
						PREF_NUM_WORDS_MOSTLY_COVERED_PRESENTE, 0);
				break;
			case VerbPracticeConfiguratorFragment.TENSE_OPTION_PRETERITO:
				mNumWordsWithFourOrMoreSubjectsCoveredAlready = mContext.getSharedPreferences(
						mContext.getString(R.string.listo_shared_preferences), 0).getInt(
						PREF_NUM_WORDS_MOSTLY_COVERED_PRESENTE, 0);
				break;
			case VerbPracticeConfiguratorFragment.TENSE_OPTION_IMPERFECTO:
				mNumWordsWithFourOrMoreSubjectsCoveredAlready = mContext.getSharedPreferences(
						mContext.getString(R.string.listo_shared_preferences), 0).getInt(
						PREF_NUM_WORDS_MOSTLY_COVERED_PRESENTE, 0);
				break;
		}
	}

	public VerbPracticePrompt getCurrentVerbPrompt() {
		return mCurrentVerbPracticePrompt;
	}

	public void next() {
		mCurrentPromptNum++;
		if (mCurrentPromptNum < mVerbPracticePrompts.size()) {
			mCurrentVerbPracticePrompt = mVerbPracticePrompts.get(mCurrentPromptNum);
		} else {
			mIsFinished = true;
		}
	}

	public boolean getIsFinished() {
		return mIsFinished;
	}

	public static class VerbPracticePrompt implements Parcelable {

		HashSet<Integer> mSubjectsAlreadyCoveredByVerbForThisTense;
		SavedWord mWord;
		String mCorrectConjugation;
		int mSubject;
		int mTense;

		public VerbPracticePrompt(SavedWord word, String correctConjugation, int subject, int tense, HashSet<Integer> subjectsCovered) {
			mWord = word;
			mCorrectConjugation = correctConjugation;
			mSubject = subject;
			mTense = tense;
			mSubjectsAlreadyCoveredByVerbForThisTense = subjectsCovered;
		}

		protected VerbPracticePrompt(Parcel in) {
			mWord = in.readParcelable(SavedWord.class.getClassLoader());
			mCorrectConjugation = in.readString();
			mSubject = in.readInt();
			mTense = in.readInt();
			boolean[] helper = new boolean[6];
			in.readBooleanArray(helper);
			for (int i = 0; i < 6; i++) {
				if(helper[i])mSubjectsAlreadyCoveredByVerbForThisTense.add(i);
			}
		}

		public static final Creator<VerbPracticePrompt> CREATOR = new Creator<VerbPracticePrompt>() {
			@Override
			public VerbPracticePrompt createFromParcel(Parcel in) {
				return new VerbPracticePrompt(in);
			}

			@Override
			public VerbPracticePrompt[] newArray(int size) {
				return new VerbPracticePrompt[size];
			}
		};

		public String getCorrectConjugation() {
			return mCorrectConjugation;
		}

		public HashSet<Integer> getmSubjectsAlreadyCoveredByVerbForThisTense() {
			return mSubjectsAlreadyCoveredByVerbForThisTense;
		}

		public SavedWord getmWord() {
			return mWord;
		}
		public int getmSubject(){
			return mSubject;
		}

		public int getTense() {
			return mTense;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeParcelable(mWord, flags);
			dest.writeString(mCorrectConjugation);
			dest.writeInt(mSubject);
			dest.writeInt(mTense);
			boolean[] helper = {
					mSubjectsAlreadyCoveredByVerbForThisTense.contains(0),
					mSubjectsAlreadyCoveredByVerbForThisTense.contains(1),
					mSubjectsAlreadyCoveredByVerbForThisTense.contains(2),
					mSubjectsAlreadyCoveredByVerbForThisTense.contains(3),
					mSubjectsAlreadyCoveredByVerbForThisTense.contains(4),
					mSubjectsAlreadyCoveredByVerbForThisTense.contains(5)};
			dest.writeBooleanArray(helper);
		}
	}

}
