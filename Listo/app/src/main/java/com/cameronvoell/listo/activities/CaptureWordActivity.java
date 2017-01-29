package com.cameronvoell.listo.activities;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.fragments.ManualWordCaptureFragment;
import com.cameronvoell.listo.util.ColorUtil;

/**
 * Created by cameronvoell on 1/15/17.
 */
public class CaptureWordActivity extends AppCompatActivity {

	ManualWordCaptureFragment mWordCaptureFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capture_word);

		RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
		mainLayout.setBackground(new ColorUtil(getApplicationContext()).getLightColorTwoDrawable());

		ImageButton closeButton = (ImageButton)findViewById(R.id.close_btn);
		closeButton.setBackground(new ColorUtil(getApplicationContext()).getLightColorTwoDrawable());

		FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
		fab.setBackgroundTintList(ColorStateList.valueOf(new ColorUtil(getApplicationContext()).getLightColorResource()));

		mWordCaptureFragment = new ManualWordCaptureFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,
				mWordCaptureFragment).commit();

	}

	public void close(View v) {
		View view = this.getCurrentFocus();
		if (view != null) {
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
		Handler delay = new Handler();
		delay.postDelayed(new Runnable() {
			@Override
			public void run() {
				finish();
			}
		}, 100);

	}

	public void captureWord(View view) {
		if(mWordCaptureFragment.captureWord()) {
			close(null);
		}
	}

	public void autofill(View view) {
		mWordCaptureFragment.autoFill();
	}
}
