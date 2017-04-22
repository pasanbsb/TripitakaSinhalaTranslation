package com.pbstudios.tripitaka.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.pbstudios.tripitaka.model.Bookmark;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pasan on 2/16/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    // database version
    private static final int DB_VERSION = 1;
    // database name
    private static final String DB_NAME = "TRIPITAKA_DB";
    private static final String TBL_BOOKMARKS = "BOOKMARKS";
    private static final String BOOKMARK_ID = "ID";
    private static final String BOOKMARK_NAME = "NAME";
    private static final String URL = "URL";

    private static final String TBL_SEARCH_TOPICS = "SEARCH_TOPICS";
    private static final String SEARCH_TOPIC = "SEARCH_TOPIC";


    private static final String[] COLUMNS = {BOOKMARK_ID, BOOKMARK_NAME, URL};

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_BOOK_TABLE = "CREATE TABLE BOOKMARKS ( " + "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME TEXT, " + "URL TEXT )";
        sqLiteDatabase.execSQL(CREATE_BOOK_TABLE);

        checkAndCreateSearchTable(sqLiteDatabase);
    }

    private void checkAndCreateSearchTable(SQLiteDatabase sqLiteDatabase) {
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '" + TBL_SEARCH_TOPICS + "'", null);

            if (cursor.getCount() < 0) {

                String CREATE_SEARCH_TABLE = "CREATE TABLE SEARCH_TOPICS ( " + "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "SEARCH_TOPIC TEXT, " + "URL TEXT )";
                sqLiteDatabase.execSQL(CREATE_SEARCH_TABLE);

                File f = new File("file:///android_asset/data/topics.txt");

                List<String> lines = FileUtils.readLines(f);

                for (String line : lines) {
                    String data[] = line.split(",");
                    ContentValues values = new ContentValues();
                    values.put(SEARCH_TOPIC, data[0]);
                    values.put(URL, data[1]);

                    sqLiteDatabase.insert(TBL_SEARCH_TOPICS, null, values);
                    sqLiteDatabase.close();
                }
            }
        } catch (Exception e) {
            Log.e("DBHelper", "error creating search table", e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BOOKMARKS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SEARCH_TOPICS");
        this.onCreate(sqLiteDatabase);
    }

    public void createBookmark(Bookmark bookmark) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BOOKMARK_NAME, bookmark.getBookmarkName());
        values.put(URL, bookmark.getUrl());

        db.insert(TBL_BOOKMARKS, null, values);
        db.close();
    }

    public Bookmark readBookmark(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_BOOKMARKS + " WHERE ID =" + id, null);

        if (cursor != null)
            cursor.moveToFirst();

        Bookmark bookmark = null;
        if (cursor.getCount() > 0) {
            bookmark = new Bookmark();
            bookmark.setId(Integer.parseInt(cursor.getString(0)));
            bookmark.setBookmarkName(cursor.getString(1));
            bookmark.setUrl(cursor.getString(2));
        }

        return bookmark;
    }

    public List getAllBookmarks() {
        List books = new LinkedList();

        String query = "SELECT  * FROM " + TBL_BOOKMARKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Bookmark bookmark = null;
        if (cursor.moveToFirst()) {
            do {
                bookmark = new Bookmark();
                bookmark.setId(Integer.parseInt(cursor.getString(0)));
                bookmark.setBookmarkName(cursor.getString(1));
                bookmark.setUrl(cursor.getString(2));

                books.add(bookmark);
            } while (cursor.moveToNext());
        }
        return books;
    }

    public void deleteBookmark(Bookmark book) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TBL_BOOKMARKS, BOOKMARK_ID + " = ?", new String[]{String.valueOf(book.getId())});
        db.close();
    }

    public List<Bookmark> searchBookmark(String searchQuery) {
        List books = new LinkedList();

        String query = "SELECT  * FROM " + TBL_BOOKMARKS + " WHERE " + SEARCH_TOPIC + " LIKE '%" + searchQuery + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Bookmark bookmark = null;
        if (cursor.moveToFirst()) {
            do {
                bookmark = new Bookmark();
                bookmark.setId(Integer.parseInt(cursor.getString(0)));
                bookmark.setBookmarkName(cursor.getString(1));
                bookmark.setUrl(cursor.getString(2));

                books.add(bookmark);
            } while (cursor.moveToNext());
        }
        return books;
    }
}
