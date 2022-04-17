package com.shadrin_like_you.gb_note.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
