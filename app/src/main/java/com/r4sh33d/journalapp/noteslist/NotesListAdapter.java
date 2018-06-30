package com.r4sh33d.journalapp.noteslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.r4sh33d.journalapp.models.Note;

import java.util.ArrayList;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesItemHolder> {
    private ArrayList<Note> notesList;

    public NotesListAdapter(ArrayList<Note> notesList) {
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public NotesItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesItemHolder holder, int position) {

    }

    void swapData(ArrayList<Note> newNotesList){
        notesList = newNotesList;
        notifyDataSetChanged();
    }

    void addNote(Note note){
        this.notesList.add(note);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    class NotesItemHolder extends RecyclerView.ViewHolder {

        public NotesItemHolder(View itemView) {
            super(itemView);
        }
    }
}
