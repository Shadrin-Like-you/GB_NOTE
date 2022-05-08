package com.shadrin_like_you.gb_note.ui.settings;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.shadrin_like_you.gb_note.R;

import java.util.Arrays;


public class SettingsFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.dialog_multiple_choice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence[] items = {"1", "2", "3"};
                boolean[] selected = {false, false, false};

                new AlertDialog.Builder(requireContext())
                        .setMultiChoiceItems(items, selected, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                selected[i] = b;
                            }
                        })
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(requireContext(), Arrays.toString(selected), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

            }


        });


    }
}



