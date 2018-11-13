package com.apps.mastin.libros.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.apps.mastin.libros.DataBase.Model.AuthorDto;
import com.apps.mastin.libros.DataBase.Model.BookDto;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "books_db";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BookDto.CREATE_TABLE);
        db.execSQL(AuthorDto.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BookDto.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AuthorDto.TABLE_NAME);
        onCreate(db);
    }

   //PARA LA TABLA BOOKS
   public long insertBook(String bookTitle, String bookDate, String bookAuthorsNumber) {
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put(BookDto.COLUMN_TITLE, bookTitle);
       values.put(BookDto.COLUMN_DATE, bookDate);
       values.put(BookDto.COLUMN_AUTHORS_NUMBER, bookAuthorsNumber);

       long id = db.insert(BookDto.TABLE_NAME, null, values);

       db.close();

       return id;
   }

    public BookDto getBook(long id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(BookDto.TABLE_NAME,
                new String[]{BookDto.COLUMN_ID, BookDto.COLUMN_TITLE, BookDto.COLUMN_AUTHORS_NUMBER,BookDto.COLUMN_DATE},
                BookDto.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        BookDto note = new BookDto(
                cursor.getInt(cursor.getColumnIndex(BookDto.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(BookDto.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(BookDto.COLUMN_DATE)),
                cursor.getInt(cursor.getColumnIndex(BookDto.COLUMN_AUTHORS_NUMBER))
        );

        cursor.close();

        return note;
    }

    public ArrayList<BookDto> getAllBooks() {
        ArrayList<BookDto> books = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + BookDto.TABLE_NAME + " ORDER BY " +
                BookDto.COLUMN_TITLE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BookDto bookDto = new BookDto(cursor.getInt(cursor.getColumnIndex(BookDto.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(BookDto.COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(BookDto.COLUMN_DATE)),
                        cursor.getInt(cursor.getColumnIndex(BookDto.COLUMN_AUTHORS_NUMBER))
                        );

                books.add(bookDto);
            } while (cursor.moveToNext());
        }

        db.close();

        return books;
    }

    public ArrayList<BookDto> getAllBooksOrderedByName() {
        ArrayList<BookDto> books = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + BookDto.TABLE_NAME + " ORDER BY " +
                BookDto.COLUMN_TITLE + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BookDto bookDto = new BookDto(cursor.getInt(cursor.getColumnIndex(BookDto.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(BookDto.COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(BookDto.COLUMN_DATE)),
                        cursor.getInt(cursor.getColumnIndex(BookDto.COLUMN_AUTHORS_NUMBER))
                );

                books.add(bookDto);
            } while (cursor.moveToNext());
        }

        db.close();

        return books;
    }

    public ArrayList<BookDto> getAllBooksOrderedByNumberOfAuthors() {
        ArrayList<BookDto> books = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + BookDto.TABLE_NAME + " ORDER BY " +
                BookDto.COLUMN_AUTHORS_NUMBER + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BookDto bookDto = new BookDto(cursor.getInt(cursor.getColumnIndex(BookDto.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(BookDto.COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(BookDto.COLUMN_DATE)),
                        cursor.getInt(cursor.getColumnIndex(BookDto.COLUMN_AUTHORS_NUMBER))
                );

                books.add(bookDto);
            } while (cursor.moveToNext());
        }

        db.close();

        return books;
    }

    public ArrayList<BookDto> getAllBooksOrderedByEditionDate() {
        ArrayList<BookDto> books = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + BookDto.TABLE_NAME + " ORDER BY " +
                BookDto.COLUMN_DATE + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BookDto bookDto = new BookDto(cursor.getInt(cursor.getColumnIndex(BookDto.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(BookDto.COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(BookDto.COLUMN_DATE)),
                        cursor.getInt(cursor.getColumnIndex(BookDto.COLUMN_AUTHORS_NUMBER))
                );

                books.add(bookDto);
            } while (cursor.moveToNext());
        }

        db.close();

        return books;
    }

    public int getBooksSize() {
        String countQuery = "SELECT  * FROM " + BookDto.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int updateBook(BookDto bookDto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BookDto.COLUMN_TITLE, bookDto.getBookTitle());
        values.put(BookDto.COLUMN_AUTHORS_NUMBER,bookDto.getBookAuthorsNumber());
        values.put(BookDto.COLUMN_DATE,bookDto.getBookDate());

        // updating row
        return db.update(BookDto.TABLE_NAME, values, BookDto.COLUMN_ID + " = ?",
                new String[]{String.valueOf(bookDto.getBookId())});
    }

    public void deleteBook(BookDto bookDto) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BookDto.TABLE_NAME, BookDto.COLUMN_ID + " = ?",
                new String[]{String.valueOf(bookDto.getBookId())});
        db.close();
    }

    //PARA LA TABLA DE AUTORES

    public long insertAuthor(String authorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AuthorDto.COLUMN_AUTHOR_NAME, authorName);

        long id = db.insert(AuthorDto.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public ArrayList<AuthorDto> getAllAuthors() {
        ArrayList<AuthorDto> authors = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + AuthorDto.TABLE_NAME + " ORDER BY " +
                AuthorDto.COLUMN_AUTHOR_NAME + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                AuthorDto authorDto = new AuthorDto(cursor.getInt(cursor.getColumnIndex(BookDto.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(AuthorDto.COLUMN_AUTHOR_NAME))
                );

                authors.add(authorDto);
            } while (cursor.moveToNext());
        }

        db.close();

        return authors;
    }



}