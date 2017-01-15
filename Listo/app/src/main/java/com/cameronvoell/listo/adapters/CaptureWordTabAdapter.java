package com.cameronvoell.listo.adapters;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.fragments.HomeFragment;
import com.cameronvoell.listo.fragments.ManualWordCaptureFragment;
import com.cameronvoell.listo.fragments.SuggestedWordCaptureFragment;
import com.cameronvoell.listo.fragments.VocabWordListFragment;
import com.cameronvoell.listo.ui_widgets.SlidingTabLayout;

/**
 * Created by cameronvoell on 1/15/17.
 */
public class CaptureWordTabAdapter extends FragmentPagerAdapter implements SlidingTabLayout.TabIconProvider {

	private static final String iconTxt[] = {
			"manual",
			"suggested"
	};

	public CaptureWordTabAdapter(AppCompatActivity activity) {
		super(activity.getSupportFragmentManager());
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		super.registerDataSetObserver(observer);
	}

	@Override
	public Fragment getItem(int position) {
		if (position == 0) return ManualWordCaptureFragment.newInstance();
		else return SuggestedWordCaptureFragment.newInstance();
	}

	@Override
	public int getCount() {
		return iconTxt.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return iconTxt[position];
	}

	@Override
	public int getPageIconResId(int position) {
		return 0;
	}
}
