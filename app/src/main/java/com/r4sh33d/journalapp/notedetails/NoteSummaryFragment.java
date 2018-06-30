package com.r4sh33d.journalapp.notedetails;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r4sh33d.journalapp.R;
import com.r4sh33d.journalapp.activities.MainActivity;
import com.r4sh33d.journalapp.models.Note;
import com.r4sh33d.journalapp.utility.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteSummaryFragment extends Fragment {

    @BindView(R.id.note_title_textview)
    TextView noteTitle;
    @BindView(R.id.note_body_textview)
    TextView noteBody;
    @BindView(R.id.note_time_created_textview)
    TextView timeCreated;
    private static final String FIREBASE_NOTE_KEY = NoteSummaryFragment.class.getSimpleName();
    private DatabaseReference mNotesReference;
    FirebaseUser user;
    Note currentNote;
    public static NoteSummaryFragment newInstance(String firebaseNoteKey) {
        Bundle args = new Bundle();
        args.putString(FIREBASE_NOTE_KEY, firebaseNoteKey);
        NoteSummaryFragment fragment = new NoteSummaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public NoteSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note_summary, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setDrawerIconToBack();
        String notesKey = getArguments().getString(FIREBASE_NOTE_KEY);
        user = FirebaseAuth.getInstance().getCurrentUser();
        mNotesReference = FirebaseDatabase.getInstance().getReference("journalApp")
                .child(user.getUid()).child("notes").child(notesKey);
        mNotesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentNote = dataSnapshot.getValue(Note.class);
                updatePageDetails();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updatePageDetails() {
        timeCreated.setText(Utils.getRelativeSentFromMessageWithTime(currentNote.timeCreated));
        noteTitle.setText(currentNote.title);
        noteBody.setText(currentNote.body);
    }
}
