package com.control.paymentlibrery.service.model.list;

import com.control.paymentlibrery.service.model.MonthDataModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MonthDataList {

    @SerializedName("monthDataList")
    private List<MonthDataModel> dataList;

    public List<MonthDataModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<MonthDataModel> dataList) {
        this.dataList = dataList;
    }
}
