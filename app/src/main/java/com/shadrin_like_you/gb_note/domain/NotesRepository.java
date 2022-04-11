package com.shadrin_like_you.gb_note.domain;

import java.util.List;

public interface NotesRepository {
    List<Note> getAll();

    void add (Note note);
}
