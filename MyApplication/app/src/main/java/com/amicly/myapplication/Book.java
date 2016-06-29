package com.amicly.myapplication;

/**
 * Created by aaronfields on 6/24/16.
 */
public class Book {

    private String category;
    private String author;
    private String title;
    private double price;
    private String date;
    private String imageURI;
    private int ID;

    public Book(int ID, String category, String author, String title, double price, String date, String imageURI) {
        this.ID = ID;
        this.category = category;
        this.author = author;
        this.title = title;
        this.price = price;
        this.date = date;
        this.imageURI = imageURI;
    }

    public Book(String category, String author, String title, double price, String date, String imageURI) {
        this.category = category;
        this.author = author;
        this.title = title;
        this.price = price;
        this.date = date;
        this.imageURI = imageURI;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String name) {
            this.title = title;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getImageURI() {
            return imageURI;
        }

        public void setImageURI(String imageURI) {
            this.imageURI = imageURI;
        }


}
