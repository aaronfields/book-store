package com.amicly.myapplication;

/**
 * Created by aaronfields on 6/24/16.
 */
public class Publisher {

    private String company;
    private String title;
    private double wholesalePrice;
    private int ID;

    public Publisher(String company, String title, double wholesalePrice) {
        this.company = company;
        this.title = title;
        this.wholesalePrice = wholesalePrice;
    }

    public Publisher(int ID, String company, String title, double wholesalePrice) {
        this.ID = ID;
        this.company = company;
        this.title = title;
        this.wholesalePrice = wholesalePrice;
    }



    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
