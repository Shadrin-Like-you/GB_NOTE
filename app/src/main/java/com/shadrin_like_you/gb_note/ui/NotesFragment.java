package com.shadrin_like_you.gb_note.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shadrin_like_you.gb_note.R;
import com.shadrin_like_you.gb_note.domain.InMemoryNotesRepo;
import com.shadrin_like_you.gb_note.domain.Note;

import java.util.List;

public class NotesFragment extends Fragment {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false); //(разметка, контейнер, нет зависимостисти(прицепа) от контейнера)
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Note> notes = InMemoryNotesRepo.getINSTATE(requireContext()).getAll(); //берем список из MemoryRepo/ requireContext() - возвращаем контекс

        LinearLayout container = view.findViewById(R.id.container); //находим нужный контейнер по id

        for (Note note : notes) {


            View itemView = getLayoutInflater().inflate(R.layout.item_note, container, false);

            itemView.findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show(); //показываем выбраную заметку

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
