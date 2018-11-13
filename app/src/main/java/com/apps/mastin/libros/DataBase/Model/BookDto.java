package com.apps.mastin.libros.DataBase.Model;

import java.util.Date;

public class BookDto {

    public static final String TABLE_NAME = "book";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHORS_NUMBER = "authors";
    public static final String COLUMN_DATE = "date";

    private String bookTitle;
    private String bookDate;
    private int bookId, bookAuthorsNumber;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_AUTHORS_NUMBER + " INTEGER,"
                    + COLUMN_DATE + " TEXT"
                    + ")";


    public BookDto(int bookId, String bookTitle, String bookDate, int bookAuthorsNumber){
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthorsNumber = bookAuthorsNumber;
        this.bookDate = bookDate;
    }

    public int getBookAuthorsNumber() {
        return bookAuthorsNumber;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookAuthorsNumber(int bookAuthorsNumber) {
        this.bookAuthorsNumber = bookAuthorsNumber;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }
}
