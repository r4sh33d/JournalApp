package com.r4sh33d.journalapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    public String title;
    public String body;
    public long timeCreated;
    public long lastEditTime;
    public  String uniqueKey;

    public Note() {
    }

    public Note(String title, String body, long timeCreated, long lastEditTime, String uniqueKey) {
        this(title, body, timeCreated, lastEditTime);
        this.uniqueKey = uniqueKey;
    }

    public Note(String title, String body, long timeCreated, long lastEditTime) {
        this.title = title;
        this.body = body;
        this.timeCreated = timeCreated;
        this.lastEditTime = lastEditTime;
    }

    protected Note(Parcel in) {
        title = in.readString();
        body = in.readString();
        timeCreated = in.readLong();
        lastEditTime = in.readLong();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(body);
        dest.writeLong(timeCreated);
        dest.writeLong(lastEditTime);
    }
}
