package com.cameronvoell.listo.model;

/**
 * Created by cameronvoell on 1/15/17.
 */

public class SavedWord {

	public int getmFrequency() {
		return mFrequency;
	}

	public void setmFrequency(int mFrequency) {
		this.mFrequency = mFrequency;
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

	public String getmType() {
		return mType;
	}

	public void setmType(String mType) {
		this.mType = mType;
	}

	private int mFrequency;
	private String mWord;
	private String mEng;
	private String mType;
	private boolean mHasCard;
	private String mAddedDate;

	public SavedWord(int frequency, String spanish, String eng, String type) {
		mFrequency = frequency;
		mWord = spanish;
		mEng = eng;
		mType = type;
	}

	public boolean ismHasCard() {
		return mHasCard;
	}

	public void setmHasCard(boolean mHasCard) {
		this.mHasCard = mHasCard;
	}

	public String getmAddedDate() {
		return mAddedDate;
	}

	public void setmAddedDate(String mAddedDate) {
		this.mAddedDate = mAddedDate;
	}
}

