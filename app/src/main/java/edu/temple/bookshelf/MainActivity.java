package com.example.bookshelflab;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BookListFragment.OnBookSelectedInterface {
    BookDetailFragment bookDetailsFragment;//creating a variable for bookdetailfragment so we can use it to do different actions such as specifying which container should handle what fragment
    ArrayList<String> books = new ArrayList<>();//creating a arraylist to store the books
    ArrayList<HashMap> book;
    boolean smallScreen;//do we have a small screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {//using this onCreate to do stuff once program starts
        super.onCreate(savedInstanceState);//on create do this
        setContentView(R.layout.activity_main);//we will be using the activity main
        Resources res = getResources();//Here i am trying to get the resources
        int i;
        Resources resources = getResources();
        final String [] titles = resources.getStringArray(R.array.titles);
        final String [] authors = resources.getStringArray(R.array.authors);
        books = new ArrayList<>();
        for(i=0;i<titles.length;i++){
            HashMap book = new HashMap();
            book.put("title", titles[i]);
            book.put("author", authors[i]);
        }
        HashMap initialBook = new HashMap();
        initialBook.put("title", "Default book");
        initialBook.put("author", "Default author");
        books.addAll(Arrays.asList(res.getStringArray(R.array.titles)));//Gets all the titles and adds it to the arraylist
        smallScreen = (findViewById(R.id.container_2) == null);// Check if we're using a small screen. If so we dont want to use container two in the same pane later on
        Fragment c1frag= getSupportFragmentManager().findFragmentById(R.id.container_1);//getsupportfragmentmanager is used for transactions for adding removing and replacing for container1fragment

        if (c1frag == null && smallScreen) { // if container_1 has no Fragment already attached to it and we're using a small screen // Attaching ViewPagerFragment
            getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.container_1,new Activity().commit();//Starting transaction and allowing to go back if the view changes then adding only the first container to show
        } else if (c1frag instanceof BookListFragment && smallScreen) { // if container1Fragment is a BookListFragment, meaning we're coming back to singlePane from landscape mode // Attaching ViewPagerFragment
            getSupportFragmentManager().beginTransaction().replace(R.id.container_1, new Activity().commit();
        } else { // it's not small screen or its null // Attaching BookListFragment
            getSupportFragmentManager().beginTransaction().replace(R.id.container_1, BookListFragment.newInstance(books)).commit();//if its not a smallScreen then we want to create a newInstance and have BookListFragment accept books
        }
    }
    @Override
    public void bookSelected(int pos) {//this is a interface that will be used for communication between the fragment to activity
        String bookname = books.get(pos); // Get bookTitle for the selected position
        // Add bookTitle to bundle to be passed to the BookDetailsFragment
        bookDetailsFragment = new BookDetailFragment();//create a new BookDetailFragment
        Bundle detailsBundle = new Bundle();//making a bundle this will be use for communication
        detailsBundle.putString(BookDetailFragment.BOOK_TITLE_KEY, bookname);//Passing for communication (the string) and placing the bookname to its key
        bookDetailsFragment.setArguments(detailsBundle);//setting the arguements to the detailsBundle this is what will be passed onto for the communication to use
        if (!smallScreen) {//if its not a smallScreen then we want to have another container
            // container_2 should always attach the BookDetailsFragment if not in singlePane
            getSupportFragmentManager().beginTransaction().addToBackStack(null); // allows user to hit back arrow and go back to last BookDetailsFragment rather than going back to home screen and closing the app.replace(R.id.container_2, bookDetailsFragment).commit();
        }
    }
}
