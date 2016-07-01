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
    public int position;

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
        public static final String BOOKS_TABLE_NAME = "books";
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
            DataEntryBooks.BOOKS_TABLE_NAME + " (" +
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
            DataEntryBooks.BOOKS_TABLE_NAME;
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
        values.put(DataEntryBooks.COLUMN_DATE, books.getDate());
        db.insertOrThrow(DataEntryBooks.BOOKS_TABLE_NAME, null, values);

    }

    public void insertRowPublishers(Publisher publishers) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataEntryPublishers.COLUMN_COMPANY, publishers.getCompany());
        values.put(DataEntryPublishers.COLUMN_TITLE, publishers.getTitle());
        values.put(DataEntryPublishers.COLUMN_WPRICE, publishers.getWholesalePrice());
        db.insertOrThrow(DataEntryPublishers.TABLE_NAME, null, values);
    }



    // Get books to show up on grid view
    private BookCursorWrapper queryBooks() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books", null);
        return new BookCursorWrapper(cursor);
    }

    public ArrayList<Book> getBooks(){
        ArrayList<Book> books = new ArrayList<>();
        BookCursorWrapper cursor = queryBooks();
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                books.add(cursor.getBook());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return books;
    }

    // Get book to show up in detail activity
    private BookCursorWrapper queryBook(int bookID) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books WHERE _id = " + bookID, null);
        return new BookCursorWrapper(cursor);
    }

    //Modify to just return a  book
    public Book getMyBook(int position) {
        Book book = null;
        BookCursorWrapper cursor = queryBook(position);
        try {
            cursor.moveToFirst();
            book = cursor.getBook();
        } finally {
            cursor.close();

            return book;
        }

    }
    // Use Joins to get the publisher for the detail view
    private BookCursorWrapper queryPublisher(int bookID) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM publishers INNER JOIN books ON books.title = publishers.title WHERE books._id = " + bookID, null);
        return new BookCursorWrapper(cursor);
    }

    //modify to just return a  book
    public Publisher getPublisher(int position) {
        Publisher publisher = null;
        BookCursorWrapper cursor = queryPublisher(position);
        try {
            cursor.moveToFirst();
            publisher = cursor.getPublisher();
        } finally {
            cursor.close();

            return publisher;
        }

    }


    // Search function query
    private BookCursorWrapper searchProducts(String query) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books WHERE title LIKE '%"+query+"%' or author LIKE '%"+query+"%'", null);
        return new BookCursorWrapper(cursor);
    }

    public ArrayList<Book> searchBooks(String query){
        ArrayList<Book> books = new ArrayList<>();
        BookCursorWrapper cursor = searchProducts(query);
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                books.add(cursor.getBook());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return books;
    }

    // Query for Lit and Fiction category
    private BookCursorWrapper queryLiterature() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books WHERE category LIKE 'Literature%'", null);
        return new BookCursorWrapper(cursor);
    }

    public ArrayList<Book> getLitBooks(){
        ArrayList<Book> books = new ArrayList<>();
        BookCursorWrapper cursor = queryLiterature();
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                books.add(cursor.getBook());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return books;
    }

    // Query for Mystery category
    private BookCursorWrapper queryMystery() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books WHERE category LIKE 'Mystery%'", null);
        return new BookCursorWrapper(cursor);
    }

    public ArrayList<Book> getMysBooks(){
        ArrayList<Book> books = new ArrayList<>();
        BookCursorWrapper cursor = queryMystery();
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                books.add(cursor.getBook());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return books;
    }

    //Query for Science Fiction & Fantasy
    private BookCursorWrapper queryFantasy() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books WHERE category LIKE 'Science%'", null);
        return new BookCursorWrapper(cursor);
    }

    public ArrayList<Book> getFanBooks(){
        ArrayList<Book> books = new ArrayList<>();
        BookCursorWrapper cursor = queryFantasy();
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                books.add(cursor.getBook());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return books;
    }

    // Sort by authors query
    private BookCursorWrapper sortAuthors() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books ORDER BY author", null);
        return new BookCursorWrapper(cursor);
    }

    public ArrayList<Book> getsortAuthors(){
        ArrayList<Book> books = new ArrayList<>();
        BookCursorWrapper cursor = sortAuthors();
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                books.add(cursor.getBook());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return books;
    }

    // Query for all books
    private BookCursorWrapper returnAll() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books", null);
        return new BookCursorWrapper(cursor);
    }

    public ArrayList<Book> getAll(){
        ArrayList<Book> books = new ArrayList<>();
        BookCursorWrapper cursor = returnAll();
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                books.add(cursor.getBook());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return books;
    }

    // Method to clean database
    public void cleanDataBase(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM books");
    }
}
