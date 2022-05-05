package com.shadrin_like_you.gb_note.ui;

import android.app.Fragment;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationManagerCompat;

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

       // NotificationManagerCompat.from(requireContext()).createNotificationChannel(notificationChannelCompat);

    }
}
