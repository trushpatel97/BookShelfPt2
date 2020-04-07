package com.example.bookshelf;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Book implements Parcelable {//chose parcelable over the other one

    //Making private class variables that will be used to create constructors and functions to pass down
    private int id;
    private String title,author,coverURL;
    //***************************************************************************************************

    //Creating the books constructor*********************************************************************
    public Book(int id, String title, String author, String coverURL){
        this.id = id;
        this.title = title;
        this.author = author;
        this.coverURL = coverURL;
    }
    //***************************************************************************************************

    //Creating another contructor for the Parcels input getting the private field's**********************
    private Book(Parcel input){
        this.id=input.readInt();
        this.title=input.readString();
        this.author=input.readString();
        this.coverURL=input.readString();
    }
    //***************************************************************************************************

    //Creating a creator that will be used for the parcel. It was requiring me to make one***************
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }
        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
    //making book a json object  hits will contain to books details
    public Book(JSONObject jsonObject) throws JSONException {
        this(jsonObject.getInt("id"),jsonObject.getString("title"),
                jsonObject.getString("author"), jsonObject.getString("cover_url"));
    }
    //***************************************************************************************************

    //Creating public methods to get some important data about the book if needed************************
    @Override
    public int describeContents() {return 0;}
    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getCoverURL() {return coverURL;}

    //Writing to the Parcel******************************************************************************
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(coverURL);
    }
    //**************************************************************************************************
}