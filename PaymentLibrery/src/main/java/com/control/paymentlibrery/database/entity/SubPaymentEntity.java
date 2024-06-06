package com.control.paymentlibrery.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SubPaymentEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "amount")
    public double amount;
    @ColumnInfo(name = "idMonthPay")
    public int idDetailsPay;


}
