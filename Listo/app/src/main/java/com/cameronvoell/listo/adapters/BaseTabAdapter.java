package com.cameronvoell.listo.adapters;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.fragments.CustomizeFragment;
import com.cameronvoell.listo.fragments.HomeFragment;
import com.cameronvoell.listo.fragments.VerbsFragment;
import com.cameronvoell.listo.fragments.VocabWordListFragment;
import com.cameronvoell.listo.ui_widgets.SlidingTabLayout;

/**
 * Created by cameronvoell on 1/15/17.
 */
public class BaseTabAdapter extends FragmentPagerAdapter implements SlidingTabLayout.TabIconProvider {

	private static final int iconRes[] = {
			R.drawable.ic_action_eye_open,
			R.drawable.ic_list,
			R.drawable.ic_keyboard_arrow_down,
			R.drawable.ic_photo
	};

	private static final String iconTxt[] = {
			"home",
			"vocabList",
			"verbs",
			"customize"
	};

	public BaseTabAdapter(AppCompatActivity activity) {
		super(activity.getSupportFragmentManager());
	}

	public BaseTabAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		super.registerDataSetObserver(observer);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return HomeFragment.newInstance();
			case 1:
				return VocabWordListFragment.newInstance();
			case 2:
				return VerbsFragment.newInstance();
			default:
				return CustomizeFragment.newInstance();
		}
	}

	@Override
	public int getCount() {
		return iconRes.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return iconTxt[position];
	}

	@Override
	public int getPageIconResId(int position) {
		return iconRes[position];
	}
}
