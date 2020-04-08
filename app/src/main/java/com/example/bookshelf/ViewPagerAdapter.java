package com.example.bookshelf;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ArrayList<BookDetailsFragment> bookDetailsFragments;//Creating bookdetails arraylist
    public ViewPagerAdapter(FragmentManager fm, ArrayList<BookDetailsFragment> bookDetailsFragments) {//creating a constructor
        super(fm);//setting the super so it can be used
        this.bookDetailsFragments = bookDetailsFragments;//setting this to bookdetailsfragments
    }



    public void addBooks(ArrayList<Book> books) {
        bookDetailsFragments = new ArrayList<BookDetailsFragment>(); // Empty the array list containing the collection of fragments
        for (Book book : books) {
            bookDetailsFragments.add(BookDetailsFragment.newInstance(book));
            Log.d("New Books", book.getTitle()); // Populate
        }
        notifyDataSetChanged(); // FragmentStatePagetAdapter object.
    }
    //setting some getters in the functions below to return specific stuff****************************************************************
    @Override
    public Fragment getItem(int i) {
        return bookDetailsFragments.get(i);

    }

    @Override
    public int getCount() {
        return bookDetailsFragments.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
    //*************************************************************************************************************************************
}