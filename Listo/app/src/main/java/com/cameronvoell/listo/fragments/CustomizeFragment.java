package com.cameronvoell.listo.fragments;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.util.ColorUtil;


public class CustomizeFragment extends Fragment {

    private LinearLayout mColombiaLayout;
    private LinearLayout mSpainLayout;
    private LinearLayout mMexicoLayout;
    private LinearLayout mArgentinaLayout;

    public static CustomizeFragment newInstance() {
        CustomizeFragment fragment = new CustomizeFragment();
        return fragment;
    }

    public CustomizeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_customize, container, false);

        mColombiaLayout = (LinearLayout)v.findViewById(R.id.colombian_theme);
        mSpainLayout = (LinearLayout)v.findViewById(R.id.espana_theme);
        mMexicoLayout = (LinearLayout)v.findViewById(R.id.mexico_theme);
        mArgentinaLayout = (LinearLayout)v.findViewById(R.id.argentina_theme);

        int theme = getContext().getSharedPreferences(getContext().getString(
                R.string.listo_shared_preferences), 0).getInt(ColorUtil.KEY_PREFERENCES_THEME, 0);

        switch (theme) {
            case ColorUtil.THEME_COLOMBIA:
                mColombiaLayout.setBackground(new ColorUtil(getContext()).getLightColorDrawable());
                break;
            case ColorUtil.THEME_SPAIN:
                mSpainLayout.setBackground(new ColorUtil(getContext()).getLightColorDrawable());
                break;
            case ColorUtil.THEME_MEXICO:
                mMexicoLayout.setBackground(new ColorUtil(getContext()).getLightColorDrawable());
                break;
            case ColorUtil.THEME_ARGENTINA:
                mArgentinaLayout.setBackground(new ColorUtil(getContext()).getLightColorDrawable());
                break;
        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void updateHighlights() {
        int theme = getContext().getSharedPreferences(getContext().getString(
                R.string.listo_shared_preferences), 0).getInt(ColorUtil.KEY_PREFERENCES_THEME, 0);

        mColombiaLayout.setBackground(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.darker_gray)));
        mSpainLayout.setBackground(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.darker_gray)));
        mMexicoLayout.setBackground(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.darker_gray)));
        mArgentinaLayout.setBackground(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.darker_gray)));

        switch (theme) {
            case ColorUtil.THEME_COLOMBIA:
                mColombiaLayout.setBackground(new ColorUtil(getContext()).getLightColorDrawable());
                break;
            case ColorUtil.THEME_SPAIN:
                mSpainLayout.setBackground(new ColorUtil(getContext()).getLightColorDrawable());
                break;
            case ColorUtil.THEME_MEXICO:
                mMexicoLayout.setBackground(new ColorUtil(getContext()).getLightColorDrawable());
                break;
            case ColorUtil.THEME_ARGENTINA:
                mArgentinaLayout.setBackground(new ColorUtil(getContext()).getLightColorDrawable());
                break;
        }
    }
}
