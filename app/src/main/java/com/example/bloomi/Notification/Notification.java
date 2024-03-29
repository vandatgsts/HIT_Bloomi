package com.example.bloomi.Notification;

public class Notification {

    String noti_name,noti_action;
    int noti_time;
    String noti_time_unit;
    int type=1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



    public String getNoti_name() {
        return noti_name;
    }

    public void setNoti_name(String noti_name) {
        this.noti_name = noti_name;
    }

    public String getNoti_action() {
        return noti_action;
    }

    public void setNoti_action(String noti_action) {
        this.noti_action = noti_action;
    }

    public int getNoti_time() {
        return noti_time;
    }

    public void setNoti_time(int noti_time) {
        this.noti_time = noti_time;
    }

    public String getNoti_time_unit() {
        return noti_time_unit;
    }

    public void setNoti_time_unit(String noti_time_unit) {
        this.noti_time_unit = noti_time_unit;
    }

    public Notification(int noti_Image, String noti_name, String noti_action, int noti_time, String noti_time_unit) {

        this.noti_name = noti_name;
        this.noti_action = noti_action;
        this.noti_time = noti_time;
        this.noti_time_unit = noti_time_unit;
    }

    public Notification(String noti_action, String noti_name) {
        this.noti_name = noti_name;
        this.noti_action = noti_action;
    }
}
