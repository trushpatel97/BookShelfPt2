package com.example.bookshelflab;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
public class BookDetailFragment extends Fragment {//Extending fragment because its a fragment this fragment will show details of the book
    TextView textView;//Creating textView
    public static final String BOOK_TITLE_KEY = "book title";//Creating a default value
    String bookTitle;//Creating booktitle variable
    public BookDetailFragment() {//Creating a constructor as required
    }
    public static BookDetailFragment newInstance(String bookTitle) {//Creating a new instance that will be used depending on which book selected
        BookDetailFragment bookDetailsFragment = new BookDetailFragment();//Creating  bookDetailsFragment that will be used for a specific book
        Bundle args = new Bundle();//creating bundle that will be used for args
        args.putString(BOOK_TITLE_KEY, bookTitle);//putting the bookTitle to the booktitlekey
        bookDetailsFragment.setArguments(args);//setting the args from the bundle to communicate
        return bookDetailsFragment;//returning the bookDetailFragment
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {//on create this is what data we want passed
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();//get the bundle args
        if (args != null) {//until no more args to retrieve
            bookTitle = args.getString(BOOK_TITLE_KEY);//getString of booktitle
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {//onCreateView this is what we want to display
        textView = (TextView) inflater.inflate(R.layout.fragment_book_detail, container, false);//inflate book detail fragment
        if (bookTitle != null) {//until no more book titles
            displayBook(bookTitle);//display this book name
        }
        return textView;//return the textView
    }
    public void displayBook(String title) {//function to display book
        textView.setGravity(Gravity.CENTER);//center the text
        textView.setText(title);//set the text to the title
        textView.setTextSize(20);//change font size
    }
}
