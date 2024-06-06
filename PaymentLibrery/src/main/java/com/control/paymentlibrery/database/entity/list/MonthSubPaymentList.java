package com.control.paymentlibrery.database.entity.list;

import com.control.paymentlibrery.database.entity.SubPaymentEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MonthSubPaymentList {
    @SerializedName("subPaymentDataMonthList")
    private List<SubPaymentEntity> list;

    public MonthSubPaymentList(List<SubPaymentEntity> list) {
        this.list = list;
    }
}
