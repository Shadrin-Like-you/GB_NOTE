package com.shadrin_like_you.gb_note.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.shadrin_like_you.gb_note.R;

public class CustomViewDialogFragment extends DialogFragment {

    public static final String ARG_MESSAGE = "ARG_MESSAGE";
    public static final String RESULT_KEY = "CustomViewDialogFragment_RESULT_KEY";

    public static CustomViewDialogFragment newInstance(String message) {

        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        CustomViewDialogFragment fragment = new CustomViewDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View customTitle = getLayoutInflater().inflate(R.layout.dialog_custom_title, null);

        View customView = getLayoutInflater().inflate(R.layout.dialog_custom_view, null);

        EditText editText = customView.findViewById(R.id.edit_text);

        String message = getArguments().getString(ARG_MESSAGE);

        editText.setText(message);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext())
                .setCustomTitle(customTitle)
                .setView(customView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Bundle result = new Bundle();
                        result.putString(ARG_MESSAGE, editText.getText().toString());

                        getParentFragmentManager()
                                .setFragmentResult(RESULT_KEY, result);
                    }
                });

        return builder.create();

    }


}
