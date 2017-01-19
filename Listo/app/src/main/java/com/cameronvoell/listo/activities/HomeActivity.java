package com.cameronvoell.listo.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cameronvoell.listo.adapters.BaseTabAdapter;
import com.cameronvoell.listo.R;
import com.cameronvoell.listo.fragments.VocabWordListFragment;
import com.cameronvoell.listo.ui_widgets.SlidingTabLayout;

public class HomeActivity extends AppCompatActivity {

	ViewPager mViewPager;
	BaseTabAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		setupViewPager();
	}

	private void setupViewPager() {

		mViewPager = (ViewPager) (findViewById(R.id.pager));
		mAdapter = new BaseTabAdapter(this);
		mViewPager.setAdapter(mAdapter);

		SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
		slidingTabLayout.setCustomTabView(R.layout.tab_img_layout, R.id.tab_name_img);
		slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			@Override
			public int getIndicatorColor(int position) {
				return getResources().getColor(R.color.colorAccent);
			}
		});
		slidingTabLayout.setViewPager(mViewPager);
	}

	public void captureWord(View v) {
		startActivity(new Intent(getApplicationContext(), CaptureWordActivity.class));
	}

	public void sort(View view) {
		Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
		if (mViewPager.getCurrentItem() == 1 && page != null) {
			((VocabWordListFragment)page).sort();
		}
	}

	public void filter(View view) {
		Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
		if (mViewPager.getCurrentItem() == 1 && page != null) {
			((VocabWordListFragment)page).filter();
		}
	}

	public void custom(View view) {
		Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
		if (mViewPager.getCurrentItem() == 1 && page != null) {
			((VocabWordListFragment)page).custom();
		}
	}

	public void search(View view) {
		Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
		if (mViewPager.getCurrentItem() == 1 && page != null) {
			((VocabWordListFragment)page).search();
		}
	}

	public void goToReview(View view) {
		startActivity(new Intent(getApplicationContext(), ReviewWordsActivity.class));
	}
}
