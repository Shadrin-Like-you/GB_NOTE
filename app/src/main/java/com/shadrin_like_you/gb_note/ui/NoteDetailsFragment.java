package com.shadrin_like_you.gb_note.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.shadrin_like_you.gb_note.R;
import com.shadrin_like_you.gb_note.domain.Note;

public class NoteDetailsFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";
    private TextView title;
    private ImageView icon;

    public NoteDetailsFragment() {
        super(R.layout.fragment_notes_details);

    }

    public static NoteDetailsFragment newInstance(Note note) {   //Вызов данного оператора для исключения потери фрагмента при пересоздании

        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);

        NoteDetailsFragment fragment = new NoteDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = view.findViewById(R.id.title);
        icon = view.findViewById(R.id.icon);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (requireActivity() instanceof ToolbarHolder) {
            ((ToolbarHolder) requireActivity()).setToolbar(toolbar);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_info:
                        Toast.makeText(requireContext(), "info", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_share:
                        Toast.makeText(requireContext(), "share", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });


        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(requireContext(), view);

                requireActivity().getMenuInflater().inflate(R.menu.menu_pop_up, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_search:
                                Toast.makeText(requireContext(), "search", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.action_copy:
                                Toast.makeText(requireContext(), "copy", Toast.LENGTH_SHORT).show();
                                return true;
                        }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });


        getParentFragmentManager()
                .setFragmentResultListener(NotesFragment.NOTES_CLICKED_KEY, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = result.getParcelable(NotesFragment.SELECTED_KEY);

                        showNote(note);
                    }
                });

        if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {
            Note note = getArguments().getParcelable(ARG_NOTE);

            showNote(note);
        }
    }

    private void showNote(Note note) {
        title.setText(note.getContent());
        icon.setImageResource(note.getIcon());
    }


}
