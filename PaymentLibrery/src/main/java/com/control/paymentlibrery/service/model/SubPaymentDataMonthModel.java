package com.control.paymentlibrery.service.model;

import com.google.gson.annotations.SerializedName;

public class SubPaymentDataMonthModel {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("amount")
    private double amount;
    @SerializedName("idDetailsPay")
    private int idDetailsPay;

    public SubPaymentDataMonthModel(int id, String title, double amount, int idDetailsPay) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.idDetailsPay = idDetailsPay;
    }

    public SubPaymentDataMonthModel(String title, double amount) {
        this.title = title;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getIdDetailsPay() {
        return idDetailsPay;
    }

    public void setIdDetailsPay(int idDetailsPay) {
        this.idDetailsPay = idDetailsPay;
    }
}
