package com.cameronvoell.listo.util;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cameronvoell on 2/6/17.
 */

public class PrefUtil {

	public static final String PREF_VERBS_PRACTICED_PRESENT = "PREF_VERBS_PRACTICED_PRESENT";

	public static Set<String> getStringSetPref(Context context, String s) {
		return PreferenceManager.getDefaultSharedPreferences(context).getStringSet(s, new HashSet<String>());
	}

	public static void updateStringSetPref(Context context, String s, Set<String> set) {
		PreferenceManager.getDefaultSharedPreferences(context).edit().putStringSet(s, set).apply();
	}

}
