package com.amicly.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by aaronfields on 6/21/16.
 */
public class Helper extends SQLiteOpenHelper {
    private String category;
    private String author;
    private String title;
    private String date;
    private String image;
    private double price;
    private String priceDouble;
    private String company;

    public Helper(Context context) {
        super(context, "db", null, 1);
    }

    public static Helper INSTANCE;

    public static synchronized Helper getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new Helper(context.getApplicationContext());
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_BOOKS);
        db.execSQL(SQL_CREATE_ENTRIES_PUBLISHERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_BOOKS);
        db.execSQL(SQL_DELETE_ENTRIES_PUBLISHERS);
        onCreate(db);

    }

    public static abstract class DataEntryBooks implements BaseColumns {
        public static final String TABLE_NAME = "books";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_IMAGE = "image";

    }

    public static abstract class DataEntryPublishers implements BaseColumns {
        public static final String TABLE_NAME = "publishers";
        public static final String COLUMN_COMPANY = "company";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_WPRICE = "wholesalePrice";
    }

    private static final String SQL_CREATE_ENTRIES_BOOKS = "CREATE TABLE IF NOT EXISTS " +
            DataEntryBooks.TABLE_NAME + " (" +
            DataEntryBooks._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DataEntryBooks.COLUMN_CATEGORY + " TEXT," +
            DataEntryBooks.COLUMN_AUTHOR + " TEXT," +
            DataEntryBooks.COLUMN_TITLE + " TEXT," +
            DataEntryBooks.COLUMN_PRICE + " DOUBLE," +
            DataEntryBooks.COLUMN_IMAGE + " TEXT," +
            DataEntryBooks.COLUMN_DATE + " TEXT" + ")";

    private static final String SQL_CREATE_ENTRIES_PUBLISHERS = "CREATE TABLE IF NOT EXISTS " +
            DataEntryPublishers.TABLE_NAME + " (" +
            DataEntryPublishers._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DataEntryPublishers.COLUMN_COMPANY + " TEXT," +
            DataEntryPublishers.COLUMN_TITLE + " TEXT," +
            DataEntryPublishers.COLUMN_WPRICE + " DOUBLE" + ")";

    private static final String SQL_DELETE_ENTRIES_BOOKS = "DROP TABLE IF EXISTS " +
            DataEntryBooks.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_PUBLISHERS = "DROP TABLE IF EXISTS " +
            DataEntryPublishers.TABLE_NAME;

    public void insertRow(Book books) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataEntryBooks.COLUMN_CATEGORY, books.getCategory());
        values.put(DataEntryBooks.COLUMN_AUTHOR, books.getAuthor());
        values.put(DataEntryBooks.COLUMN_TITLE, books.getTitle());
        values.put(DataEntryBooks.COLUMN_PRICE, books.getPrice());
        values.put(DataEntryBooks.COLUMN_IMAGE, books.getImageURI());
        values.put(DataEntryBooks.COLUMN_DATE, books.getPrice());
        db.insertOrThrow(DataEntryBooks.TABLE_NAME, null, values);

    }

    public void insertRowPublishers(Publishers publishers) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataEntryPublishers.COLUMN_COMPANY, publishers.getCompany());
        values.put(DataEntryPublishers.COLUMN_TITLE, publishers.getTitle());
        values.put(DataEntryPublishers.COLUMN_WPRICE, publishers.getWholesalePrice());
        db.insertOrThrow(DataEntryPublishers.TABLE_NAME, null, values);
    }

    public String getLiterature() {
        String result = "Nothing found";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM books WHERE category = Literature%";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        result = String.format(cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_AUTHOR)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_CATEGORY)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_IMAGE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_PRICE)));
        cursor.moveToNext();
        cursor.close();
        return result;
    }

    public String getMystery() {
        String result = "Nothing found";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM books WHERE category = Mystery%";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        result = String.format(cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_AUTHOR)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_CATEGORY)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_IMAGE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_PRICE)));
        cursor.moveToNext();
        cursor.close();
        return result;
    }

    public String getFantasy() {
        String result = "Nothing found";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM books WHERE category = Science%";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        result = String.format(cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_AUTHOR)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_CATEGORY)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_IMAGE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_PRICE)));
        cursor.moveToNext();
        cursor.close();
        return result;
    }

    public String sortByAuthor() {
        String result = "Nothing found";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM books ORDER BY author";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        result = String.format(cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_AUTHOR)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_CATEGORY)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_IMAGE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_PRICE)));
        cursor.moveToNext();
        cursor.close();
        return result;
    }

    public String sortByDate() {
        String result = "Nothing found";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM books ORDER BY date";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        result = String.format(cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_AUTHOR)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_CATEGORY)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_IMAGE)),
                cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_PRICE)));
        cursor.moveToNext();
        cursor.close();
        return result;
    }

    public Book getEverything() {
        String result = "Nothing found";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM books";
        Cursor cursor = db.rawQuery(query, null);
        while () {
            cursor.moveToFirst();
            result = String.format(cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_AUTHOR)),
                    cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_CATEGORY)),
                    cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_DATE)),
                    cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_IMAGE)),
                    cursor.getString(cursor.getColumnIndex(DataEntryBooks.COLUMN_PRICE)));
            cursor.moveToNext();
            cursor.close();
        }


        Book book = new Book(category, author, title, price, date, image);
        String category = book.getCategory();
        String title = book.getTitle();
        String author = book.getAuthor();
        String date = book.getDate();
        double price = book.getPrice();
        String priceDouble = Double.toString(price);
        String imageURI = book.getImageURI();




        return book;
    }


}
