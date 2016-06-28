package com.amicly.myapplication;

/**
 * Created by aaronfields on 6/21/16.
 */
public class Manufacturer {

    String company;
    String price;

    public Manufacturer(String company, String price) {
        this.company = company;
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
