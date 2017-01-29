package com.cameronvoell.listo.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.activities.MainActivity;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.util.ColorUtil;


public class HomeFragment extends Fragment {

    private TextView mNumberWordsSavedView;
    private TextView mNumberScheduledView;
    private TextView mNumberWordsAwaitingSentencePracticeView;

    private RelativeLayout rLayout1;
    private RelativeLayout rLayout2;
    private RelativeLayout rLayout3;

    private TextView mViewBg;

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

        v.setBackground(ContextCompat.getDrawable(getContext(), getBackgroundDrawableFromTheme()));

        mNumberWordsSavedView = (TextView)v.findViewById(R.id.words_saved);
        mNumberScheduledView = (TextView)v.findViewById(R.id.words_scheduled);
        mNumberWordsAwaitingSentencePracticeView = (TextView)v.findViewById(R.id.words_awaiting_sentence_practice);
        mViewBg = (TextView)v.findViewById(R.id.viewBg);

        rLayout1 = (RelativeLayout) v.findViewById(R.id.saved_box);
        rLayout2 = (RelativeLayout) v.findViewById(R.id.words_awaiting_memorization);
        rLayout3 = (RelativeLayout) v.findViewById(R.id.practice_ready);

        return v;
    }

    private int getBackgroundDrawableFromTheme() {
        SharedPreferences pref = getContext().getSharedPreferences(
                getContext().getString(R.string.listo_shared_preferences), 0);
        int theme = pref.getInt(ColorUtil.KEY_PREFERENCES_THEME, 1);
        switch (theme) {
            case ColorUtil.THEME_COLOMBIA:
                return R.drawable.colombia_one_scaled;
            case ColorUtil.THEME_SPAIN:
                return R.drawable.guernica_scaled;
            case ColorUtil.THEME_MEXICO:
                return R.drawable.mexico_one_scaled;
            case ColorUtil.THEME_ARGENTINA:
                return R.drawable.argentina_one_scaled;
            default:
                return R.drawable.guernica_scaled;
        }
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

        String text3 = "<font color=#C84C42>" + numWordsAwaitingSentencePractice + "</font><font color=#333333> words ready for practice</font>";
        mNumberWordsAwaitingSentencePracticeView.setText(Html.fromHtml(text3));
    }

    public void viewBackground() {

        if (mViewBg.getText().toString().equals("(view)")) {
            Animation translateAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.translate);

            rLayout1.startAnimation(translateAnimation);
            rLayout2.startAnimation(translateAnimation);
            rLayout3.startAnimation(translateAnimation);

            rLayout1.setVisibility(View.GONE);
            rLayout2.setVisibility(View.GONE);
            rLayout3.setVisibility(View.GONE);

            mViewBg.setText("(return)");
        } else {
            Animation translateAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.translate_back);

            rLayout1.setVisibility(View.VISIBLE);
            rLayout2.setVisibility(View.VISIBLE);
            rLayout3.setVisibility(View.VISIBLE);

            rLayout1.startAnimation(translateAnimation);
            rLayout2.startAnimation(translateAnimation);
            rLayout3.startAnimation(translateAnimation);

            mViewBg.setText("(view)");
            ((MainActivity)getActivity()).showFab();
        }



    }

    public void resetActionBoxes() {
        if (mViewBg.getText().toString().equals("(return)")) {
            viewBackground();
        }
    }
}
