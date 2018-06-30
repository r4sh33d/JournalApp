package com.r4sh33d.journalapp.noteslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesItemHolder> {

    @NonNull
    @Override
    public NotesItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class NotesItemHolder extends RecyclerView.ViewHolder {

        public NotesItemHolder(View itemView) {
            super(itemView);
        }
    }
}
