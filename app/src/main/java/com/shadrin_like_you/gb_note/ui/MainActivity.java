package com.shadrin_like_you.gb_note.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import com.shadrin_like_you.gb_note.R;
import com.shadrin_like_you.gb_note.ui.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
   /* findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new SettingsFragment())
                    .commit();
        }
    });

*/

}