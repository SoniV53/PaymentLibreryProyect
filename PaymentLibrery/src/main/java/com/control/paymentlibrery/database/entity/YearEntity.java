package com.control.paymentlibrery.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class YearEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    public YearEntity() {
    }

    public YearEntity(String name) {
        this.name = name;
    }
}
