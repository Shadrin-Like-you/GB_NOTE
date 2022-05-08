package com.shadrin_like_you.gb_note.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.shadrin_like_you.gb_note.R;

public class MainActivity extends AppCompatActivity implements ToolbarHolder {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);

        NavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_notes:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new NotesFragment())
                                .commit();

                        drawerLayout.closeDrawers();

                        return true;

//                    case R.id.settings:
//                        getSupportFragmentManager().popBackStack();
//
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.fragment_container, new SettingsFragment())
//                                .commit();
//
//                        drawerLayout.closeDrawer();
//
//                        return true;

                    case R.id.action_notifications:
                        getSupportFragmentManager().popBackStack();

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new NotificationsFragment())
                                .commit();

                        drawerLayout.closeDrawers();

                        return true;

                }
                return false;
            }
        });
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Выйти из приложения?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();
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