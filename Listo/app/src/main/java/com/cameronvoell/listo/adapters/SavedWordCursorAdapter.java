package com.cameronvoell.listo.adapters;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.database.DatabaseHelper;

/**
 * Created by cameronvoell on 1/15/17.
 */
public class SavedWordCursorAdapter extends CursorAdapter {
	/**
	 * Constructor that always enables auto-requery.
	 *
	 * @param context The context
	 * @param c       The cursor from which to get the data.
	 * @deprecated This option is discouraged, as it results in Cursor queries
	 * being performed on the application's UI thread and thus can cause poor
	 * responsiveness or even Application Not Responding errors.  As an alternative,
	 * use {@link LoaderManager} with a {@link CursorLoader}.
	 */
	public SavedWordCursorAdapter(Context context, Cursor c) {
		super(context, c, false);
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
		return LayoutInflater.from(context).inflate(R.layout.vocab_word_list_item, parent, false);

	}

	/**
	 * Bind an existing view to the data pointed to by cursor
	 *
	 * @param view    Existing view, returned earlier by newView
	 * @param context Interface to application's global information
	 * @param cursor  The cursor from which to get the data. The cursor is already
	 */
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// Find fields to populate in inflated template
		TextView freqTextView = (TextView) view.findViewById(R.id.freq);
		TextView spanishTextView = (TextView) view.findViewById(R.id.spanish_word);
		TextView englishDefTextView = (TextView)view.findViewById(R.id.english_def);
		TextView wordTypeTextView = (TextView)view.findViewById(R.id.type);
		CheckBox hasCardView = (CheckBox)view.findViewById(R.id.hasCard);
		// Extract properties from cursor
		int freqColumn = cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_FREQ_RANK);
		int spanishWordColumn = cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SPANISH_WORD);
		int hasCard = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_HAS_CARD));

		if (hasCard > 1) hasCardView.setChecked(true);
		else hasCardView.setChecked(false);

		wordTypeTextView.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TYPE)));
		englishDefTextView.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_ENG_DEF)));



		int freq = cursor.getInt(freqColumn);
		String word = cursor.getString(spanishWordColumn);
		// Populate fields with extracted properties
		if (freq != 9999 && freq != -1)freqTextView.setText("" + freq);
		else freqTextView.setText("");
		spanishTextView.setText(word);
	}
}
