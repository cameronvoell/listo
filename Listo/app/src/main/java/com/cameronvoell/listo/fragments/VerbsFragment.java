package com.cameronvoell.listo.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.util.ColorUtil;


public class VerbsFragment extends Fragment {

    private static final String PREF_NUM_TENSES_MASTERED = "PREF_NUM_TENSES_MASTERED";
    private TextView mVerbsReviewedTextView;
    private TextView mTensesMasteredTextView;

    private RelativeLayout rLayout1;
    private RelativeLayout rLayout2;

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

        View v = inflater.inflate(R.layout.fragment_verbs, container, false);

        mVerbsReviewedTextView = (TextView)v.findViewById(R.id.verbs_reviewed);
        mTensesMasteredTextView = (TextView)v.findViewById(R.id.tenses_mastered);

        rLayout1 = (RelativeLayout)v.findViewById(R.id.verbs_reviewed_layout);
        rLayout2 = (RelativeLayout)v.findViewById(R.id.tenses_mastered_layout);

        updateBackgroundColor();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void updateBackgroundColor() {
        if (getView() != null) {
            ColorUtil colorUtil = new ColorUtil(getContext());
            getView().setBackground(colorUtil.getLightColorTwoDrawable());

            DatabaseHelper helper = new DatabaseHelper(getContext());
            int numVerbsReviewed = helper.getNumVerbsReviewed();
            int numVerbsTotal = helper.getNumFreqVerbs();
            int numTensesMastered = getContext().getSharedPreferences(getString(R.string.listo_shared_preferences), 0)
                    .getInt(PREF_NUM_TENSES_MASTERED, 0);

            String text = "<font color=#C84C42>" + numVerbsReviewed + "</font><font color=#333333>/" + numVerbsTotal + " verbs reviewed</font>";
            mVerbsReviewedTextView.setText(Html.fromHtml(text));

            String text2 = "<font color=#C84C42>" + numTensesMastered + "</font><font color=#333333> tenses mastered</font>";
            mTensesMasteredTextView.setText(Html.fromHtml(text2));

//            rLayout1.setBackground(colorUtil.getLightColorDrawable());
//            rLayout2.setBackground(colorUtil.getLightColorDrawable());

        }
    }
}
