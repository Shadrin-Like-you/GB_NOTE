package com.shadrin_like_you.gb_note.ui;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.google.android.material.snackbar.Snackbar;
import com.shadrin_like_you.gb_note.R;

import java.util.Arrays;

public class NotificationsFragment extends Fragment {


    private static final String CHANNEL_ID = "CHANNEL_ID";

    public NotificationsFragment() {
        super(R.layout.fragment_notifications);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            //Создания канала уведомлений
        NotificationChannelCompat notificationChannelCompat = new NotificationChannelCompat.Builder(CHANNEL_ID, NotificationManagerCompat.IMPORTANCE_DEFAULT)
                .setName("Channel Name")
                .setDescription("Description")
                .build();

        NotificationManagerCompat.from(requireContext()).createNotificationChannel(notificationChannelCompat);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            getParentFragmentManager()
                    .setFragmentResultListener(CustomViewDialogFragment.RESULT_KEY, getViewLifecycleOwner(), new FragmentResultListener() {
                        @Override
                        public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                            String message = result.getString(CustomViewDialogFragment.ARG_MESSAGE);

                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    });
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

        view.findViewById(R.id.dialog_single_choice).setOnClickListener(new View.OnClickListener() {

            // Единичный выбор (выбирает пользователь)

            @Override
            public void onClick(View view) {
                CharSequence[] items = {"1", "2", "3", "4"};

                final int[] selected = {-1};

                new AlertDialog.Builder(requireContext())
                        .setSingleChoiceItems(items, selected[0], new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                selected[0] = i;
                            }
                        })
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(requireContext(), items[selected[0]], Toast.LENGTH_SHORT).show();

                            }
                        })
                        .show();

            }
        });

        view.findViewById(R.id.dialog_multiple_choice).setOnClickListener(new View.OnClickListener() {

            // Несколько вариантов выбора (выбирает пользователь)

            @Override
            public void onClick(View view) {
                CharSequence[] items = {"1", "2", "3", "4"};
                boolean[] selected = {false, false, false, false};

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

        view.findViewById(R.id.dialog_custom_dialog_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomViewDialogFragment.newInstance("Message")
                        .show(getParentFragmentManager(), "");
            }
        });

        view.findViewById(R.id.dialog_fragment_custom_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new VeryCustomDialogFragment().show(getParentFragmentManager(), "");
            }
        });

        view.findViewById(R.id.bottom_sheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BottomSheetDialogFragment().show(getParentFragmentManager(), "");

            }
        });

        view.findViewById(R.id.system_tray_notification).setOnClickListener(new View.OnClickListener() {

            // Показывает уведомления вне приложения

            @Override
            public void onClick(View view) {

                Intent launchMain = new Intent(requireContext(), MainActivity.class);

                PendingIntent pendingIntent = PendingIntent.getActivity(requireContext(), 0, launchMain, PendingIntent.FLAG_CANCEL_CURRENT);

                Notification notification = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                        .setContentTitle("Title")
                        .setSmallIcon(R.drawable.ic_baseline_send_24)
                        .setContentText("Text")
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .build();

                NotificationManagerCompat.from(requireContext())
                        .notify(10, notification);

            }
        });

    }


}
