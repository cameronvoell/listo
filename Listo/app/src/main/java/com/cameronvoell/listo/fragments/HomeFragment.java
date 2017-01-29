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
    private TextView mNumberWordsAwaitingSentencePracticeView;

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
        mNumberWordsAwaitingSentencePracticeView = (TextView)v.findViewById(R.id.words_awaiting_sentence_practice);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseHelper helper = new DatabaseHelper(getContext());
        int numPruned = helper.getNumFreqWordsPruned();
        int numSavedNotYetMemorized = helper.getNumWordsNotYetMemorized();
        int numWordsAwaitingSentencePractice = helper.getNumWordsMemorized();

        String text = "<font color=#C84C42>" + numPruned + "</font><font color=#333333>/5000 words pruned</font>";
        mNumberWordsSavedView.setText(Html.fromHtml(text));

        String text2 = "<font color=#C84C42>" + numSavedNotYetMemorized + "</font><font color=#333333> words awaiting memorization</font>";
        mNumberScheduledView.setText(Html.fromHtml(text2));

        String text3 = "<font color=#C84C42>" + numWordsAwaitingSentencePractice + "</font><font color=#333333> words awaiting sentence practice</font>";
        mNumberWordsAwaitingSentencePracticeView.setText(Html.fromHtml(text3));

    }

}
