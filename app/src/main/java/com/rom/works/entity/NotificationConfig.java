package com.rom.works.entity;

public class NotificationConfig {
    private Integer id;
    private boolean notifyOnSunday;
    private boolean notifyOnMonday;
    private boolean notifyOnTuesday;
    private boolean notifyOnWednesday;
    private boolean notifyOnThursday;
    private boolean notifyOnFriday;
    private boolean notifyOnSaturday;
    private boolean notificationsOn;
    private Integer numberOfWords;

    public Integer getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(Integer numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNotifyOnSunday() {
        return notifyOnSunday;
    }

    public void setNotifyOnSunday(boolean notifyOnSunday) {
        this.notifyOnSunday = notifyOnSunday;
    }

    public boolean isNotifyOnMonday() {
        return notifyOnMonday;
    }

    public void setNotifyOnMonday(boolean notifyOnMonday) {
        this.notifyOnMonday = notifyOnMonday;
    }

    public boolean isNotifyOnTuesday() {
        return notifyOnTuesday;
    }

    public void setNotifyOnTuesday(boolean notifyOnTuesday) {
        this.notifyOnTuesday = notifyOnTuesday;
    }

    public boolean isNotifyOnWednesday() {
        return notifyOnWednesday;
    }

    public void setNotifyOnWednesday(boolean notifyOnWednesday) {
        this.notifyOnWednesday = notifyOnWednesday;
    }

    public boolean isNotifyOnThursday() {
        return notifyOnThursday;
    }

    public void setNotifyOnThursday(boolean notifyOnThursday) {
        this.notifyOnThursday = notifyOnThursday;
    }

    public boolean isNotifyOnFriday() {
        return notifyOnFriday;
    }

    public void setNotifyOnFriday(boolean notifyOnFriday) {
        this.notifyOnFriday = notifyOnFriday;
    }

    public boolean isNotifyOnSaturday() {
        return notifyOnSaturday;
    }

    public void setNotifyOnSaturday(boolean notifyOnSaturday) {
        this.notifyOnSaturday = notifyOnSaturday;
    }

    public boolean isNotificationsOn() {
        return notificationsOn;
    }

    public void setNotificationsOn(boolean notificationsOn) {
        this.notificationsOn = notificationsOn;
    }

    @Override
    public String toString() {
        return "NotificationConfig{" +
                "id=" + id +
                ", notifyOnSunday=" + notifyOnSunday +
                ", notifyOnMonday=" + notifyOnMonday +
                ", notifyOnTuesday=" + notifyOnTuesday +
                ", notifyOnWednesday=" + notifyOnWednesday +
                ", notifyOnThursday=" + notifyOnThursday +
                ", notifyOnFriday=" + notifyOnFriday +
                ", notifyOnSaturday=" + notifyOnSaturday +
                ", notificationsOn=" + notificationsOn +
                '}';
    }
}
