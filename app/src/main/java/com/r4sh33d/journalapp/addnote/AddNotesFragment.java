package com.r4sh33d.journalapp.addnote;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.r4sh33d.journalapp.R;
import com.r4sh33d.journalapp.activities.MainActivity;
import com.r4sh33d.journalapp.base.BaseFragment;
import com.r4sh33d.journalapp.models.Note;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */

public class AddNotesFragment extends BaseFragment implements AddNotesContract.View {
    private static final String TAG = AddNotesFragment.class.getSimpleName();
    private static final String ARGS_NOTE_KEY = "note_key";
    private AddNotePresenter addNotesPresenter;

    @BindView(R.id.body_edittext)
    EditText noteBodyEditText;
    @BindView(R.id.title_edittext)
    EditText noteTitleEditText;
    boolean isEdit = false;
    private long noteTimeCreated;


    public static AddNotesFragment newInstance (Note note) {
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
            isEdit = true;
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
        noteBodyEditText.setText(note.body);
        noteTitleEditText.setText(note.title);
        noteTimeCreated = note.timeCreated;
    }


    @OnClick(R.id.save_button)
    void onSaveButtonClick() {
        //Edit the note or save for the first time based on where the user is comming from
        String title = noteTitleEditText.getText().toString();
        String body = noteBodyEditText.getText().toString();
        if (TextUtils.isEmpty(title)) {
            showToast("Enter a valid title to proceed");
            return;
        }
        if (TextUtils.isEmpty(body)) {
            showToast("Enter the note's body to proceed");
            return;
        }
        Note noteToSave = new Note(title, body,
                noteTimeCreated == 0 ? System.currentTimeMillis() : noteTimeCreated,
                System.currentTimeMillis());

        if (isEdit) {
            Note editingNote = getArguments().getParcelable(ARGS_NOTE_KEY);
            noteToSave.uniqueKey = editingNote.uniqueKey;
            addNotesPresenter.editNote(noteToSave);
        } else {
            addNotesPresenter.addNote(noteToSave);
        }
        getActivity().getSupportFragmentManager().popBackStack();
    }


    @Override
    public void moveToNextStep() {

    }

    @Override
    public void onNotesSaved(Note note) {
        showToast("Note successfully saved");

    }

    @Override
    public void onNotesSuccessfullyEdited(Note note) {
        showToast("Note successfully Edited");
    }

}
