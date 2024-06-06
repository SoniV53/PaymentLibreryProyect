package com.control.paymentlibrery.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MonthPaymentEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "amountPaid")
    public double amountPaid;
    @ColumnInfo(name = "status")
    public String status;
    @ColumnInfo(name = "quotesPay")
    public String quotesPay;
    @ColumnInfo(name = "idAmountMonth")
    public int idAmountMonth;
    @ColumnInfo(name = "idPayment")
    public int idPayment;
}
