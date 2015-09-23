package com.example.myapplication;

import java.io.Serializable;

public class Usuario implements Serializable {

    public String user;
    public String password;

    public void setUser(String user){
        this.user = user;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUser(){
        return this.user;
    }

    public String getPassword(){
        return this.password;
    }
}
