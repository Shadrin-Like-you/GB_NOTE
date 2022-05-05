package com.shadrin_like_you.gb_note.ui;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.shadrin_like_you.gb_note.R;

public class NotificationsFragment extends Fragment {


    private static final String CHANNEL_ID = "CHANNEL_ID";

    public NotificationsFragment() {
        super(R.layout.fragment_notifications);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationChannelCompat notificationChannelCompat = new NotificationChannelCompat.Builder(CHANNEL_ID, NotificationManagerCompat.IMPORTANCE_DEFAULT)
                .setName("Channel Name")
                .setDescription("Description")
                .build();

        NotificationManagerCompat.from(requireContext()).createNotificationChannel(notificationChannelCompat);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//            getParentFragmentManager()
//                    .setFragmentResultListener(CustomViewDialogFragment.RESULT_KEY, getViewLifecycleOwner(), new FragmentResultListener() {
//                        @Override
//                        public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                            String message = result.getString(CustomViewDialogFragment.ARG_MESSAGE);
//
//                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
//                        }
//                    });
        view.findViewById(R.id.toast).setOnClickListener(new View.OnClickListener() {       //Сообщение от приложения (поверх окон без конкретики)
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "Toast Message", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.snackbar).setOnClickListener(new View.OnClickListener() {  // Тоже самое что и Toast, но в окне приложения + возможность  свапать и добавления кнопок)
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Snack", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(requireContext(), "Action", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .show();
            }
        });


        view.findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {  //выводит диалог с пользователем
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(requireContext())
                        .setTitle("Ошибка")
                        .setMessage("Что-то пошло не так... Будем отлаживать?")
                        .setIcon(R.drawable.ic_baseline_info_24)
                        .setCancelable(false)
                        // при добавлении этой команды диалог нельзя скипнуть

                        .setPositiveButton("Да, конечно!", new DialogInterface.OnClickListener() {  // вариант действия
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(requireContext(), "Вперед за работу!", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Само пройдет!", new DialogInterface.OnClickListener() {  // вариант действия
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(requireContext(), "Действительно...", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNeutralButton("У меня лапки", new DialogInterface.OnClickListener() {  // вариант действия
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(requireContext(), ";)", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .show();

            }
        });


        view.findViewById(R.id.custom_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View customTitle = getLayoutInflater().inflate(R.layout.dialog_custom_title, null); // Кастомный заголовок

                //getLayoutInflater переводит xlm файл во view

                View customView = getLayoutInflater().inflate(R.layout.dialog_custom_view, null); // Кастомный фон, картинка и т.д.

                EditText editText = customView.findViewById(R.id.edit_text); //ввод текста пользователя

                new AlertDialog.Builder(requireContext())
                        .setCustomTitle(customTitle)
                        .setView(customView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(requireContext(), editText.getText().toString(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        view.findViewById(R.id.dialog_list).setOnClickListener(new View.OnClickListener() {

            // диалог с активными вариантами выбора

            @Override
            public void onClick(View view) {

                CharSequence[] items = {"1", "2", "3", "4"};

                new AlertDialog.Builder(requireContext())
                        .setTitle("Choose an item")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(requireContext(), items[i], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });


    }


}
