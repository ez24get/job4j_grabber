package ru.job4j.ood.lsp.modelfood;

import java.util.Calendar;

public class Food {

    private String name;

    private Calendar expiryDate;

    private double price;

    private double discount;

    public Food(String name, Calendar expiryDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}