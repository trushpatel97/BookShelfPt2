package com.example.bookshelf;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;
public class BookDetailsFragmentLandscape extends Fragment {
    TextView titleLandscape;
    TextView authorLandscape;
    ImageView coverLandscape;
    public BookDetailsFragmentLandscape() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_details_landscape, container, false);
        titleLandscape = v.findViewById(R.id.bookTitleLandscape);
        authorLandscape = v.findViewById(R.id.bookAuthorLandscape);
        coverLandscape = v.findViewById(R.id.bookCoverLandscape);
        return v;
    }
    public void displayBookName(Book book) {
        titleLandscape.setText(book.getTitle());
        authorLandscape.setText(book.getAuthor());
        Picasso.get().load(book.getCoverURL()).into(coverLandscape);
    }
}