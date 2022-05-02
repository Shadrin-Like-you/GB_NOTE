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

import com.shadrin_like_you.gb_note.R;
import com.shadrin_like_you.gb_note.domain.InMemoryNotesRepo;
import com.shadrin_like_you.gb_note.domain.Note;

import java.util.List;

public class NotesFragment extends Fragment {

    public static final String NOTES_CLICKED_KEY = "NOTES_CLICKED_KEY";
    public static final String SELECTED_KEY = "SELECTED_KEY";


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


        List<Note> notes = InMemoryNotesRepo.getINSTATE(requireContext()).getAll(); //берем список из MemoryRepo/ requireContext() - возвращаем контекс

        LinearLayout container = view.findViewById(R.id.container); //находим нужный контейнер по id

        for (Note note : notes) {


            View itemView = getLayoutInflater().inflate(R.layout.item_note, container, false);

            itemView.findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                        Bundle bundle = new Bundle();
                        bundle.putParcelable(SELECTED_KEY, note);
                        getParentFragmentManager()
                                .setFragmentResult(NOTES_CLICKED_KEY, bundle);
                    } else {

                        NoteDetailsActivity.show(requireContext(), note); // тоже самое что и Toast, но на отдельном активити
                        // Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show(); //показываем выбраную заметку
                    }
                }
            });

            ImageView icon = itemView.findViewById(R.id.icon);

            icon.setImageResource(note.getIcon());

            TextView title = itemView.findViewById(R.id.title);

            title.setText(note.getTitle());

            container.addView(itemView);
        }

    }
}
