package com.example.merge;

public class NotificationModel {
    String year, category, subject, description;


    public NotificationModel(){}

    public NotificationModel(String year, String category, String subject, String description){
        this.year = year;
        this.category = category;
        this.subject = subject;
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
