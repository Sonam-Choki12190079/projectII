package com.example.merge;

public class database {
    String name;
    String email;
    String password;
    String uid;

    public database(String name, String email, String password, String uid) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public database() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUid() {
        return uid;
    }
}
