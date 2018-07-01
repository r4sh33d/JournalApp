package com.r4sh33d.journalapp.noteslist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.r4sh33d.journalapp.activities.MainActivity;
import com.r4sh33d.journalapp.addnote.AddNotesFragment;
import com.r4sh33d.journalapp.base.BaseFragment;
import com.r4sh33d.journalapp.models.Note;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesListFragment extends BaseFragment implements  NoteListContract.View {
    private DatabaseReference mNotesReference;
    FirebaseUser user;
    NotesListAdapter notesListAdapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

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
        View view =  inflater.inflate(R.layout.fragment_notes_list, container, false);
        ButterKnife.bind(this , view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setDrawerIconToHome();
        setToolbarTitle(getString(R.string.note_lists));
        notesListAdapter = new NotesListAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(notesListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        user = FirebaseAuth.getInstance().getCurrentUser();
        mNotesReference = FirebaseDatabase.getInstance().getReference("journalApp")
                .child(user.getUid()).child("notes");
        mNotesReference.addChildEventListener(mNotesChildEventListener);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mNotesReference.removeEventListener(mNotesChildEventListener);
    }


    @OnClick(R.id.fab)
    public void onClickFab(){
        navigateToFragmentWithBackStack(AddNotesFragment.newInstance(null));
    }

    @Override
    public void moveToNextStep() {

    }

    @Override
    public void showNotes() {

    }
}
