package com.control.paymentlibrery.service.model.list;

import com.control.paymentlibrery.service.model.PaymentDataMonthModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentDataMonthList {

    @SerializedName("paymentDataMonthList")
    private List<PaymentDataMonthModel> dataList;

    public PaymentDataMonthList(List<PaymentDataMonthModel> dataList) {
        this.dataList = dataList;
    }

    public List<PaymentDataMonthModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<PaymentDataMonthModel> dataList) {
        this.dataList = dataList;
    }
}
