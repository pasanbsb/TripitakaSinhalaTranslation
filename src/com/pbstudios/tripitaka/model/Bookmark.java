package com.pbstudios.tripitaka.model;

/**
 * Created by Pasan on 2/16/2016.
 */
public class Bookmark {
    private int id;
    private String bookmarkName;
    private String url;

    public Bookmark() {
    }

    public Bookmark(int id, String bookmarkName, String url) {
        this.id = id;
        this.bookmarkName = bookmarkName;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", bookmarkName='" + bookmarkName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
