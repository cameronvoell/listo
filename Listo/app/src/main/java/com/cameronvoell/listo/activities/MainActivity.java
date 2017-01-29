package com.cameronvoell.listo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.cameronvoell.listo.adapters.BaseTabAdapter;
import com.cameronvoell.listo.R;
import com.cameronvoell.listo.fragments.CustomizeFragment;
import com.cameronvoell.listo.fragments.HomeFragment;
import com.cameronvoell.listo.fragments.VerbsFragment;
import com.cameronvoell.listo.fragments.VocabWordListFragment;
import com.cameronvoell.listo.ui_widgets.SlidingTabLayout;
import com.cameronvoell.listo.util.ColorUtil;

public class MainActivity extends AppCompatActivity {

	public static final int RESULT_CODE_ADDED_WORD = 0;
	public static final int RESULT_CODE_PRUNING_SESSION = 1;
	public static final int RESULT_CODE_REVIEW_SESSION = 2;

	ViewPager mViewPager;
	BaseTabAdapter mAdapter;
	SlidingTabLayout mSlidingTabLayout;
	FloatingActionButton mFab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mFab = (FloatingActionButton) findViewById(R.id.fab);
		mFab.setBackgroundTintList(ColorStateList.valueOf(new ColorUtil(getApplicationContext()).getLightColorResource()));

		setupViewPager();
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RESULT_CODE_ADDED_WORD) {
			if (resultCode == RESULT_OK) {
				mViewPager.setCurrentItem(1);
				Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
				if (page != null) {
					((VocabWordListFragment)page).updateFilter(VocabWordListFragment.FILTER_OPTION_MY_SAVED_WORDS);
				}
			}
		} else if (requestCode == RESULT_CODE_PRUNING_SESSION ||
				   requestCode == RESULT_CODE_REVIEW_SESSION) {
//				getApplicationContext().getSharedPreferences(getApplicationContext().getString(
//				R.string.listo_shared_preferences), 0).edit().putInt(
//				VocabWordListFragment.PREF_VOCABf_LIST_FILTER,
//				VocabWordListFragment.FILTER_OPTION_WORDS_NEEDING_REVIEW).apply();
		}
	}

	private void setupViewPager() {

		mViewPager = (ViewPager) (findViewById(R.id.pager));
		mAdapter = new BaseTabAdapter(this);
		mViewPager.setAdapter(mAdapter);

		mSlidingTabLayout= (SlidingTabLayout) findViewById(R.id.tabs);
		mSlidingTabLayout.setCustomTabView(R.layout.tab_img_layout, R.id.tab_name_img);
		mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			@Override
			public int getIndicatorColor(int position) {
				return new ColorUtil(getApplicationContext()).getLightColorResource();
			}
		});
		mSlidingTabLayout.setViewPager(mViewPager);
		mSlidingTabLayout.setBackground(new ColorUtil(getApplicationContext()).getDarkColorDrawable());

		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				if (position == 2) {
					Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
					if (mViewPager.getCurrentItem() == 2 && page != null) {
						((VerbsFragment)page).updateBackgroundColor();
					}
					mFab.show();
				} else if (position == 3) {
					mFab.hide();
				} else if (position == 0) {
					mFab.show();
					Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
					if (mViewPager.getCurrentItem() == 0 && page != null) {
						((HomeFragment)page).resetActionBoxes();
					}
				} else {
					Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
					if (mViewPager.getCurrentItem() == 1 && page != null) {
						((VocabWordListFragment)page).updateFilter();
					}
					mFab.show();
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

	}

	public void captureWord(View v) {
		startActivityForResult(new Intent(getApplicationContext(), CaptureWordActivity.class), RESULT_CODE_ADDED_WORD);
	}

	public void filter(View view) {
		Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
		if (mViewPager.getCurrentItem() == 1 && page != null) {
			((VocabWordListFragment)page).filter();
		}
	}

	public void goToReview(View view) {
		startActivityForResult(new Intent(getApplicationContext(), ReviewWordsActivity.class),
				RESULT_CODE_REVIEW_SESSION);
	}

	public void goToPruning(View view) {
		startActivityForResult(new Intent(getApplicationContext(), PruneWordsActivity.class),
				RESULT_CODE_PRUNING_SESSION);
	}

	public void setTheme(View view) {
		int newTheme = 1;

		switch(view.getId()) {
			case R.id.colombian_theme:
				newTheme = ColorUtil.THEME_COLOMBIA;
				break;
			case R.id.espana_theme:
				newTheme = ColorUtil.THEME_SPAIN;
				break;
			case R.id.mexico_theme:
				newTheme = ColorUtil.THEME_MEXICO;
				break;
			case R.id.argentina_theme:
				newTheme = ColorUtil.THEME_ARGENTINA;
				break;
		}
		ColorUtil colorUtil = new ColorUtil(getApplicationContext());
		colorUtil.updateTheme(newTheme);
		mSlidingTabLayout.setBackground(colorUtil.getDarkColorDrawable());
		mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			@Override
			public int getIndicatorColor(int position) {
				return new ColorUtil(getApplicationContext()).getLightColorResource();
			}
		});
		mFab.setBackgroundTintList(ColorStateList.valueOf(new ColorUtil(getApplicationContext()).getLightColorResource()));
		Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
		if (mViewPager.getCurrentItem() == 3 && page != null) {
			((CustomizeFragment)page).updateHighlights();
		}
	}

	public void viewBackground(View view) {
		Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
		if (mViewPager.getCurrentItem() == 0 && page != null) {
			mFab.hide();
			((HomeFragment)page).viewBackground();
		}
	}

	public void showFab() {
		mFab.show();
	}
}
