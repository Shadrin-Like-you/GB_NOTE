package com.shadrin_like_you.gb_note.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.shadrin_like_you.gb_note.R;
import com.shadrin_like_you.gb_note.domain.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final List<Note> data = new ArrayList<>();
    private Fragment fragment;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy.MM.d HH:mm", Locale.getDefault());
    private OnNoteClicked noteClicked;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setData(Collection<Note> notes) {
        data.addAll(notes);
    }

    public OnNoteClicked getNoteClicked() {
        return noteClicked;
    }

    public void setNoteClicked(OnNoteClicked noteClicked) {
        this.noteClicked = noteClicked;
    }

    public int addNote(Note note) {
        data.add(note);
        return data.size() - 1;
    }

    public void removeNote(Note selectedNote) {
        data.remove(selectedNote);
    }

    public void replaceNote(Note note, int selectedPosition) {
        data.set(selectedPosition, note);
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

            CardView cardView = itemView.findViewById(R.id.card_view);

            fragment.registerForContextMenu(cardView);

            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (noteClicked != null) {
                        int clickedPosition = getAdapterPosition();
                        noteClicked.onNoteClicked(data.get(clickedPosition));
                    }

                }
            });

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    cardView.showContextMenu();

                    if (noteClicked != null) {
                        int clickedPosition = getAdapterPosition();

                        noteClicked.onNoteLongClicked(data.get(clickedPosition), clickedPosition);
                    }

                    return true;
                }
            });

        }
    }
}