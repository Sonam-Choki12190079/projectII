package com.example.merge;

public class ModelAdmin {
    String user, email;
    public ModelAdmin(){}

    public ModelAdmin(String user, String email){
        this.user=user;
        this.email=email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String name) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
