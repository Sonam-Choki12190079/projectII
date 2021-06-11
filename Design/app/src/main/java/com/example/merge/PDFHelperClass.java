package com.example.merge;

public class PDFHelperClass {
    String year, title, PDF;
    public PDFHelperClass(){}

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PDFHelperClass(String year, String title, String pdf){
        this.title = title;
        PDF = pdf;
        this.year = year;
    }
}

