package com.rom.works.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.rom.works.R;
import com.rom.works.entity.NotificationConfig;
import com.rom.works.service.NotificationConfigDatabaseHelper;

import java.util.List;

public class NotificationAdapter extends ArrayAdapter<WeekdayNotification> implements View.OnClickListener {

    private ArrayAdapter<WeekdayNotification> adapter;
    private List<WeekdayNotification> data;

    private static class ViewHolder {
        TextView txtWeekday;
        CheckBox isNotificationOn;
    }

    public NotificationAdapter(List<WeekdayNotification> data, Context context) {
        super(context, R.layout.notification_display_layout, data);
        adapter = this;
        this.data = data;
    }


    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        WeekdayNotification weekdayNotification = getItem(position);
        Toast.makeText(getContext(), "Clicked: " + weekdayNotification, Toast.LENGTH_LONG).show();
    }

    private int lastPosition = -1;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        WeekdayNotification dataModel = getItem(position);
        assert dataModel != null;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.notification_display_layout, parent, false);
            convertView.setOnClickListener(createOnClickListenerForNotificationWeekday(position));
            viewHolder.txtWeekday = convertView.findViewById(R.id.notification_weekday_name);
            viewHolder.isNotificationOn = convertView.findViewById(R.id.notification_weekday_on);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        lastPosition = position;

        viewHolder.txtWeekday.setText(dataModel.getWeekday());
        viewHolder.isNotificationOn.setChecked(dataModel.isNotificationOn());
        // Return the completed view to render on screen
        return convertView;
    }

    private View.OnClickListener createOnClickListenerForNotificationWeekday(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeekdayNotification weekdayNotification = data.get(position);
                weekdayNotification.setNotificationOn(!weekdayNotification.isNotificationOn());
                adapter.notifyDataSetChanged();
                NotificationConfigDatabaseHelper instance = NotificationConfigDatabaseHelper.INSTANCE;
                NotificationConfig config = instance.getConfig();
                // update db
                updateConfigFields(weekdayNotification, config);
                instance.updateConfig(config);

            }
        };
    }

    private void updateConfigFields(WeekdayNotification notification, NotificationConfig config) {
        switch (notification.getWeekday()) {
            case WeekdayNotification.EVERY_SUNDAY:
                config.setNotifyOnSunday(!config.isNotifyOnSunday());
                break;
            case WeekdayNotification.EVERY_MONDAY:
                config.setNotifyOnMonday(!config.isNotifyOnMonday());
                break;
            case WeekdayNotification.EVERY_TUESDAY:
                config.setNotifyOnTuesday(!config.isNotifyOnTuesday());
                break;
            case WeekdayNotification.EVERY_WEDNESDAY:
                config.setNotifyOnWednesday(!config.isNotifyOnWednesday());
                break;
            case WeekdayNotification.EVERY_THURSDAY:
                config.setNotifyOnThursday(!config.isNotifyOnThursday());
                break;
            case WeekdayNotification.EVERY_FRIDAY:
                config.setNotifyOnFriday(!config.isNotifyOnFriday());
                break;
            case WeekdayNotification.EVERY_SATURDAY:
                config.setNotifyOnSaturday(!config.isNotifyOnSaturday());
            default:
                break;
        }
    }

}
