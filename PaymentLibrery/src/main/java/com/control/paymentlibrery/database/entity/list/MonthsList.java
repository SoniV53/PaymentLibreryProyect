package com.control.paymentlibrery.database.entity.list;

import com.control.paymentlibrery.database.entity.MonthEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MonthsList {
    @SerializedName("monthDataList")
    private List<MonthEntity> list;

    public MonthsList(List<MonthEntity> list) {
        this.list = list;
    }
}
