package com.apps.mastin.libros;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.apps.mastin.libros.Adapters.BookAdapter;
import com.apps.mastin.libros.DataBase.DataBaseHelper;
import com.apps.mastin.libros.DataBase.Model.BookDto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BookAdapter adapter;
    ArrayList<BookDto> bookDtoArrayList;
    ListView booksList;
    FloatingActionButton fabAdd;
    private DataBaseHelper dataBaseHelper;
    RadioGroup filterRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        booksList = (ListView) findViewById(R.id.booksList);
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        filterRadioGroup = (RadioGroup) findViewById(R.id.filterRadioGroup);
        filterRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.nameOrder){
                    bookDtoArrayList = new ArrayList<>();
                    bookDtoArrayList.addAll(dataBaseHelper.getAllBooksOrderedByName());
                    adapter = new BookAdapter(MainActivity.this,bookDtoArrayList);
                    booksList.setAdapter(adapter);
                }else if(i == R.id.dateOrder){
                    bookDtoArrayList = new ArrayList<>();
                    bookDtoArrayList.addAll(dataBaseHelper.getAllBooksOrderedByEditionDate());
                    adapter = new BookAdapter(MainActivity.this,bookDtoArrayList);
                    booksList.setAdapter(adapter);
                }else{
                    bookDtoArrayList = new ArrayList<>();
                    bookDtoArrayList.addAll(dataBaseHelper.getAllBooksOrderedByNumberOfAuthors());
                    adapter = new BookAdapter(MainActivity.this,bookDtoArrayList);
                    booksList.setAdapter(adapter);
                }
            }
        });


        //Para agregar un nuevo libro
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openEditBook = new Intent(getApplicationContext(),AddEditBookActivity.class);
                startActivity(openEditBook);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        bookDtoArrayList = new ArrayList<>();
        bookDtoArrayList.addAll(dataBaseHelper.getAllBooks());
        adapter = new BookAdapter(MainActivity.this,bookDtoArrayList);
        booksList.setAdapter(adapter);
    }
}
