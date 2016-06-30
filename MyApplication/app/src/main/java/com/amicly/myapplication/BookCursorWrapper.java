package com.amicly.myapplication;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by aaronfields on 6/28/16.
 */
public class BookCursorWrapper extends CursorWrapper {
    private String category;
    private String author;
    private String title;
    private String date;
    private String image;
    private double price;
    private String priceDouble;
    private String company;
    private double wholesalePrice;


    public BookCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Book getBook() {
        int id = getInt(getColumnIndex(Helper.DataEntryBooks._ID));
        String title = getString(getColumnIndex(Helper.DataEntryBooks.COLUMN_TITLE));
        String author = getString(getColumnIndex(Helper.DataEntryBooks.COLUMN_AUTHOR));
        double price = getDouble(getColumnIndex(Helper.DataEntryBooks.COLUMN_PRICE));
        String image = getString(getColumnIndex(Helper.DataEntryBooks.COLUMN_IMAGE));
        String category = getString(getColumnIndex(Helper.DataEntryBooks.COLUMN_CATEGORY));
        String date = getString(getColumnIndex(Helper.DataEntryBooks.COLUMN_DATE));

        Book book = new Book(id, category, author, title, price, date, image);
        book.setID(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);
        book.setImageURI(image);
        book.setCategory(category);
        book.setDate(date);

        return book;

    }

    public Publisher getPublisher() {
        int id = getInt(getColumnIndex(Helper.DataEntryPublishers._ID));
        String company = getString(getColumnIndex(Helper.DataEntryPublishers.COLUMN_COMPANY));
        String title = getString(getColumnIndex(Helper.DataEntryPublishers.COLUMN_TITLE));
        double wholesalePrice = getDouble(getColumnIndex(Helper.DataEntryPublishers.COLUMN_WPRICE));

        Publisher publisher = new Publisher(id, company, title, wholesalePrice);
        publisher.setID(id);
        publisher.setCompany(company);
        publisher.setTitle(title);
        publisher.setWholesalePrice(wholesalePrice);

        return publisher;
    }

}
