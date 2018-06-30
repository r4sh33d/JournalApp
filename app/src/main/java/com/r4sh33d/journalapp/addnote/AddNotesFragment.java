package com.r4sh33d.journalapp.addnote;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.r4sh33d.journalapp.R;
import com.r4sh33d.journalapp.activities.MainActivity;
import com.r4sh33d.journalapp.base.BaseFragment;
import com.r4sh33d.journalapp.models.Note;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNotesFragment extends BaseFragment implements AddNotesContract.View {
    private static final String TAG = AddNotesFragment.class.getSimpleName();
    private static final String ARGS_NOTE_KEY = "note_key";
    private AddNotePresenter addNotesPresenter;

    @BindView(R.id.note_body_textview)
    TextView noteBodyTextView;
    @BindView(R.id.note_title_textview)
    TextView noteTitleText;


    public static AddNotesFragment newInstance(Note note) {
        Bundle args = new Bundle();
        args.putParcelable(ARGS_NOTE_KEY, note);
        AddNotesFragment fragment = new AddNotesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AddNotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setDrawerIconToBack();
        addNotesPresenter = new AddNotePresenter(this);
        if (getArguments() != null && getArguments().getParcelable(ARGS_NOTE_KEY) != null) {
            //We want to edit note
            setToolbarTitle("Edit Note");
            Note note = getArguments().getParcelable(ARGS_NOTE_KEY);

            //prepopulate the fields with the details of the note to edit
            prepopulateFields(note);
        } else {
            // We want to add new note
            setToolbarTitle("Add new note ");
        }
    }

    private void prepopulateFields(Note note) {

    }


    @Override
    public void moveToNextStep() {

    }
}
