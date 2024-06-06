package com.control.paymentlibrery.service.model.list;

import com.control.paymentlibrery.service.model.SubPaymentDataMonthModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubPaymentDataMonthList {

    @SerializedName("subPaymentDataMonthList")
    private List<SubPaymentDataMonthModel> dataList;

    public List<SubPaymentDataMonthModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<SubPaymentDataMonthModel> dataList) {
        this.dataList = dataList;
    }
}
