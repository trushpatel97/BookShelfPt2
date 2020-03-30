package com.example.bookshelf;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private int id;
    private String title,author,coverURL;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public Book(int id, String title, String author, String coverURL){
        this.id = id;
        this.title = title;
        this.author = author;
        this.coverURL = coverURL;
    } 
    private Book(Parcel input){
        this.id=input.readInt();
        this.title=input.readString();
        this.author=input.readString();
        this.coverURL=input.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(coverURL);
    }
}
