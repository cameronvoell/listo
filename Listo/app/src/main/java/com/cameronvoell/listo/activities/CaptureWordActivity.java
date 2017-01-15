package com.cameronvoell.listo.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.adapters.CaptureWordTabAdapter;
import com.cameronvoell.listo.fragments.ManualWordCaptureFragment;
import com.cameronvoell.listo.ui_widgets.SlidingTabLayout;

/**
 * Created by cameronvoell on 1/15/17.
 */
public class CaptureWordActivity extends AppCompatActivity {

	ViewPager mViewPager;
	CaptureWordTabAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capture_word);

		setupViewPager();
	}

	private void setupViewPager() {

		mViewPager = (ViewPager) (findViewById(R.id.pager));
		mAdapter = new CaptureWordTabAdapter(this);
		mViewPager.setAdapter(mAdapter);

		SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
		slidingTabLayout.setCustomTabView(R.layout.tab_text_layout, R.id.tab_name_text);
		slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			@Override
			public int getIndicatorColor(int position) {
				return getResources().getColor(R.color.colorAccent);
			}
		});
		slidingTabLayout.setViewPager(mViewPager);
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
		Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
		if (mViewPager.getCurrentItem() == 0 && page != null) {
			((ManualWordCaptureFragment)page).captureWord();
		}
	}

	public void autofill(View view) {
		Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
		if (mViewPager.getCurrentItem() == 0 && page != null) {
			((ManualWordCaptureFragment)page).autoFill();
		}
	}
}
