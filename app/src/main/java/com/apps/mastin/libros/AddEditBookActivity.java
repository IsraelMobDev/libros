package com.apps.mastin.libros;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.mastin.libros.Adapters.AuthorAdapter;
import com.apps.mastin.libros.Model.AuthorDto;

import java.util.ArrayList;
import java.util.Calendar;

public class AddEditBookActivity extends AppCompatActivity implements View.OnClickListener{

    AuthorAdapter adapter;
    ArrayList<AuthorDto> authorDtoArrayList;
    ListView authorsList;
    ImageButton getDate;
    EditText dateText;

    //Para el DatePicker
    private static final String ZERO = "0";
    private static final String BAR = "/";
    public final Calendar c = Calendar.getInstance();
    final int month = c.get(Calendar.MONTH);
    final int day = c.get(Calendar.DAY_OF_MONTH);
    final int year = c.get(Calendar.YEAR);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_edit_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        authorDtoArrayList = new ArrayList<>();
        authorsList = (ListView) findViewById(R.id.authorsList);
        getDate = (ImageButton) findViewById(R.id.getDateBtn);
        dateText = (EditText) findViewById(R.id.dateText);

        ///Datos de prueba
        AuthorDto authorDto = new AuthorDto(6353524,"Marco Bolton");
        authorDtoArrayList.add(authorDto);
        authorDtoArrayList.add(authorDto);
        ///

        getDate.setOnClickListener(this);
        adapter = new AuthorAdapter(AddEditBookActivity.this,authorDtoArrayList);
        authorsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        authorsList.setAdapter(adapter);

        authorsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
        getMenuInflater().inflate(R.menu.done_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save_btn) {




            return true;
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
