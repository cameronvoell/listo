package com.cameronvoell.listo.fragments;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.adapters.SavedWordCursorAdapter;
import com.cameronvoell.listo.adapters.SuggestedWordCursorAdapter;
import com.cameronvoell.listo.database.DatabaseHelper;

/**
 * Created by cameronvoell on 1/15/17.
 */

public class SuggestedWordCaptureFragment extends ListFragment {

	private boolean mSorted = false;
	private TextView mNumSelectedTextView;
	private SuggestedWordCursorAdapter mAdapter;
	private DatabaseHelper mDatabaseHelper;

	public static SuggestedWordCaptureFragment newInstance() {
		SuggestedWordCaptureFragment fragment = new SuggestedWordCaptureFragment();
		return fragment;
	}

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public SuggestedWordCaptureFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDatabaseHelper = new DatabaseHelper(getContext());
		mAdapter = new SuggestedWordCursorAdapter(getContext(), mDatabaseHelper.getSuggestedWordsCursor(), this);
		setListAdapter(mAdapter);
	}

	/**
	 * Provide default implementation to return a simple list view.  Subclasses
	 * can override to replace with their own layout.  If doing so, the
	 * returned view hierarchy <em>must</em> have a ListView whose id
	 * is {@link android.R.id#list android.R.id.list} and can optionally
	 * have a sibling view id {@link android.R.id#empty android.R.id.empty}
	 * that is to be shown when the list is empty.
	 *
	 * <p>If you are overriding this method with your own custom content,
	 * consider including the standard layout {@link android.R.layout#list_content}
	 * in your layout file, so that you continue to retain all of the standard
	 * behavior of ListFragment.  In particular, this is currently the only
	 * way to have the built-in indeterminant progress state be shown.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_suggested_word_list, container, false);

		mNumSelectedTextView = (TextView) v.findViewById(R.id.numSelected);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle b) {
		super.onActivityCreated(b);

		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

	}

	@Override
	public void onResume() {
		super.onResume();
		CursorAdapter c = (CursorAdapter)getListAdapter();
		c.notifyDataSetChanged();
		mAdapter = new SuggestedWordCursorAdapter(getContext(), new DatabaseHelper(getContext()).getSuggestedWordsCursor(), this);
		setListAdapter(mAdapter);
	}



	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}

	public void sort() {
		if (mSorted) {
			setListAdapter(new SavedWordCursorAdapter(getContext(), mDatabaseHelper.getSavedWordsCursor()));
		} else {
			setListAdapter(new SavedWordCursorAdapter(getContext(), mDatabaseHelper.getSavedWordsCursorSortedByFrequency()));
		}
		mSorted = !mSorted;
	}

	public void filter() {
	}

	public void custom() {
	}

	public void clear() {
		mAdapter.clearSelections();
		mAdapter.notifyDataSetChanged();
	}

	public void updateNumSelected(boolean isChecked) {
		int numSelected = Integer.valueOf(mNumSelectedTextView.getText().toString());
		if (isChecked) {
			mNumSelectedTextView.setText(String.valueOf(++numSelected));
		} else {
			mNumSelectedTextView.setText(String.valueOf(--numSelected));
		}
	}

	public void zeroNumSelected() {
		mNumSelectedTextView.setText("0");
	}

	public void captureWords() {
		int numToAdd = mAdapter.getCheckedIndices().size();
		mDatabaseHelper.saveVocabWordsWithFreq(mAdapter.getCheckedIndices());
		mAdapter = new SuggestedWordCursorAdapter(getContext(), mDatabaseHelper.getSuggestedWordsCursor(), this);
		setListAdapter(mAdapter);
		zeroNumSelected();
		Toast.makeText(getContext(), numToAdd + " words added!", Toast.LENGTH_SHORT).show();
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		public void onFragmentInteraction(String id);
	}

}
