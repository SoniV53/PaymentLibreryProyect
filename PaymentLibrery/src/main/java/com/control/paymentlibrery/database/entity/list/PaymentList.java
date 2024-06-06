package com.control.paymentlibrery.database.entity.list;

import com.control.paymentlibrery.database.entity.PaymentEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentList {
    @SerializedName("paymentDataList")
    private List<PaymentEntity> list;

    public PaymentList(List<PaymentEntity> list) {
        this.list = list;
    }
}
