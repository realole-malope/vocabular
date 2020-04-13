package com.rom.works.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.rom.works.R;
import com.rom.works.entity.NotificationConfig;
import com.rom.works.service.NotificationConfigDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private final List<WeekdayNotification> weekdays = new ArrayList<>();
    private ArrayAdapter<WeekdayNotification> adapter;
    private NotificationConfigDatabaseHelper notificationConfigDatabaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationConfigDatabaseHelper = new NotificationConfigDatabaseHelper(getContext());
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        adapter = new NotificationAdapter(weekdays, getContext());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }


    private void initData() {
        NotificationConfig config = notificationConfigDatabaseHelper.getConfig();
        System.out.println(config);
        weekdays.add(new WeekdayNotification(WeekdayNotification.EVERY_SUNDAY, config.isNotifyOnSunday()));
        weekdays.add(new WeekdayNotification(WeekdayNotification.EVERY_MONDAY, config.isNotifyOnMonday()));
        weekdays.add(new WeekdayNotification(WeekdayNotification.EVERY_TUESDAY, config.isNotifyOnTuesday()));
        weekdays.add(new WeekdayNotification(WeekdayNotification.EVERY_WEDNESDAY, config.isNotifyOnWednesday()));
        weekdays.add(new WeekdayNotification(WeekdayNotification.EVERY_THURSDAY, config.isNotifyOnThursday()));
        weekdays.add(new WeekdayNotification(WeekdayNotification.EVERY_FRIDAY, config.isNotifyOnFriday()));
        weekdays.add(new WeekdayNotification(WeekdayNotification.EVERY_SATURDAY, config.isNotifyOnSaturday()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "HELLLLLo", Toast.LENGTH_SHORT).show();
    }
}