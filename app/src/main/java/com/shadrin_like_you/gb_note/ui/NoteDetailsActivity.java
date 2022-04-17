package com.shadrin_like_you.gb_note.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shadrin_like_you.gb_note.R;
import com.shadrin_like_you.gb_note.domain.Note;

public class NoteDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_NOTE = "EXTRA_NOTE";

    public static void show(Context context, Note note) {
        Intent intent = new Intent(context, NoteDetailsActivity.class);
        intent.putExtra(EXTRA_NOTE, note);

        context.startActivities(new Intent[]{intent});

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details_activity);

        if (savedInstanceState == null) { //сохранение состояние и кол-ва экранов, чтобы не пересоздавать заново

                Note note = getIntent().getParcelableExtra(EXTRA_NOTE);

                NoteDetailsFragment noteDetailsFragment = NoteDetailsFragment.newInstance(note);

                getSupportFragmentManager()
                        .beginTransaction() // начало транзакции
                        .replace(R.id.container, noteDetailsFragment)
                        .commit(); //конец

        }

    }
}