package com.cameronvoell.listo.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.util.ColorUtil;


public class VerbsFragment extends Fragment {

    private View mView;

    public static VerbsFragment newInstance() {
        VerbsFragment fragment = new VerbsFragment();
        return fragment;
    }

    public VerbsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_verbs, container, false);
        mView.setBackground(new ColorUtil(getContext()).getLightColorTwoDrawable());

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void updateBackgroundColor() {
        if (getView() != null)getView().setBackground(new ColorUtil(getContext()).getLightColorTwoDrawable());
    }
}
