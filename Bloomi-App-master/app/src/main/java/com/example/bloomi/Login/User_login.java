package com.example.bloomi.Login;

import com.example.bloomi.uses_manage;

public class User_login {
    String jwt;
    uses_manage Accout;
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public uses_manage getAccout() {
        return Accout;
    }

    public void setAccout(uses_manage accout) {
        Accout = accout;
    }

    public User_login(String jwt, uses_manage accout) {
        this.jwt = jwt;
        Accout = accout;
    }

    @Override
    public String toString() {
        return "User_login{" +
                "jwt='" + jwt + '\'' +
                ", Accout=" + Accout +
                '}';
    }
}
