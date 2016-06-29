package com.amicly.myapplication;

import java.util.ArrayList;

/**
 * Created by aaronfields on 6/27/16.
 */
public class Singleton {

    private static Singleton singleton;
    private static ArrayList<Book> books;

    private Singleton(){
        books = new ArrayList<>();
    }

    public static Singleton getInstance(){
        if(singleton == null)
            singleton = new Singleton();
        return singleton;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
