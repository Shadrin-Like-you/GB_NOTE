package com.shadrin_like_you.gb_note.ui;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shadrin_like_you.gb_note.R;
import com.shadrin_like_you.gb_note.di.Dependencies;
import com.shadrin_like_you.gb_note.domain.Callback;
import com.shadrin_like_you.gb_note.domain.Note;

import java.util.List;

public class NotesFragment extends Fragment {

    public static final String NOTES_CLICKED_KEY = "NOTES_CLICKED_KEY";
    public static final String SELECTED_KEY = "SELECTED_KEY";

    private Note selectedNote;
    private int selectedPosition;

    private NotesAdapter adapter;
    private ProgressBar progressBar;

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


        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();  //Настройки анимации
        defaultItemAnimator.setRemoveDuration(3000L);
        notesList.setItemAnimator(defaultItemAnimator);

        adapter = new NotesAdapter(this);

        adapter.setNoteClicked(new NotesAdapter.OnNoteClicked() {

            @Override
            public void onNoteClicked(Note note) {
                Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoteLongClicked(Note note, int position) {

                selectedNote = note;
                selectedPosition = position;

            }
        });

        notesList.setAdapter(adapter);


        getParentFragmentManager()
                .setFragmentResultListener(AddNoteBottomSheetDialogFragment.ADD_KEY_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = result.getParcelable(AddNoteBottomSheetDialogFragment.ARG_NOTE);

                        int index = adapter.addNote(note);

                        adapter.notifyItemInserted(index);

                        notesList.smoothScrollToPosition(index);
                    }
                });

        getParentFragmentManager()
                .setFragmentResultListener(AddNoteBottomSheetDialogFragment.UPDATE_KEY_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = result.getParcelable(AddNoteBottomSheetDialogFragment.ARG_NOTE);

                        adapter.replaceNote(note, selectedPosition);

                        adapter.notifyItemChanged(selectedPosition);
                    }
                });

        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddNoteBottomSheetDialogFragment()
                        .show(getParentFragmentManager(), "AddNoteBottomSheetDialogFragment");
            }
        });

        progressBar = view.findViewById(R.id.progress);

        progressBar.setVisibility(View.VISIBLE);

        Dependencies.NOTES_REPOSITORY.getAll(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> data) {

                adapter.setData(data); //берем список из MemoryRepo/ requireContext() - возвращаем контекс

                adapter.notifyDataSetChanged();  //перерисовка

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onError(Throwable exception) {

                progressBar.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_notes_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:

                progressBar.setVisibility(View.VISIBLE);

                Dependencies.getNotesRepository().removeNote(selectedNote, new Callback<Void>() {
                    @Override
                    public void onSuccess(Void data) {

                        progressBar.setVisibility(View.GONE);

                        adapter.removeNote(selectedNote);

                        adapter.notifyItemRemoved(selectedPosition);
                    }

                    @Override
                    public void onError(Throwable exception) {

                    }
                });

                return true;

            case R.id.action_edit:
                AddNoteBottomSheetDialogFragment.editInstance(selectedNote)
                        .show(getParentFragmentManager(), "AddNoteBottomSheetDialogFragment");
                return true;

        }
        return super.onContextItemSelected(item);
    }

}
