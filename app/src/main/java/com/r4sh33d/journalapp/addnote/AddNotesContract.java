package com.r4sh33d.journalapp.addnote;

import com.r4sh33d.journalapp.models.Note;

public interface AddNotesContract {

    interface Presenter  {
        void start();
        public void addNote(Note note);
        public void editNote(Note note);
    }

    interface View  {
        void moveToNextStep();
    }
}
