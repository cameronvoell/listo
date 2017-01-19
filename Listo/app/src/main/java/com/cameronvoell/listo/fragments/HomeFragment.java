package com.cameronvoell.listo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;


public class HomeFragment extends Fragment {

    private TextView mNumberWordsSavedView;
    private TextView mNumberScheduledView;
    private TextView mNumberMemorizedView;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mNumberWordsSavedView = (TextView)v.findViewById(R.id.words_saved);
        mNumberScheduledView = (TextView)v.findViewById(R.id.words_scheduled);
        mNumberMemorizedView = (TextView)v.findViewById(R.id.words_memorized);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseHelper helper = new DatabaseHelper(getContext());
        int numSavedToday = helper.getNumWordsSavedToday();
        int numMemorized = helper.getNumWordsMemorized();
        int numSavedNotYetMemorized = helper.getNumWordsNotYetMemorized();

        String text = "<font color=#C84C42>" + numSavedToday + "</font> <font color=#333333> words saved today</font>";
        mNumberWordsSavedView.setText(Html.fromHtml(text));

        String text2 = "<font color=#C84C42>" + numMemorized + "</font> <font color=#333333> words memorized</font>";
        mNumberMemorizedView.setText(Html.fromHtml(text2));

        String text3 = "<font color=#C84C42>" + numSavedNotYetMemorized + "</font> <font color=#333333> words awaiting review</font>";
        mNumberScheduledView.setText(Html.fromHtml(text3));

    }

}
