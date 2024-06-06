package com.control.paymentlibrery.service.model.list;

import com.control.paymentlibrery.service.model.PaymentDataModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentDataList {

    @SerializedName("paymentDataList")
    private List<PaymentDataModel> dataList;

    public List<PaymentDataModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<PaymentDataModel> dataList) {
        this.dataList = dataList;
    }
}
