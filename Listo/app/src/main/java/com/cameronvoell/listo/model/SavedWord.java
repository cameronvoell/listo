package com.cameronvoell.listo.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Spanned;

/**
 * Created by cameronvoell on 1/15/17.
 */

public class SavedWord implements Parcelable {

	public static final int MEMORIZED_NOT_KNOWN = 0;
	public static final int MEMORIZED_MEANING_KNOWN = 1;
	public static final int MEMORIZED_WORD_MASTERED = 2;

	private String mWord;
	private String mEng;
	private int mFrequency;
	private String mAddedDate;
	private String mType;
	private String mExample;
	private String mHint;
	private boolean mIsMemorized;
	private int mMemoryStrength;
	private String mLastReviewedDate;
	private int mNumTimesReviewed;
	private int mNumTimesIncorrect;
	private boolean mIsPruned;
	private boolean mIsManuallyAdded;
	private String mSentenceId;
	private int mNumSentences;
	private boolean mSkipSentencePractice;

	public SavedWord(String spanish, String eng, int frequency, String addedDate, String type,
					 String example, String hint, boolean isMemorized, int memoryStrength,
					 String lastReviewedDate, int numTimesReviewed, int numTimesIncorrect,
					 boolean isPruned, boolean isManuallyAdded, String sentenceId, int numSentences,
					 boolean skipSentencePractice) {
		mWord = spanish;
		mEng = eng;
		mFrequency = frequency;
		mAddedDate = addedDate;
		mType = type;
		mExample = example;
		mHint = hint;
		mIsMemorized = isMemorized;
		mMemoryStrength = memoryStrength;
		mLastReviewedDate = lastReviewedDate;
		mNumTimesReviewed = numTimesReviewed;
		mNumTimesIncorrect = numTimesIncorrect;
		mIsPruned = isPruned;
		mIsManuallyAdded = isManuallyAdded;
		mSentenceId = sentenceId;
		mNumSentences = numSentences;
		mSkipSentencePractice = skipSentencePractice;
	}

	public String getmWord() {
		return mWord;
	}

	public void setmWord(String mWord) {
		this.mWord = mWord;
	}

	public String getmEng() {
		return mEng;
	}

	public void setmEng(String mEng) {
		this.mEng = mEng;
	}

	public int getmFrequency() {
		return mFrequency;
	}

	public void setmFrequency(int mFrequency) {
		this.mFrequency = mFrequency;
	}

	public String getmAddedDate() {
		return mAddedDate;
	}

	//Only can be set by constructor
//	public void setmAddedDate(String mAddedDate) {
//		this.mAddedDate = mAddedDate;
//	}

	public String getmType() {
		return mType;
	}

	public void setmType(String mType) {
		this.mType = mType;
	}

	public String getmExample() {
		return mExample;
	}

	public void setmExample(String mExample) {
		this.mExample = mExample;
	}

	public String getmHint() {
		return mHint;
	}

	public void setmHint(String mHint) {
		this.mHint = mHint;
	}

	public boolean ismIsMemorized() {
		return mIsMemorized;
	}

	public void setmIsMemorized(boolean mIsMemorized) {
		this.mIsMemorized = mIsMemorized;
	}

	public int getmMemoryStrength() {
		return mMemoryStrength;
	}

	public void setmMemoryStrength(int mMemoryStrength) {
		this.mMemoryStrength = mMemoryStrength;
	}

	public String getmLastReviewedDate() {
		return mLastReviewedDate;
	}

	public void setmLastReviewedDate(String mLastReviewedDate) {
		this.mLastReviewedDate = mLastReviewedDate;
	}

	public int getmNumTimesReviewed() {
		return mNumTimesReviewed;
	}

	public void setmNumTimesReviewed(int mNumTimesReviewed) {
		this.mNumTimesReviewed = mNumTimesReviewed;
	}

	public int getmNumTimesIncorrect() {
		return mNumTimesIncorrect;
	}

	public void setmNumTimesIncorrect(int mNumTimesIncorrect) {
		this.mNumTimesIncorrect = mNumTimesIncorrect;
	}


	public boolean ismIsPruned() {
		return mIsPruned;
	}

	public void setmIsPruned(boolean mIsPruned) {
		this.mIsPruned = mIsPruned;
	}

	public boolean ismIsManuallyAdded() {
		return mIsManuallyAdded;
	}

	public void setmIsManuallyAdded(boolean mIsManuallyAdded) {
		this.mIsManuallyAdded = mIsManuallyAdded;
	}

	protected SavedWord(Parcel in) {
		mWord = in.readString();
		mEng = in.readString();
		mFrequency = in.readInt();
		mAddedDate = in.readString();
		mType = in.readString();
		mExample = in.readString();
		mHint = in.readString();
		mIsMemorized = in.readByte() != 0;
		mMemoryStrength = in.readInt();
		mLastReviewedDate = in.readString();
		mNumTimesReviewed = in.readInt();
		mNumTimesIncorrect = in.readInt();
		mIsPruned = in.readByte() != 0;
		mIsManuallyAdded = in.readByte() != 0;
		mSentenceId = in.readString();
		mNumSentences = in.readInt();
		mSkipSentencePractice = in.readByte() != 0;
	}

	public static final Creator<SavedWord> CREATOR = new Creator<SavedWord>() {
		@Override
		public SavedWord createFromParcel(Parcel in) {
			return new SavedWord(in);
		}

		@Override
		public SavedWord[] newArray(int size) {
			return new SavedWord[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mWord);
		dest.writeString(mEng);
		dest.writeInt(mFrequency);
		dest.writeString(mAddedDate);
		dest.writeString(mType);
		dest.writeString(mExample);
		dest.writeString(mHint);
		dest.writeByte((byte) (mIsMemorized ? 1 : 0));
		dest.writeInt(mMemoryStrength);
		dest.writeString(mLastReviewedDate);
		dest.writeInt(mNumTimesReviewed);
		dest.writeInt(mNumTimesIncorrect);
		dest.writeByte((byte)(mIsPruned ? 1 : 0));
		dest.writeByte((byte)(mIsManuallyAdded ? 1 : 0));
		dest.writeString(mSentenceId);
		dest.writeInt(mNumSentences);
		dest.writeByte((byte)(mSkipSentencePractice ? 1 : 0));
	}

	@Override
	public String toString() {
		return mWord;
	}

	public String getmSentenceId() {
		return mSentenceId;
	}

	public void setmSentenceId(String mSentenceId) {
		this.mSentenceId = mSentenceId;
	}

	public int getmNumSentences() {
		return mNumSentences;
	}

	public void setmNumSentences(int mNumSentences) {
		this.mNumSentences = mNumSentences;
	}

	public boolean ismSkipSentencePractice() {
		return mSkipSentencePractice;
	}

	public void setmSkipSentencePractice(boolean mSkipSentencePractice) {
		this.mSkipSentencePractice = mSkipSentencePractice;
	}
}

