package com.r4sh33d.journalapp.models;

public class Note {
    public String title;
    public String body;
    public long timeCreated;
    public long lastEditTime;

    public Note() {

    }

    public Note(String title, String body, long timeCreated, long lastEditTime) {
        this.title = title;
        this.body = body;
        this.timeCreated = timeCreated;
        this.lastEditTime = lastEditTime;
    }
}
