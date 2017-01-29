package com.cameronvoell.listo.adapters;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.fragments.SuggestedWordCaptureFragment;

import java.util.HashSet;

/**
 * Created by cameronvoell on 1/15/17.
 */

public class SuggestedWordCursorAdapter extends CursorAdapter {

	HashSet<Integer> mCheckedIndices;
	SuggestedWordCaptureFragment mFragment;


	/**
	 * Constructor that always enables auto-requery.
	 *
	 * @param context The context
	 * @param c       The cursor from which to get the data.
	 * This option is discouraged, as it results in Cursor queries
	 * being performed on the application's UI thread and thus can cause poor
	 * responsiveness or even Application Not Responding errors.  As an alternative,
	 * use {@link LoaderManager} with a {@link CursorLoader}.
	 */
	public SuggestedWordCursorAdapter(Context context, Cursor c, SuggestedWordCaptureFragment fragment) {
		super(context, c, false);

		mCheckedIndices = new HashSet<>();
		mFragment = fragment;
	}

	/**
	 * Makes a new view to hold the data pointed to by cursor.
	 *
	 * @param context Interface to application's global information
	 * @param cursor  The cursor from which to get the data. The cursor is already
	 *                moved to the correct position.
	 * @param parent  The parent to which the new view is attached to
	 * @return the newly created view.
	 */
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return LayoutInflater.from(context).inflate(R.layout.suggested_word_list_item, parent, false);

	}

	/**
	 * Bind an existing view to the data pointed to by cursor
	 *
	 * @param view    Existing view, returned earlier by newView
	 * @param context Interface to application's global information
	 * @param cursor  The cursor from which to get the data. The cursor is already
	 */
	@Override
	public void bindView(View view, Context context, final Cursor cursor) {
		// Find fields to populate in inflated template
		TextView freqTextView = (TextView) view.findViewById(R.id.freq);
		TextView spanishTextView = (TextView) view.findViewById(R.id.spanish_word);
		TextView englishDefTextView = (TextView)view.findViewById(R.id.english_def);
		TextView wordTypeTextView = (TextView)view.findViewById(R.id.type);
		final CheckBox shouldAddCheckBox = (CheckBox)view.findViewById(R.id.shouldAdd);

		// Extract properties from cursor
		int freqColumn = cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_FREQ_RANK);
		int spanishWordColumn = cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_FREQ_SPANISH_WORD);


		wordTypeTextView.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_FREQ_TYPE)));
		englishDefTextView.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_FREQ_ENG_DEF)));



		final int freq = cursor.getInt(freqColumn);
		String word = cursor.getString(spanishWordColumn);
		// Populate fields with extracted properties
		if (freq != 9999 && freq != -1)freqTextView.setText("" + freq);
		else freqTextView.setText("");
		spanishTextView.setText(word);

		if (mCheckedIndices.contains(freq)) {
			shouldAddCheckBox.setChecked(true);
		} else {
			shouldAddCheckBox.setChecked(false);
		}

//		shouldAddCheckBox.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mFragment.updateNumSelected(shouldAddCheckBox.isChecked());
//				if (shouldAddCheckBox.isChecked()) {
//					mCheckedIndices.add(freq);
//				} else {
//					mCheckedIndices.remove(freq);
//				}
//			}
//		});
	}

	public HashSet<Integer> getCheckedIndices() {
		return mCheckedIndices;
	}

	public void clearSelections() {
//		mCheckedIndices.clear();
//		mFragment.zeroNumSelected();
	}
}
