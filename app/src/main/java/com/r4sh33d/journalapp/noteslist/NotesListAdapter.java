package com.r4sh33d.journalapp.noteslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.r4sh33d.journalapp.R;
import com.r4sh33d.journalapp.models.Note;
import com.r4sh33d.journalapp.utility.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesItemHolder> {
    private ArrayList<Note> notesList;

    public NotesListAdapter(ArrayList<Note> notesList) {
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public NotesItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes_list, parent, false);
        return new NotesItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesItemHolder holder, int position) {
        Note currentNote = notesList.get(position);
        holder.noteTitle.setText(currentNote.title);
        holder.noteBody.setText(currentNote.body);
        holder.timeCreated.setText(Utils.getRelativeSentFromMessageWithTime(currentNote.timeCreated));
        //TODO set created time from relative formatting
    }

    void swapData(ArrayList<Note> newNotesList) {
        notesList = newNotesList;
        notifyDataSetChanged();
    }

    void addNote(Note note) {
        this.notesList.add(note);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    class NotesItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.note_title_textview)
        TextView noteTitle;
        @BindView(R.id.note_body_textview)
        TextView noteBody;
        @BindView(R.id.note_time_created_textview)
        TextView timeCreated;

        public NotesItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
