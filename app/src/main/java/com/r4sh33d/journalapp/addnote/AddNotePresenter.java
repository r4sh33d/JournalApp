package com.r4sh33d.journalapp.addnote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r4sh33d.journalapp.models.Note;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rasheed on 30/06/18.
 */

public class AddNotePresenter implements AddNotesContract.Presenter {
    private static final String TAG = AddNotePresenter.class.getSimpleName();
    private AddNotesContract.View view;
    DatabaseReference mNotesReference;
    FirebaseUser user;

    AddNotePresenter(AddNotesContract.View view) {
        this.view = view;
        user = FirebaseAuth.getInstance().getCurrentUser();
        mNotesReference = FirebaseDatabase.getInstance().getReference("journalApp")
                .child(user.getUid()).child("notes");
    }

    @Override
    public void start() {

    }

    @Override
    public void addNote(Note note) {
        DatabaseReference noteReference = mNotesReference.push();
        note.uniqueKey = noteReference.getKey();
        noteReference.setValue(note, (databaseError, databaseReference) -> {
            if (view != null ) {
                if (databaseError != null) {
                    Log.d(TAG, "Note could not be saved " + databaseError.getMessage());
                    view.showError("Unable to save note, Please try again");
                } else {
                    view.onNotesSaved(note);
                }
            }
        });
    }

    @Override
    public void editNote(Note note) {
        Map<String, Object> notesUpdate = new HashMap<>();
        notesUpdate.put(note.uniqueKey, note);
        mNotesReference.updateChildren(notesUpdate,
                (databaseError, databaseReference) -> {
                    if (view != null) {
                        if (databaseError != null) {
                            Log.d(TAG, "Note could not be edited " + databaseError.getMessage());
                            view.showError("Unable to edit note, Please try again");
                        } else {
                            view.onNotesSuccessfullyEdited(note);
                        }
                    }
                });
    }

    @Override
    public void detachListeners() {
        DatabaseReference noteReference = mNotesReference.push();
    }

}
