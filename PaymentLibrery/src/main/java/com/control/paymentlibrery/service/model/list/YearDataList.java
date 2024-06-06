package com.control.paymentlibrery.service.model.list;

import com.control.paymentlibrery.service.model.YearDataModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YearDataList {
    @SerializedName("yearDataList")
    private List<YearDataModel> dataList;

    public List<YearDataModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<YearDataModel> dataList) {
        this.dataList = dataList;
    }
}
