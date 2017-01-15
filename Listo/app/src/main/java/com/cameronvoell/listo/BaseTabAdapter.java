package com.cameronvoell.listo;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.cameronvoell.listo.fragments.HomeFragment;
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
		if (position == 0) return HomeFragment.newInstance();
		else return VocabWordListFragment.newInstance();
	}

	@Override
	public int getCount() {
		return iconRes.length;
	}

	@Override
	public int getPageIconResId(int position) {
		return iconRes[position];
	}
}
