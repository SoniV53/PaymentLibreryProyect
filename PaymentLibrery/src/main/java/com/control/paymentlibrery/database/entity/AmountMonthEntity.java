package com.control.paymentlibrery.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AmountMonthEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "amount")
    public double amount;
    @ColumnInfo(name = "idYear")
    public int idYear;

    @ColumnInfo(name = "idMonth")
    public int idMonth;


}
