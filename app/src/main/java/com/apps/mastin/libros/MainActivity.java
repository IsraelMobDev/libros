package com.apps.mastin.libros;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.apps.mastin.libros.Adapters.BookAdapter;
import com.apps.mastin.libros.Model.BookDto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BookAdapter adapter;
    ArrayList<BookDto> bookDtoArrayList;
    ListView booksList;
    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookDtoArrayList = new ArrayList<>();
        booksList = (ListView) findViewById(R.id.booksList);
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);

        //Datos de prueba
        BookDto book = new BookDto("948474636","Cien años de soledad","20 de agosto del 2012",2);
        bookDtoArrayList.add(book);
        bookDtoArrayList.add(book);
        bookDtoArrayList.add(book);
        //


        adapter = new BookAdapter(MainActivity.this,bookDtoArrayList);
        booksList.setAdapter(adapter);

        //Para agregar un nuevo libro
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openEditBook = new Intent(getApplicationContext(),AddEditBookActivity.class);
                startActivity(openEditBook);
            }
        });

    }
}
