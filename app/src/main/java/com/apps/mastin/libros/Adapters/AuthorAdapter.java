package com.apps.mastin.libros.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.apps.mastin.libros.Model.AuthorDto;
import com.apps.mastin.libros.R;

import java.util.ArrayList;

public class AuthorAdapter extends BaseAdapter {

    Context context;
    private ArrayList<AuthorDto> data;
    private static LayoutInflater inflater = null;

    public AuthorAdapter(Context context, ArrayList<AuthorDto> data){
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
            vi = inflater.inflate(R.layout.item_author, null);

        CheckedTextView authorName = (CheckedTextView) vi.findViewById(R.id.authorName);
        authorName.setText(data.get(i).getAuthorName());
        return vi;
    }
}
