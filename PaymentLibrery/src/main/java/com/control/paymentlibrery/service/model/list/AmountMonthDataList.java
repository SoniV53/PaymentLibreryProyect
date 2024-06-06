package com.control.paymentlibrery.service.model.list;

import com.control.paymentlibrery.service.model.AmountMonthDataModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AmountMonthDataList {

    @SerializedName("amountMonthList")
    private List<AmountMonthDataModel> dataList;

    public List<AmountMonthDataModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<AmountMonthDataModel> dataList) {
        this.dataList = dataList;
    }
}
