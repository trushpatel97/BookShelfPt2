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

    Context context;
    public JSONArray jsonArray;

    public BooksAdapter (Context context, JSONArray jsonArray) {
        this.context = context;
        this.jsonArray = jsonArray;
    }



    @Override
    public int getCount() {
        return jsonArray.length();
    }

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

    @Override
    public long getItemId(int position) {
        return 0;
    }

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
}
