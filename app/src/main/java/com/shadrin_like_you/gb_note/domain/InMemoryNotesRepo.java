package com.shadrin_like_you.gb_note.domain;

import android.content.Context;

import com.shadrin_like_you.gb_note.R;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotesRepo implements NotesRepository {


    private static NotesRepository INSTATE;

    public static NotesRepository getINSTATE (Context context) {
        if (INSTATE == null) {
            INSTATE = new InMemoryNotesRepo(context);
        }
        return INSTATE;
    }

    private Context context;

    private InMemoryNotesRepo(Context context) {
        this.context = context;
    }


    @Override
    public List<Note> getAll() {
        ArrayList<Note> result = new ArrayList<>();
        result.add(new Note(context.getString(R.string.note1),R.drawable.ic_baseline_auto_stories_24));
        return result;
    }

    @Override
    public void add(Note note) {

    }
}
