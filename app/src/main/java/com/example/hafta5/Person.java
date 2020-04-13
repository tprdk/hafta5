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
    private String gender;
    private String mode;
    private int image;
    private int height;
    private int weight;
    private int age;

    public Person(String name, String surname, String email, String username, String password) {
        this.name= name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.image = R.drawable.ic_person_man;
        this.height = 0;
        this.weight = 0;
        this.age = 0;
        this.mode = "Dark";
        this.gender = "Male";
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getName() { return name; }

    public String getGender() { return gender;}

    public void setGender(String gender) { this.gender = gender; }

    public String getMode() { return mode; }

    public void setMode(String mode) { this.mode = mode; }

}
