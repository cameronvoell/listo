package com.cameronvoell.listo.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.activities.EditSavedWordActivity;
import com.cameronvoell.listo.adapters.SavedWordCursorAdapter;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.model.SavedWord;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class VocabWordListFragment extends ListFragment {

    private boolean mSorted = false;
    private SavedWordCursorAdapter mAdapter;

    public static VocabWordListFragment newInstance() {
        VocabWordListFragment fragment = new VocabWordListFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VocabWordListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new SavedWordCursorAdapter(getContext(), new DatabaseHelper(getContext()).getSavedWordsCursor());
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
        View v = inflater.inflate(R.layout.fragment_vocab_word_list, container, false);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter = new SavedWordCursorAdapter(getContext(), new DatabaseHelper(getContext()).getSavedWordsCursor());
        setListAdapter(mAdapter);
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        SavedWord word = new DatabaseHelper(getContext()).cursorToSavedWord((Cursor) mAdapter.getItem(position));

        Intent intent = new Intent(getContext(), EditSavedWordActivity.class);
        intent.putExtra(EditSavedWordActivity.SAVED_WORD, word);

        startActivity(intent);


    }

    public void sort() {
        if (mSorted) {
            mAdapter = new SavedWordCursorAdapter(getContext(), new DatabaseHelper(getContext()).getSavedWordsCursor());
        } else {
            mAdapter = new SavedWordCursorAdapter(getContext(), new DatabaseHelper(getContext()).getSavedWordsCursorSortedByFrequency());
        }
        setListAdapter(mAdapter);
        mSorted = !mSorted;
    }

    public void filter() {
    }

    public void custom() {
    }

    public void search() {
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
