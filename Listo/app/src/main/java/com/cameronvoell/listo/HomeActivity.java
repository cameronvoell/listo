package com.cameronvoell.listo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
}
