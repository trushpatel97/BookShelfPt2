package com.example.bookshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.w3c.dom.Text;

import java.util.HashMap;


public class BookDetailsFragment extends Fragment {

    private static final String BOOK_KEY = "book";
    private Book book;
    String API_Link = "https://kamorris.com/lab/abp/booksearch.php?search=";
    TextView titleTextView, authorTextView;

    public BookDetailsFragment() {}

    public static BookDetailsFragment newInstance(Book book) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();

        /*
         A HashMap implements the Serializable interface
         therefore we can place a HashMap inside a bundle
         by using that put() method.
         */
        args.putParcelable(BOOK_KEY, book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.book = (Book) getArguments().getParcelable(BOOK_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_details, container, false);

        titleTextView = v.findViewById(R.id.titleTextView);
        authorTextView = v.findViewById(R.id.authorTextView);
        TextView title = v.findViewById(R.id.bookTitle);
        TextView author = v.findViewById(R.id.bookAuthor);
        ImageView coverImg = v.findViewById(R.id.bookCover);

        /*
        Because this fragment can be created with or without
        a book to display when attached, we need to make sure
        we don't try to display a book if one isn't provided
         */
        if (book != null){
            title.setText(book.getTitle());
            author.setText(book.getAuthor());
            Picasso.get().load(book.getCoverURL()).into(coverImg);
        }
        return v;
    }

    /*
    This method is used both internally and externally (from the activity)
    to display a book
     */
    public void displayBook(HashMap<String, String> book) {
        titleTextView.setText(book.get("title"));
        authorTextView.setText(book.get("author"));
    }
}
