package com.example.bloomi.Message;

public class chatBox {
    private Integer avatar;
    String Name,time,message;

    public chatBox(Integer avatar, String name, String time, String message) {
        this.avatar = avatar;
        Name = name;
        this.time = time;
        this.message = message;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
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
