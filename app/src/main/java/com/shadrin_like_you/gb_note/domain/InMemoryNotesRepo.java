package com.shadrin_like_you.gb_note.domain;

import android.content.Context;
import android.service.quicksettings.Tile;

import com.shadrin_like_you.gb_note.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class InMemoryNotesRepo implements NotesRepository {


   private ArrayList<Note> data = new ArrayList<>();

   public InMemoryNotesRepo () {

       data.add(new Note(UUID.randomUUID().toString(), "Title 1", "Message 1", new Date()));
       data.add(new Note(UUID.randomUUID().toString(), "Title 2", "Message 2", new Date()));
       data.add(new Note(UUID.randomUUID().toString(), "Title 3", "Message 3", new Date()));
       data.add(new Note(UUID.randomUUID().toString(), "Title 4", "Message 4", new Date()));
       data.add(new Note(UUID.randomUUID().toString(), "Title 5", "Message 5", new Date()));
   }


    @Override
    public List<Note> getAll() {
        return data;
    }
}
