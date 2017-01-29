package com.cameronvoell.listo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.SharedPreferencesCompat;

import com.cameronvoell.listo.R;

/**
 * Created by cameronvoell on 1/28/17.
 */

public class ColorUtil {

	public static final int THEME_SPAIN = 0;
	public static final int THEME_COLOMBIA = 1;
	public static final int THEME_MEXICO = 2;
	public static final int THEME_ARGENTINA = 3;

	public static final String KEY_PREFERENCES_THEME = "KEY_PREFERENCES_THEME";

	private SharedPreferences mPreferences;
	private Context mContext;
	private int mCurrentTheme;



	public ColorUtil(Context context) {
		mContext = context;
		mPreferences = mContext.getSharedPreferences(mContext.getString(R.string.listo_shared_preferences), 0);
		mCurrentTheme = mPreferences.getInt(KEY_PREFERENCES_THEME, 0);
	}

	public ColorDrawable getDarkColorDrawable() {
		switch (mCurrentTheme) {
			case THEME_SPAIN:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorDarkSpain));
			case THEME_COLOMBIA:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorDarkColombia));
			case THEME_MEXICO:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorDarkMexico));
			case THEME_ARGENTINA:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorDarkArgentina));
			default:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorDarkSpain));
		}
	}

	public ColorDrawable getLightColorDrawable() {
		switch (mCurrentTheme) {
			case THEME_SPAIN:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorLightSpain));
			case THEME_COLOMBIA:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorLightColombia));
			case THEME_MEXICO:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorLightMexico));
			case THEME_ARGENTINA:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorLightArgentina));
			default:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorLightSpain));
		}
	}

	public ColorDrawable getLightColorTwoDrawable() {
		switch (mCurrentTheme) {
			case THEME_SPAIN:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorLightTwoSpain));
			case THEME_COLOMBIA:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorLightTwoColombia));
			case THEME_MEXICO:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorLightTwoMexico));
			case THEME_ARGENTINA:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorLightTwoArgentina));
			default:
				return new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorLightTwoSpain));
		}
	}

	public int getDarkColorResource() {
		switch (mCurrentTheme) {
			case THEME_SPAIN:
				return ContextCompat.getColor(mContext, R.color.colorDarkSpain);
			case THEME_COLOMBIA:
				return ContextCompat.getColor(mContext, R.color.colorDarkColombia);
			case THEME_MEXICO:
				return ContextCompat.getColor(mContext, R.color.colorDarkMexico);
			case THEME_ARGENTINA:
				return ContextCompat.getColor(mContext, R.color.colorDarkArgentina);
			default:
				return ContextCompat.getColor(mContext, R.color.colorDarkSpain);
		}
	}

	public int getLightColorResource() {
		switch (mCurrentTheme) {
			case THEME_SPAIN:
				return ContextCompat.getColor(mContext, R.color.colorLightSpain);
			case THEME_COLOMBIA:
				return ContextCompat.getColor(mContext, R.color.colorLightColombia);
			case THEME_MEXICO:
				return ContextCompat.getColor(mContext, R.color.colorLightMexico);
			case THEME_ARGENTINA:
				return ContextCompat.getColor(mContext, R.color.colorLightArgentina);
			default:
				return ContextCompat.getColor(mContext, R.color.colorLightSpain);
		}
	}

	public int getLightColorTwoResource() {
		switch (mCurrentTheme) {
			case THEME_SPAIN:
				return ContextCompat.getColor(mContext, R.color.colorLightTwoSpain);
			case THEME_COLOMBIA:
				return ContextCompat.getColor(mContext, R.color.colorLightTwoColombia);
			case THEME_MEXICO:
				return ContextCompat.getColor(mContext, R.color.colorLightTwoMexico);
			case THEME_ARGENTINA:
				return ContextCompat.getColor(mContext, R.color.colorLightTwoArgentina);
			default:
				return ContextCompat.getColor(mContext, R.color.colorLightTwoSpain);
		}
	}

	public void updateTheme(int theme) {
		mPreferences.edit().putInt(KEY_PREFERENCES_THEME, theme).commit();
		mCurrentTheme = theme;
	}
}
