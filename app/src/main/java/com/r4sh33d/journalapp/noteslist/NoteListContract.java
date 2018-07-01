package com.r4sh33d.journalapp.noteslist;

import com.r4sh33d.journalapp.base.BaseContract;
import com.r4sh33d.journalapp.models.Note;

public interface NoteListContract {

    interface Presenter {
        void start();

        void LoadNotes();
    }

    interface View extends BaseContract.view {

        void moveToNextStep();

        void showNotes();
    }
}
