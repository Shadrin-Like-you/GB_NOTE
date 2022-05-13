package com.shadrin_like_you.gb_note.domain;

import java.util.List;

public interface NotesRepository {


    void getAll(Callback<List<Note>> callback);

    void addNote(String title, String message, Callback<Note> callback);

    void removeNote(Note note, Callback<Void> callback);

    void updateNote(Note note, String title, String message, Callback<Note> callback);
}
