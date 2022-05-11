package com.shadrin_like_you.gb_note.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadrin_like_you.gb_note.R;
import com.shadrin_like_you.gb_note.domain.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy.MM.d HH:mm", Locale.getDefault());

    private final List<Note> data = new ArrayList<>();

    public void setData(Collection<Note> notes) {
        data.addAll(notes);
    }



    public void setNoteClicked(OnNoteClicked noteClicked) {
        this.noteClicked = noteClicked;
    }

    private OnNoteClicked noteClicked;

    public OnNoteClicked getNoteClicked() {
        return noteClicked;
    }

    interface OnNoteClicked {
        void onNoteClicked(Note note);

        void onNoteLongClicked(Note note, int position);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

        NotesViewHolder holder = new NotesViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        Note note = data.get(position);

        holder.title.setText(note.getTitle());
        holder.massage.setText(note.getContent());
        holder.icon.setImageResource(note.getIcon());
        holder.date.setText(simpleDateFormat.format(note.getCreateDate()));


    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView massage;
        TextView date;
        ImageView icon;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            massage = itemView.findViewById(R.id.message);
            date = itemView.findViewById(R.id.date);
            icon = itemView.findViewById(R.id.icon);

        itemView.findViewById(R.id.card_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noteClicked != null) {
                    int clickedPosition = getAdapterPosition();
                    noteClicked.onNoteClicked(data.get(clickedPosition));
                }

            }
        });

        }
    }
}