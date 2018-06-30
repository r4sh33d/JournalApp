package com.r4sh33d.journalapp.addnote;

import com.r4sh33d.journalapp.base.BaseContract;
import com.r4sh33d.journalapp.models.Note;

public interface AddNotesContract {

    interface Presenter {
        void start();

        public void addNote(Note note);

        public void editNote(Note note);

        public  void detachListeners();
    }

    interface View  extends BaseContract.view{
        void moveToNextStep();

        void onNotesSaved(Note note);

        void onNotesSuccessfullyEdited(Note note);
    }
}
