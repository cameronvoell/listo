package com.cameronvoell.listo.activities;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.adapters.CaptureWordTabAdapter;
import com.cameronvoell.listo.fragments.ManualWordCaptureFragment;
import com.cameronvoell.listo.fragments.SuggestedWordCaptureFragment;
import com.cameronvoell.listo.ui_widgets.SlidingTabLayout;
import com.cameronvoell.listo.util.ColorUtil;

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

		RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
		mainLayout.setBackground(new ColorUtil(getApplicationContext()).getLightColorTwoDrawable());

		ImageButton closeButton = (ImageButton)findViewById(R.id.close_btn);
		closeButton.setBackground(new ColorUtil(getApplicationContext()).getLightColorTwoDrawable());

		FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
		fab.setBackgroundTintList(ColorStateList.valueOf(new ColorUtil(getApplicationContext()).getLightColorResource()));

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
				return new ColorUtil(getApplicationContext()).getLightColorResource();
			}
		});
		slidingTabLayout.setViewPager(mViewPager);
		slidingTabLayout.setBackground(new ColorUtil(getApplicationContext()).getLightColorTwoDrawable());

		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				if (position == 1) {
					View view = CaptureWordActivity.this.getCurrentFocus();
					if (view != null) {
						InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
					}
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
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
			View v = this.getCurrentFocus();
			if (v != null) {
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
			}
		} else if (mViewPager.getCurrentItem() == 1 && page != null) {
			((SuggestedWordCaptureFragment)page).captureWords();
		}
	}

	public void autofill(View view) {
		Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
		if (mViewPager.getCurrentItem() == 0 && page != null) {
			((ManualWordCaptureFragment)page).autoFill();
		}
	}

	public void clear(View view) {
		Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
		if (mViewPager.getCurrentItem() == 1 && page != null) {
			((SuggestedWordCaptureFragment)page).clear();
		}
	}
}
