package com.example.bloomi.Message;

public class chatBox {
    private String avatar;
    String Name,time,message;

    public chatBox(String avatar, String name, String time, String message) {
        this.avatar = avatar;
        Name = name;
        this.time = time;
        this.message = message;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
