package com.control.paymentlibrery.database.entity.list;

import com.control.paymentlibrery.database.entity.MonthPaymentEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MonthPaymentList {
    @SerializedName("paymentDataMonthList")
    private List<MonthPaymentEntity> list;

    public MonthPaymentList(List<MonthPaymentEntity> list) {
        this.list = list;
    }
}
