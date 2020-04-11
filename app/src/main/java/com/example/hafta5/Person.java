package com.example.hafta5;

import android.app.Activity;
import android.content.Context;

import java.io.*;
import java.util.ArrayList;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

public class Person  implements Serializable{
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private int image;

    public Person(String name, String surname, String email, String username, String password, int image) {
        this.name= name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.image = image;
    }
    public Person(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPicture() { return image; }

    public void setPicture(int image) {
        this.image = image;
    }
}
