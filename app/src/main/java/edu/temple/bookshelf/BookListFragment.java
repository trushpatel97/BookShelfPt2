package com.example.bookshelflab;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
public class BookListFragment extends Fragment {
    ArrayList<String> books;//Creating an array list for the books
    public final static String BOOKS_KEY = "books";//Giving it a string for the books
    private OnBookSelectedInterface mListener;//Interface variable
    public BookListFragment() {//empty constructor required for fragment
        // Required empty public constructor
    }
    public interface OnBookSelectedInterface {//interface for communication
        void bookSelected(int position);//we want to give it the bookSelected's position later
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param books ArrayList<String> of book titles
     * @return A new instance of fragment BookListFragment.
     */
    public static BookListFragment newInstance(ArrayList<String> books) {//Creating the new instance that will be used to communicate the book selected
        BookListFragment bookListFragment = new BookListFragment();//new fragment list
        Bundle args = new Bundle();//making bundle for communication
        args.putStringArrayList(BOOKS_KEY, books);//puuting the book name in the key
        bookListFragment.setArguments(args);//setting the arguement to take bundle
        return bookListFragment;//return this specific book list fragment
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {//onCreate this is what we want to load onCreate passing the data to be specific
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();//getting the arguements on create
        if (args != null) {//if we got arguements, then we want to run through all of them until we have no more
            books = args.getStringArrayList(BOOKS_KEY);//get the string's array list specific element by key
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {//OnCreateView this is what we want to display
        // Inflate the layout for this fragment
        ListView listView = (ListView) inflater.inflate(R.layout.fragment_book_list, container, false);//Inflate the fragmentBookList
        listView.setAdapter(new ArrayAdapter<>((Context) mListener, android.R.layout.simple_list_item_1, books));//set adapter to the books
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//on item selection run code below
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//onItemClick, we want to listen to which position was selected
                if(position==0){//if position 0
                    mListener.bookSelected(position);//tell listener we selected this book
                }else if(position==1){//if position 1
                    mListener.bookSelected(position);//tell listener we selected this book
                }else if(position==2){//if position 2
                    mListener.bookSelected(position);//tell listener we selected this book
                }else if(position==3){//if position 3
                    mListener.bookSelected(position);//tell listener we selected this book
                }else if(position==4){//if position 4
                    mListener.bookSelected(position);//tell listener we selected this book
                }else if(position==5){//if position 5
                    mListener.bookSelected(position);//tell listener we selected this book
                }else if(position==6){//if position 6
                    mListener.bookSelected(position);//tell listener we selected this book
                }else if(position==7){//if position 7
                    mListener.bookSelected(position);//tell listener we selected this book
                }else if(position==8){//if position 8
                    mListener.bookSelected(position);//tell listener we selected this book
                }else if(position==9){//if position 9
                    mListener.bookSelected(position);//tell listener we selected this book
                }
            }
        });
        return listView;//return this list view
    }
    @Override
    public void onAttach(Context context) {//onAttach,  called when a fragment is attached to activity
        super.onAttach(context);
        if (context instanceof OnBookSelectedInterface) {//if context is an instance of onBOokSelectedInterface
            mListener = (OnBookSelectedInterface) context;//then we want to set listener to context
        } else {//else
            throw new RuntimeException(context.toString()
                    + " must implement OnBookSelectedInterface");//throw this exception saying we have not implemented an interface
        }
    }
    @Override
    public void onDetach() {//onDetach from the activtity
        super.onDetach();
        mListener = null;//then we want to set listener to null
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}

