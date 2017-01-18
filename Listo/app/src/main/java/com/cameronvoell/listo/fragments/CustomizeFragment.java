package com.cameronvoell.listo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cameronvoell.listo.R;


public class CustomizeFragment extends Fragment {

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

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
