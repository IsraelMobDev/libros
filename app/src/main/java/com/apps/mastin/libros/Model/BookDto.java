package com.apps.mastin.libros.Model;

public class BookDto {
    private String bookId, bookTitle, bookDate;
    private int bookAuthorsNumber;

    public BookDto(String bookId, String bookTitle, String bookDate, int bookAuthorsNumber){
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthorsNumber = bookAuthorsNumber;
        this.bookDate = bookDate;
    }

    public int getBookAuthorsNumber() {
        return bookAuthorsNumber;
    }

    public String getBookId() {
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

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }
}
