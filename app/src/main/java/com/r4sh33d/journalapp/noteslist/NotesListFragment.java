package com.r4sh33d.journalapp.noteslist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r4sh33d.journalapp.R;
import com.r4sh33d.journalapp.models.Note;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesListFragment extends Fragment {
    private DatabaseReference mNotesReference;
    FirebaseUser user;
    NotesListAdapter notesListAdapter;

    ChildEventListener mNotesChildEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Note newNote = dataSnapshot.getValue(Note.class);
            notesListAdapter.addNote(newNote);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public NotesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notesListAdapter = new NotesListAdapter(new ArrayList<>());
        user = FirebaseAuth.getInstance().getCurrentUser();
        mNotesReference = FirebaseDatabase.getInstance().getReference("notes").child(user.getUid());

    }
}
