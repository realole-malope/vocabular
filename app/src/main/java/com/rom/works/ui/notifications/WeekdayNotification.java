package com.rom.works.ui.notifications;

public class WeekdayNotification {
    public static final String EVERY_SUNDAY = "Every Sunday";
    public static final String EVERY_MONDAY = "Every Monday";
    public static final String EVERY_TUESDAY = "Every Tuesday";
    public static final String EVERY_WEDNESDAY = "Every Wednesday";
    public static final String EVERY_THURSDAY = "Every Thursday";
    public static final String EVERY_FRIDAY = "Every Friday";
    public static final String EVERY_SATURDAY = "Every Saturday";
    private String weekday;
    private boolean notificationOn;

    public WeekdayNotification(){}

    public WeekdayNotification(String weekday, boolean notificationOn) {
        this.weekday = weekday;
        this.notificationOn = notificationOn;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public boolean isNotificationOn() {
        return notificationOn;
    }

    public void setNotificationOn(boolean notificationOn) {
        this.notificationOn = notificationOn;
    }
}
