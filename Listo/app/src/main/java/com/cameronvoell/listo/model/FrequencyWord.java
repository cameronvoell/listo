package com.cameronvoell.listo.model;

/**
 * Created by cameronvoell on 1/15/17.
 */

public class FrequencyWord {

	public static final String TYPE_ART = "art";
	public static final String TYPE_ADJ = "adj";
	public static final String TYPE_ADV = "adv";
	public static final String TYPE_CONJ = "conj";
	public static final String TYPE_INTERJ = "interj";
	public static final String TYPE_V = "v";
	public static final String TYPE_N = "n";
	public static final String TYPE_NC = "nc";
	public static final String TYPE_NF = "nf";
	public static final String TYPE_NM = "nm";
	public static final String TYPE_NMF = "art";
	public static final String TYPE_NM_F = "nm/f";
	public static final String TYPE_NUM = "num";
	public static final String TYPE_PREP = "prep";
	public static final String TYPE_PRON = "pron";

	private int mFrequency;
	private String mWord;
	private String mEng;
	private String mType;
	private boolean mIsPruned;

	public FrequencyWord(int frequency, String spanish, String eng, String type, boolean isPruned) {
		mFrequency = frequency;
		mWord = spanish;
		mEng = eng;
		mType = type;
		mIsPruned = isPruned;
	}

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

	public boolean ismIsPruned() {
		return mIsPruned;
	}

	public void setmIsPruned(boolean mIsPruned) {
		this.mIsPruned = mIsPruned;
	}
}
