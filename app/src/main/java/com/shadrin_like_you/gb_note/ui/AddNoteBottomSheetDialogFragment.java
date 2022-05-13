package com.shadrin_like_you.gb_note.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shadrin_like_you.gb_note.R;
import com.shadrin_like_you.gb_note.di.Dependencies;
import com.shadrin_like_you.gb_note.domain.Callback;
import com.shadrin_like_you.gb_note.domain.Note;

public class AddNoteBottomSheetDialogFragment extends BottomSheetDialogFragment{

    public static final String ADD_KEY_RESULT = "AddNoteBottomSheetDialogFragment_ADD_KEY_RESULT";
    public static final String UPDATE_KEY_RESULT = "AddNoteBottomSheetDialogFragment_UPDATE_KEY_RESULT";
    public static final String ARG_NOTE = "ARG_NOTE";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_note_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Note noteToEdit = null;

        if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {
            noteToEdit = getArguments().getParcelable(ARG_NOTE);
        }

        EditText title = view.findViewById(R.id.title);
        EditText message = view.findViewById(R.id.message);

//        if (noteToEdit != null) {
//            title.setText(noteToEdit.getTitle());
//            message.setText(noteToEdit.getMessage());
//        }

        Button btnSave = view.findViewById(R.id.save);
        Note finalNoteToEdit = noteToEdit;
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnSave.setEnabled(false); //Блокировка (пока не ввел, другое не доступно пока есть запрос)

                if (finalNoteToEdit != null) {
                    Dependencies.getNotesRepository().updateNote(finalNoteToEdit, title.getText().toString(), message.getText().toString(), new Callback<Note>() {
                        @Override
                        public void onSuccess(Note data) {

                            Bundle bundle = new Bundle();
                            bundle.putParcelable(ARG_NOTE, data);

                            getParentFragmentManager().setFragmentResult(UPDATE_KEY_RESULT, bundle);

                            btnSave.setEnabled(true);

                            dismiss();

                        }

                        @Override
                        public void onError(Throwable exception) {
                            btnSave.setEnabled(true);
                        }
                    });
                } else {

                    Dependencies.getNotesRepository().addNote(title.getText().toString(), message.getText().toString(), new Callback<Note>() {
                        @Override
                        public void onSuccess(Note data) {

                            Bundle bundle = new Bundle();
                            bundle.putParcelable(ARG_NOTE, data);

                            getParentFragmentManager().setFragmentResult(ADD_KEY_RESULT, bundle);

                            btnSave.setEnabled(true);

                            dismiss();
                        }

                        @Override
                        public void onError(Throwable exception) {
                            btnSave.setEnabled(true);

                        }
                    });
                }
            }
        });
    }
}
