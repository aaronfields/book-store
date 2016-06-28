package com.amicly.myapplication;

/**
 * Created by aaronfields on 6/21/16.
 */
public class Product {

    private String name;
    private String author;
    private String size;
    private double price;

    public Product(String name, String author, String size, double price) {
        this.name = name;
        this.author = author;
        this.size = size;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
