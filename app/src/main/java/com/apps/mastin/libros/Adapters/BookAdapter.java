package com.apps.mastin.libros.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apps.mastin.libros.Model.BookDto;
import com.apps.mastin.libros.R;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {

    Context context;
    private ArrayList<BookDto> data;
    private static LayoutInflater inflater = null;

    public BookAdapter(Context context, ArrayList<BookDto> data){
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View vi = view;
        if (vi == null)
            vi = inflater.inflate(R.layout.item_book, null);

        //Declaracion de Variables
        TextView title = (TextView) vi.findViewById(R.id.bookTitle);
        TextView date = (TextView) vi.findViewById(R.id.bookEdition);
        TextView authors = (TextView) vi.findViewById(R.id.bookAuthors);

        title.setText(data.get(i).getBookTitle());
        date.setText(data.get(i).getBookDate());
        String authorsNumber = String.valueOf(data.get(i).getBookAuthorsNumber());
        authors.setText(authorsNumber);

        return vi;

    }
}
