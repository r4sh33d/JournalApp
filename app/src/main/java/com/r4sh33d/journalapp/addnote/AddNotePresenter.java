package com.r4sh33d.journalapp.addnote;

import com.r4sh33d.journalapp.models.Note;

/**
 * Created by rasheed on 3/1/18.
 */

public class AddNotePresenter implements AddNotesContract.Presenter {
    private AddNotesContract.View view;


    AddNotePresenter(AddNotesContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void addNote(Note note) {

    }

    @Override
    public void editNote(Note note) {

    }


}
