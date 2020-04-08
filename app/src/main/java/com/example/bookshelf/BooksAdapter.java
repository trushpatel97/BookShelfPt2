package com.example.bookshelf;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class BooksAdapter extends BaseAdapter {

    //Creating private variables for the class to use*********************************************************
    Context context;
    public JSONArray jsonArray;
    //********************************************************************************************************

    //Creating a constuctor for the books adapter*************************************************************
    public BooksAdapter (Context context, JSONArray jsonArray) {
        this.context = context;
        this.jsonArray = jsonArray;
    }
    //********************************************************************************************************

    //Getting the json items of the position clicked on*******************************************************
    @Override
    public JSONObject getItem(int position) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject = jsonArray.getJSONObject(position);
        }catch (JSONException error){
            error.printStackTrace();
        }
        return jsonObject;
    }
    //********************************************************************************************************

    //Setting the text of the view by the title***************************************************************
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv=new TextView(context);
        try{
            tv.setText(jsonArray.getJSONObject(position).getString("title"));
        }catch (JSONException error){
            error.printStackTrace();
        }
        return tv;
    }
    //********************************************************************************************************

    //public methods that will return a value of how many there are in json and the items position************
    @Override
    public int getCount() {
        return jsonArray.length();
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    //********************************************************************************************************
}
