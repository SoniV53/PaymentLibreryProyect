package com.control.paymentlibrery.service.model;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class PaymentDataModel {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("category")
    private String category;
    @SerializedName("amount")
    private double amount;
    @SerializedName("type")
    private String type;
    @SerializedName("quote")
    private int quote;
    @SerializedName("amountQuote")
    private double amountQuote;
    @ColumnInfo(name = "quoteStart")
    public int quoteStart;

    public PaymentDataModel() {
    }

    public PaymentDataModel(String title, String description, String category, double amount, String type, int quote, double amountQuote) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.type = type;
        this.quote = quote;
        this.amountQuote = amountQuote;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }

    public double getAmountQuote() {
        return amountQuote;
    }

    public void setAmountQuote(double amountQuote) {
        this.amountQuote = amountQuote;
    }

    public int getQuoteStart() {
        return quoteStart;
    }

    public void setQuoteStart(int quoteStart) {
        this.quoteStart = quoteStart;
    }
}
