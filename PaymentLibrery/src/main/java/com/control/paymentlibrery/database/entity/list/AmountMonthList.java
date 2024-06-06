package com.control.paymentlibrery.database.entity.list;

import com.control.paymentlibrery.database.entity.AmountMonthEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AmountMonthList {

    @SerializedName("amountMonthList")
    private List<AmountMonthEntity> list;

    public AmountMonthList(List<AmountMonthEntity> list) {
        this.list = list;
    }
}
