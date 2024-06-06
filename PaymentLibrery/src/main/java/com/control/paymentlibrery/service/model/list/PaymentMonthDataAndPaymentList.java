package com.control.paymentlibrery.service.model.list;

import com.control.paymentlibrery.service.model.with.PaymentMonthDataAndPaymentData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentMonthDataAndPaymentList {

    @SerializedName("paymentMonthDataAndPaymentList")
    private List<PaymentMonthDataAndPaymentData> dataList;

    public PaymentMonthDataAndPaymentList(List<PaymentMonthDataAndPaymentData> dataList) {
        this.dataList = dataList;
    }

    public List<PaymentMonthDataAndPaymentData> getDataList() {
        return dataList;
    }

    public void setDataList(List<PaymentMonthDataAndPaymentData> dataList) {
        this.dataList = dataList;
    }
}
