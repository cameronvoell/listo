package com.cameronvoell.listo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cameronvoell on 1/15/17.
 */

public class SavedWord implements Parcelable {

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

	public SavedWord(String spanish, String eng, int frequency, String addedDate, String type,
					 String example, String hint, boolean isMemorized, int memoryStrength,
					 String lastReviewedDate, int numTimesReviewed, int numTimesIncorrect) {
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
	}
}

