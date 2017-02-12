package com.cameronvoell.listo.model;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by cameronvoell on 2/11/17.
 */
public class SentenceRow {

	private String mSentenceId;
	private String mSentenceText;
	private String mSentenceWord;
	private int mSentenceWordLocation;
	private String mSentenceEng;
	private boolean mSentenceStarred;
	private String mSentenceCapturedDate;
	private int mSentenceVerbTense;

	public SentenceRow(String sentenceId, String text, String word, int wordLocation, String eng,
					   boolean starred, String capturedDate, int verbTense) {
		mSentenceId = sentenceId;
		mSentenceText = text;
		mSentenceWord = word;
		mSentenceWordLocation = wordLocation;
		mSentenceEng = eng;
		mSentenceStarred = starred;
		mSentenceCapturedDate = capturedDate;
		mSentenceVerbTense = verbTense;
	}

	public Spanned getStylizedSentence() {
		String[] words = mSentenceText.split(" ");
		String text = "<font color=#333333>";
		for (int i = 0; i < words.length; i++) {
			if (i == mSentenceWordLocation) {
				text += "</font><font color=#C84C42>" + "____" + "</font><font color=#333333>";
			} else {
				text += words[i];
			}
			text += " ";
		}
		return Html.fromHtml(text);
	}

	public String getmSentenceId() {
		return mSentenceId;
	}

	public void setmSentenceId(String mSentenceId) {
		this.mSentenceId = mSentenceId;
	}

	public String getmSentenceText() {
		return mSentenceText;
	}

	public void setmSentenceText(String mSentenceText) {
		this.mSentenceText = mSentenceText;
	}

	public String getmSentenceWord() {
		return mSentenceWord;
	}

	public void setmSentenceWord(String mSentenceWord) {
		this.mSentenceWord = mSentenceWord;
	}

	public int getmSentenceWordLocation() {
		return mSentenceWordLocation;
	}

	public void setmSentenceWordLocation(int mSentenceWordLocation) {
		this.mSentenceWordLocation = mSentenceWordLocation;
	}

	public String getmSentenceEng() {
		return mSentenceEng;
	}

	public void setmSentenceEng(String mSentenceEng) {
		this.mSentenceEng = mSentenceEng;
	}

	public boolean ismSentenceStarred() {
		return mSentenceStarred;
	}

	public void setmSentenceStarred(boolean mSentenceStarred) {
		this.mSentenceStarred = mSentenceStarred;
	}

	public String getmSentenceCapturedDate() {
		return mSentenceCapturedDate;
	}

	public void setmSentenceCapturedDate(String mSentenceCapturedDate) {
		this.mSentenceCapturedDate = mSentenceCapturedDate;
	}

	public int getmSentenceVerbTense() {
		return mSentenceVerbTense;
	}

	public void setmSentenceVerbTense(int mSentenceVerbTense) {
		this.mSentenceVerbTense = mSentenceVerbTense;
	}
}
