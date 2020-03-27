package edu.temple.bookshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.res.Resources;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BookListFragment.OnFragmentInteractionListener {
    boolean isPortrait;
    ArrayList<HashMap> books;
    FragmentManager fm;
    BookListFragment listFragment;
    BookDetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        isPortrait = (findViewById(R.id.detail_fl)==null)? true:false;
        fm = getSupportFragmentManager();

        listFragment = BookListFragment.newInstance(books);
        HashMap initialBook = new HashMap();
        initialBook.put("title", "Default book");
        initialBook.put("author", "Default author");
        detailFragment = BookDetailFragment.newInstance(initialBook);
        fm.beginTransaction().add(R.id.main_fl, listFragment).addToBackStack(null).commit();

    }
    @Override
    public void bookSelected(int position) {
        HashMap bookTitle = books.get(position); // Get bookTitle for the selected position

        // Add bookTitle to bundle to be passed to the BookDetailsFragment
        detailFragment = new BookDetailFragment();
        Bundle detailsBundle = new Bundle();
        detailsBundle.putSerializable(BookDetailFragment.BOOK_ID_KEY, bookTitle);
        detailFragment.setArguments(detailsBundle);

        if (!isPortrait) {
            // container_2 should always attach the BookDetailsFragment if not in singlePane
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null) // allows user to hit back arrow and go back to last BookDetailsFragment rather than going back to home screen and closing the app
                    .replace(R.id.detail_fl, detailFragment)
                    .commit();
        }
    }


}
