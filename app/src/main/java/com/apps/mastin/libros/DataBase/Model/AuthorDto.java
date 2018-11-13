package com.apps.mastin.libros.DataBase.Model;

public class AuthorDto {
    private int authorId;
    private String authorName;

    public static final String TABLE_NAME = "author";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AUTHOR_NAME = "authorName";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_AUTHOR_NAME + " TEXT"
                    + ")";

    public AuthorDto(int authorId, String authorName){
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}

