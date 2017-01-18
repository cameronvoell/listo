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


public class VerbsFragment extends Fragment {

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

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
