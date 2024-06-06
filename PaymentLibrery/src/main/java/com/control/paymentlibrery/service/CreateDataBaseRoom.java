package com.control.paymentlibrery.service;

import android.content.Context;

import com.control.paymentlibrery.service.controller.ui.YearAndMonthController;

public class CreateDataBaseRoom {

    public CreateDataBaseRoom(Context context) {
        new YearAndMonthController(context).createYearAndMonths();
    }
}
