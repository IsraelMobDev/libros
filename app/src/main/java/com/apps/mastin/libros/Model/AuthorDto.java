package com.apps.mastin.libros.Model;

public class AuthorDto {
    private int authorId;
    private String authorName;

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

