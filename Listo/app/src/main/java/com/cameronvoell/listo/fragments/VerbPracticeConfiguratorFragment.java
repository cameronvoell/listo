package com.cameronvoell.listo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.model.VerbPracticeSession;


public class VerbPracticeConfiguratorFragment extends Fragment {

	private static final String PREF_NUM_PRACTICE_SENTENCES = "PREF_NUM_PRACTICE_SENTENCES";

	public static final int TENSE_OPTION_PRESENTE = 0;
	public static final int TENSE_OPTION_PRETERITO = 1;
	public static final int TENSE_OPTION_IMPERFECTO = 2;

	DatabaseHelper mDatabaseHelper;
	private EditText mNumSentences;
	private Button mSelectTensesButton;
	private int mTenseSelection = TENSE_OPTION_PRESENTE;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_verb_practice_configurator, container, false);

		mNumSentences = (EditText) v.findViewById(R.id.numSentences);
		mSelectTensesButton = (Button) v.findViewById(R.id.selectTenseButton);

		mSelectTensesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Creating the instance of PopupMenu
				PopupMenu popup = new PopupMenu(getActivity(), mSelectTensesButton);
				//Inflating the Popup using xml file
				popup.getMenuInflater()
						.inflate(R.menu.tense_menu, popup.getMenu());

				//registering popup with OnMenuItemClickListener
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					public boolean onMenuItemClick(MenuItem item) {
						String choice = item.getTitle().toString();
						switch (choice) {
							case "PRESENTE":
								mTenseSelection = TENSE_OPTION_PRESENTE;
								break;
							case "PRETERITO":
								mTenseSelection = TENSE_OPTION_PRETERITO;
								break;
							case "IMPERFECTO":
								mTenseSelection = TENSE_OPTION_IMPERFECTO;
								break;
						}
						updateTenseSelection(choice);
						return true;
					}
				});

				popup.show(); //showing popup menu

			}
		});

		return v;
	}

	private void updateTenseSelection(String tense) {
		mSelectTensesButton.setText(tense);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mDatabaseHelper = new DatabaseHelper(getActivity());


	}

	public VerbPracticeSession createVerbPracticeSession() {
		int numSentences = Integer.parseInt(mNumSentences.getText().toString());

		return new VerbPracticeSession(numSentences, mDatabaseHelper,
				mTenseSelection, getActivity());
	}
}
