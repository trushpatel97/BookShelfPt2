package com.example.bookshelf;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BookListFragment.retrieveBooks {

    private boolean isTwoPane;//checking if it is a two pane screen

    BookListFragment bookListFragment;//creating a booklist fragment
    BookDetailsFragmentLandscape bookDetailsFragmentLandscape;//creating a details landscape
    ViewPagerAdapter viewPagerAdapter;//creating view pager adapter helps manage lifecycle of each page
    ViewPager viewPager;//creating a viewpager variable

    TextView searchBox;//creating a textview for the searchbox
    Button searchButton;//creating a button for search
    TextView searchBoxLandscape;//creating one for landscape
    Button searchButtonLandscape;//creating one for landscape
    String baseURL = "https://kamorris.com/lab/abp/booksearch.php?search=";//getting the api url
    Book currentBook;//creating a book variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isTwoPane = findViewById(R.id.bookListView) != null;//seeing if booklistview is two pane is or is not null  and will return true or false


        if (isTwoPane) {//if it is true then run code below
            //Landscape mode
            searchBoxLandscape = findViewById(R.id.searchBoxLandscape);//find searchboxlandscape
            searchButtonLandscape = findViewById(R.id.searchButtonLandscape);//find searchbuttonlandscape
            bookListFragment = new BookListFragment();//create a new booklistfragment
            bookDetailsFragmentLandscape = new BookDetailsFragmentLandscape();//create a new bookdetailsfragment
            getSupportFragmentManager().beginTransaction().replace(R.id.bookListView, bookListFragment).commit();//we want to use it so we begin transcation and then commit to apply changes
            getSupportFragmentManager().beginTransaction().replace(R.id.bookListView, bookDetailsFragmentLandscape).commit();//we want to use it so we begin transcation and then commit to apply changes
            searchButtonLandscape.setOnClickListener(new View.OnClickListener() {//waiting for a click listen
                @Override
                public void onClick(View v) {//on click do this
                    final String searchQueryLandscape = searchBoxLandscape.getText().toString();//get the text and make it into a string
                    Thread t2 = new Thread() {//on the second thread not the main do this
                        @Override
                        public void run() {

                            URL bookUrl;//declare bookurl

                            try {//try this
                                bookUrl = new URL(baseURL + searchQueryLandscape);//it will go through the search and append it to the url

                                BufferedReader reader = new BufferedReader(//reading for input block
                                        new InputStreamReader(bookUrl.openStream()));

                                String response = "", tmpResponse;//temp response

                                tmpResponse = reader.readLine();//read the line enetered character by character
                                while (tmpResponse != null) {//until null has reached keep running code below
                                    response = response + tmpResponse;//response is added to response with the temp
                                    tmpResponse = reader.readLine();//read the next line of the response
                                }

                                JSONArray bookArray = new JSONArray(response);//create a new book with the response given
                                Message msg = Message.obtain();//get the message
                                msg.obj = bookArray;//message obj is the bookArray
                                bookResponseHandlerLandscape.sendMessage(msg);//sending the message that the book has been recieved
                            } catch (Exception e) {//throw an exception error
                                e.printStackTrace();//print the error
                            }
                        }
                    };
                    t2.start();//start the thread
                }
            });
        } else {//else we want to run it in portrait mode do pretty much the same thing
            //Portrait mode
            viewPager = findViewById(R.id.viewPager);
            searchBox = findViewById(R.id.searchBox);
            searchButton = findViewById(R.id.searchButton);
            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String searchQuery = searchBox.getText().toString();
                    Thread t = new Thread() {
                        @Override
                        public void run() {

                            URL bookUrl;

                            try {
                                bookUrl = new URL("https://kamorris.com/lab/abp/booksearch.php?search=" + searchQuery);

                                BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(bookUrl.openStream()));

                                String response = "", tmpResponse;

                                tmpResponse = reader.readLine();
                                while (tmpResponse != null) {
                                    response = response + tmpResponse;
                                    tmpResponse = reader.readLine();
                                }

                                JSONArray bookArray = new JSONArray(response);
                                Message msg = Message.obtain();
                                msg.obj = bookArray;
                                bookResponseHandler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    t.start();
                }
            });
        }

    }

    Handler bookResponseHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {//Handle the response so if we decide to go back for whatever reason
            ArrayList<Book> bookArrayList = new ArrayList<>();//Creating arraylist
            JSONArray responseArray = (JSONArray) msg.obj;//jsonArray for the msg

            try {//try this
                for (int i = 0; i < responseArray.length(); i++) {//go through all the responses json object
                    currentBook = new Book((JSONObject) responseArray.get(i));//then set it to current book we are at
                    bookArrayList.add(currentBook);//then add that book to bookarraylist
                }
            } catch (Exception e) {//throw an error
                e.printStackTrace();//print the error
            }
            setViewPagerAdapter(bookArrayList);//set the viewPager to this bookArrayList to go back to the list
            return false;
        }
    });
    Handler bookResponseHandlerLandscape = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {//do the same for landscape except update the views this time
            JSONArray responseArray = (JSONArray) msg.obj;
            updateViews(responseArray);
            return false;
        }
    });
    @Override
    public void bookSelected(Book book) {//see which book we selected
        bookDetailsFragmentLandscape.displayBookName(book);//get that book that we selected

    }

    public void setViewPagerAdapter(ArrayList<Book> bookList) {//Adding booklist to the viewpager adapter
        ((ViewPagerAdapter) viewPager.getAdapter()).addBooks(bookList);
    }

    public void updateViews(JSONArray jsonArray) {//updating the views
        bookListFragment = new BookListFragment();
        bookListFragment.setJsonArray(jsonArray);
        bookDetailsFragmentLandscape = new BookDetailsFragmentLandscape();
        getSupportFragmentManager().beginTransaction().replace(R.id.bookListView, bookListFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.bookDetailsLandscape, bookDetailsFragmentLandscape).commit();
    }


}