package com.cameronvoell.listo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class VocabWordListFragment extends ListFragment {


    // TODO: Rename and change types of parameters
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
        //setListAdapter(new SavedWordCursorAdapter(getContext(), new DatabaseHelper(getContext()).getSavedWordsCursor()));
    }

    @Override
    public void onResume() {
        super.onResume();
        //CursorAdapter c = (CursorAdapter)getListAdapter();
        //c.notifyDataSetChanged();
        //setListAdapter(new SavedWordCursorAdapter(getContext(), new DatabaseHelper(getContext()).getSavedWordsCursor()));
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Intent intent = new Intent(getContext(), CardCreatorActivity.class);
        //intent.putExtra("position", position);
//        intent.putExtra("sortOption", mSortOption);
//        intent.putExtra("filterOption", mFilterOption);

       // startActivity(intent);



//        if (null != mListener) {
//            // Notify the active callbacks interface (the activity, if the
//            // fragment is attached to one) that an item has been selected.
//            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
//        }
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
