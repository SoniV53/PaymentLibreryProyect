package com.control.paymentlibrery.database.entity.list;

import com.control.paymentlibrery.database.entity.YearEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YearList {
    @SerializedName("yearDataList")
    private List<YearEntity> list;

    public YearList(List<YearEntity> list) {
        this.list = list;
    }
}
