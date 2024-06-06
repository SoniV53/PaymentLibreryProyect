package com.control.paymentlibrery.service.model;

import com.google.gson.annotations.SerializedName;

public class MonthDataModel {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public MonthDataModel(String name) {
        this.id = 0;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
