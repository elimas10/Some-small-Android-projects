package com.example.macbookpro.database.model.entities;

import com.example.macbookpro.database.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteEntity {

    private String title;
    private Date date;
    private String content;

    public NoteEntity(String title, Date date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;
    }


    public NoteEntity(String title, String date, String content) {
        this.title = title;
        setDate(date);
        this.content = content;
    }

    public NoteEntity() {

    }


    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getDateInString() {

        SimpleDateFormat sdf=new SimpleDateFormat(Constants.Date_Pattern);
        return sdf.format(this.date);


    }


    public String getContent() {
        return content;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) {
       SimpleDateFormat sdf=new SimpleDateFormat(Constants.Date_Pattern);
        try {
         this.date=sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            this.date=null;
        }
    }

    public void setContent(String content) {
        this.content = content;
    }
}
