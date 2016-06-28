package com.amicly.myapplication;

/**
 * Created by aaronfields on 6/24/16.
 */
public class Publishers {

    private String company;
    private String title;
    private double wholesalePrice;

    public Publishers(String company, String title, double wholesalePrice) {
        this.company = company;
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
}
