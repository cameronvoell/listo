package com.cameronvoell.listo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.VerbConjugationRow;

public class VerbConjugationsView extends ScrollView {

	TextView mInfinitiveTextView;
	TextView mGerundTextView;
	TextView mParticipleTextView;


	public VerbConjugationsView(Context context) {
		this(context, null);
	}

	public VerbConjugationsView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public VerbConjugationsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		LayoutInflater.from(context).inflate(R.layout.view_verb_conjugations, this);

		mInfinitiveTextView = (TextView)findViewById(R.id.infinitive);
		mGerundTextView = (TextView)findViewById(R.id.gerund);
		mParticipleTextView = (TextView)findViewById(R.id.participle);

	}

	public void setVerbConjugations(VerbConjugationRow verbConjugationData) {
		mInfinitiveTextView.setText(verbConjugationData.getInfinitivo());
		mGerundTextView.setText(verbConjugationData.getGerund());
		mParticipleTextView.setText(verbConjugationData.getPastParticiple());
	}


}
