package com.shadrin_like_you.gb_note.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shadrin_like_you.gb_note.R;
import com.shadrin_like_you.gb_note.di.Dependencies;
import com.shadrin_like_you.gb_note.domain.Note;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NotesFragment extends Fragment {

    public static final String NOTES_CLICKED_KEY = "NOTES_CLICKED_KEY";
    public static final String SELECTED_KEY = "SELECTED_KEY";

    public NotesFragment() {
        super(R.layout.fragment_notes_list);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false); //(разметка, контейнер, нет зависимостисти(прицепа) от контейнера)
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (requireActivity() instanceof ToolbarHolder) {
            ((ToolbarHolder) requireActivity()).setToolbar(toolbar);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_send) {
                    Toast.makeText(requireContext(), "send", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        RecyclerView notesList = view.findViewById(R.id.notes_list); //находим нужный контейнер по id

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        notesList.setLayoutManager(layoutManager);

        NotesAdapter adapter = new NotesAdapter();

        adapter.setNoteClicked(new NotesAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Note note) {
                Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoteLongClicked(Note note, int position) {

            }
        });

        notesList.setAdapter(adapter);

        List<Note> notes = Dependencies.NOTES_REPOSITORY.getAll(); //берем список из MemoryRepo/ requireContext() - возвращаем контекс

        adapter.setData(notes);

        adapter.notifyDataSetChanged();  //перерисовка

    }
}
