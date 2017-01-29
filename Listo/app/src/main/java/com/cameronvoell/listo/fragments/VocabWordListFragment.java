package com.cameronvoell.listo.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.activities.EditSavedWordActivity;
import com.cameronvoell.listo.adapters.SavedWordCursorAdapter;
import com.cameronvoell.listo.database.DatabaseHelper;
import com.cameronvoell.listo.model.SavedWord;
import com.cameronvoell.listo.util.ColorUtil;

import org.w3c.dom.Text;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class VocabWordListFragment extends ListFragment {

    public static final int FILTER_OPTION_MY_SAVED_WORDS = 0;
    public static final int FILTER_OPTION_WORDS_NEEDING_REVIEW = 1;
    public static final int FILTER_OPTION_VERBS_READY_FOR_PRACTICE = 2;

    public static final String PREF_VOCAB_LIST_FILTER = "PREF_VOCAB_LIST_FILTER";

    private SavedWordCursorAdapter mAdapter;
    private Button mFilterButton;
    private TextView mFilterNameTextView;

    private boolean mFiltered = false;
    private int mFilterOption = FILTER_OPTION_WORDS_NEEDING_REVIEW;
    private SharedPreferences mPreferences;

    private DatabaseHelper mDatabaseHelper;


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

        mDatabaseHelper = new DatabaseHelper(getContext());
        mAdapter = new SavedWordCursorAdapter(getContext(), mDatabaseHelper.getSavedWordsCursor(mFilterOption));
        setListAdapter(mAdapter);

        mPreferences = getContext().getSharedPreferences(getContext().getString(
                R.string.listo_shared_preferences), 0);
        mFilterOption = mPreferences.getInt(PREF_VOCAB_LIST_FILTER, FILTER_OPTION_WORDS_NEEDING_REVIEW);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vocab_word_list, container, false);
        RelativeLayout buttons = (RelativeLayout) v.findViewById(R.id.buttons);
        buttons.setBackground(new ColorUtil(getContext()).getLightColorTwoDrawable());
        mFilterButton = (Button) v.findViewById(R.id.filterButton);
        mFilterNameTextView = (TextView)v.findViewById(R.id.filterName);
        mFilterNameTextView.setText(getFilterNameString());
        mFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(getContext(), mFilterButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.filter_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String choice = item.getTitle().toString();
                        switch (choice) {
                            case "Words Needing Review":
                                mFilterOption = FILTER_OPTION_WORDS_NEEDING_REVIEW;
                                break;
                            case "My Saved Words":
                                mFilterOption = FILTER_OPTION_MY_SAVED_WORDS;
                                break;
                            case "Verbs Ready for Practice":
                                mFilterOption = FILTER_OPTION_VERBS_READY_FOR_PRACTICE;
                                break;
                        }
                        updateFilter(mFilterOption);
                        return true;
                    }
                });

                popup.show(); //showing popup menu

            }
        });

        return v;
    }

    private Spanned getFilterNameString() {
        int num = mAdapter.getCount();
        String text = "";

        switch (mFilterOption) {
            case FILTER_OPTION_MY_SAVED_WORDS:
                text = "<font color=#C84C42>" + num + "</font><font color=#333333> captured words</font>";
                break;
            case FILTER_OPTION_WORDS_NEEDING_REVIEW:
                text = "<font color=#C84C42>" + num + "</font><font color=#333333> words needing review</font>";
                break;
            case FILTER_OPTION_VERBS_READY_FOR_PRACTICE:
                text = "<font color=#C84C42>" + num + "</font><font color=#333333> verbs ready for practice</font>";
                break;
        }

        return Html.fromHtml(text);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPreferences = getContext().getSharedPreferences(getContext().getString(
                R.string.listo_shared_preferences), 0);
        mFilterOption = mPreferences.getInt(PREF_VOCAB_LIST_FILTER, 1);
        mAdapter = new SavedWordCursorAdapter(getContext(), new DatabaseHelper(getContext()).getSavedWordsCursor(mFilterOption));
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

    public void filter() {
        if (mFiltered) {
            setListAdapter(new SavedWordCursorAdapter(getContext(), new DatabaseHelper(getContext()).getSavedWordsCursor(mFilterOption)));
        } else {
            setListAdapter(new SavedWordCursorAdapter(getContext(), new DatabaseHelper(getContext()).getSavedWordsCursorFilteredByNotMemorized()));
        }
        mFiltered = !mFiltered;
    }

    public void updateFilter(int filterOption) {
        mFilterOption = filterOption;
        mPreferences = getContext().getSharedPreferences(getContext().getString(
                R.string.listo_shared_preferences), 0);
        mPreferences.edit().putInt(PREF_VOCAB_LIST_FILTER, mFilterOption).apply();
        mAdapter.swapCursor(mDatabaseHelper.getSavedWordsCursor(mFilterOption));
        mFilterNameTextView.setText(getFilterNameString());
    }

    public void updateFilter() {
        updateFilter(mFilterOption);
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
