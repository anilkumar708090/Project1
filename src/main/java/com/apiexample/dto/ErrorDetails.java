package com.apiexample.dto;

import lombok.Data;

import java.util.Date;

public class ErrorDetails {

    private String messege;

    private Date date;

    public ErrorDetails(String messege, Date date) {
        this.messege = messege;
        this.date = date;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

}
