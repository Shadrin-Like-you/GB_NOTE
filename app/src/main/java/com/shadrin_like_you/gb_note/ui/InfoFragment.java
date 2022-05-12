package com.shadrin_like_you.gb_note.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shadrin_like_you.gb_note.R;

public class InfoFragment extends Fragment {

    public InfoFragment() {
        super(R.layout.fragment_info);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.nested_container, new NotesFragment())
                .commit();
    }
}
