package com.control.paymentlibrery.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PaymentEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "category")
    public String category;
    @ColumnInfo(name = "amount")
    public double amount;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "quote")
    public int quote;

    @ColumnInfo(name = "amountQuote")
    public double amountQuote;

    @ColumnInfo(name = "amountPaid")
    public double amountPaid;


}
