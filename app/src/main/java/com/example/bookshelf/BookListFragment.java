package com.example.bookshelf;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class BookListFragment extends Fragment {

    //Made class variables and JSON that will be used to retrieve book info****************************************
    private static final String BOOK_LIST_KEY = "booklist";
    JSONArray jsonArray;
    BooksAdapter booksAdapter;
    ListView bookList;
    BookSelectedInterface parentActivity;
    //************************************************************************************************************


    //Creating a new instance of  Books that will basically let us set arguments for communication****************
    public static BookListFragment newInstance(ArrayList<HashMap<String, String>> books) {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();

        /*
         A HashMap implements the Serializable interface
         therefore we can place a HashMap inside a bundle
         by using that put() method.
         */
        args.putSerializable(BOOK_LIST_KEY, books);
        fragment.setArguments(args);
        return fragment;
    }
    //**********************************************************************************************************

    //On attach it will tell us if we implemented the interface or not******************************************
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        /*
         This fragment needs to communicate with its parent activity
         so we verify that the activity implemented our known interface
         */
        if (context instanceof BookSelectedInterface) {
            parentActivity = (BookSelectedInterface) context;
        } else {
            throw new RuntimeException("Please implement the required interface(s)");
        }
    }
    //*********************************************************************************************************

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //bookList = (ArrayList) getArguments().getSerializable(BOOK_LIST_KEY);
        }
    }

    //onCreateView it will show you the list of the books in this fragment*********************************
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_list, container, false);
        bookList = v.findViewById(R.id.bookListView);
        //Waiting to see what you clicked
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = null;
                try {
                    book = new Book((JSONObject) jsonArray.get(position));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ((retrieveBooks) getActivity()).bookSelected(book);
            }
        });


        // Inflate the layout for this fragment
        return v;
    }
    //***********************************************************************************************************

    //Interface for communicating with attached activity*********************************************************
    interface BookSelectedInterface {
        void bookSelected(int index);
    }
    //***********************************************************************************************************

    //Filling up the json array**********************************************************************************
    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
        booksAdapter = new BooksAdapter(getContext(), jsonArray);
        bookList.setAdapter(booksAdapter);
        booksAdapter.notifyDataSetChanged();
    }
    //**********************************************************************************************************

    //Interface created to communicate with the mainActivity this will tell us which book is selected**********
    public interface retrieveBooks {
        void bookSelected(Book book);
    }
    //*********************************************************************************************************
}