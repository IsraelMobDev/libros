package com.apps.mastin.libros;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.apps.mastin.libros.Adapters.AuthorAdapter;
import com.apps.mastin.libros.DataBase.DataBaseHelper;
import com.apps.mastin.libros.DataBase.Model.AuthorDto;
import com.apps.mastin.libros.DataBase.Model.BookDto;

import java.util.ArrayList;
import java.util.Calendar;

public class AddEditBookActivity extends AppCompatActivity implements View.OnClickListener{

    AuthorAdapter adapter;
    ArrayList<AuthorDto> authorDtoArrayList;
    ArrayList<AuthorDto> selectedItems;
    ListView authorsList;
    ImageButton getDate;
    EditText bookTitle, dateText;

    //Para el DatePicker
    private static final String ZERO = "0";
    private static final String BAR = "/";
    public final Calendar c = Calendar.getInstance();
    final int month = c.get(Calendar.MONTH);
    final int day = c.get(Calendar.DAY_OF_MONTH);
    final int year = c.get(Calendar.YEAR);

    private DataBaseHelper dataBaseHelper;
    Intent editData;
    Bundle extras;
    int editBookId;
    BookDto editBookDto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_edit_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        authorDtoArrayList = new ArrayList<>();
        selectedItems = new ArrayList<AuthorDto>();
        authorsList = (ListView) findViewById(R.id.authorsList);
        getDate = (ImageButton) findViewById(R.id.getDateBtn);
        dateText = (EditText) findViewById(R.id.dateText);
        bookTitle = (EditText) findViewById(R.id.bookTitleEditText);

        editData = getIntent();
        extras = editData.getExtras();
        if(extras != null){
            if(extras.containsKey("BookID")){
                editBookId = extras.getInt("BookID");
                editBookDto = dataBaseHelper.getBook(editBookId);
                dateText.setText(editBookDto.getBookDate());
                bookTitle.setText(editBookDto.getBookTitle());
            }
        }



        authorDtoArrayList.addAll(dataBaseHelper.getAllAuthors());

        getDate.setOnClickListener(this);
        adapter = new AuthorAdapter(AddEditBookActivity.this,authorDtoArrayList);
        authorsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        authorsList.setAdapter(adapter);

        authorsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AuthorDto selectedItem = authorDtoArrayList.get(i);//((AuthorDto) view).getAuthorId();
                if(selectedItems.contains(selectedItem))
                    selectedItems.remove(selectedItem); //remove deselected item from the list of selected items
                else
                    selectedItems.add(selectedItem); //add selected item to the list of selected items
            }
        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(editBookDto != null){
            getMenuInflater().inflate(R.menu.edit_menu, menu);
        }else{
            getMenuInflater().inflate(R.menu.done_button, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save_btn) {
            String selectedAuthors = String.valueOf(selectedItems.size());
            if(editBookDto != null){
                editBookDto.setBookAuthorsNumber(selectedItems.size());
                editBookDto.setBookDate(dateText.getText().toString());
                editBookDto.setBookTitle(bookTitle.getText().toString());
                long bookId = dataBaseHelper.updateBook(editBookDto);
            }else{
                long bookId = dataBaseHelper.insertBook(bookTitle.getText().toString(), dateText.getText().toString(), selectedAuthors);
            }

            Toast.makeText(getApplicationContext(),"Guardado Correctamente",Toast.LENGTH_SHORT).show();
            onBackPressed();
            return true;
        }else if(id == R.id.remove_btn){
            dataBaseHelper.deleteBook(editBookDto);
            Toast.makeText(getApplicationContext(),"Se elimino Correctamente",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.getDateBtn:
                obtenerFecha();
                break;
        }
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? ZERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? ZERO + String.valueOf(mesActual):String.valueOf(mesActual);
                dateText.setText(diaFormateado + BAR + mesFormateado + BAR + year);
            }
        },year, month, day);
        recogerFecha.show();

    }
}
