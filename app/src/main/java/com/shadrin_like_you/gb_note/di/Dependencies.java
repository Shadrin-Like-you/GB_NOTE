package com.shadrin_like_you.gb_note.di;

import com.shadrin_like_you.gb_note.domain.FireStoreNotesRepo;
import com.shadrin_like_you.gb_note.domain.InMemoryNotesRepo;
import com.shadrin_like_you.gb_note.domain.NotesRepository;

public class Dependencies {

    public static final NotesRepository NOTES_REPOSITORY = new FireStoreNotesRepo();


    public static NotesRepository getNotesRepository() {
        return NOTES_REPOSITORY;
    }
}
