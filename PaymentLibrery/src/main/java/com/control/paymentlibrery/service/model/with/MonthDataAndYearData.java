package com.control.paymentlibrery.service.model.with;

import com.control.paymentlibrery.service.model.AmountMonthDataModel;
import com.control.paymentlibrery.service.model.MonthDataModel;
import com.control.paymentlibrery.service.model.YearDataModel;
import com.google.gson.annotations.SerializedName;

public class MonthDataAndYearData {
    @SerializedName("year")
    private YearDataModel year;
    @SerializedName("month")
    private MonthDataModel month;

    @SerializedName("estimate")
    private AmountMonthDataModel estimate;

    public MonthDataAndYearData() {
    }

    public MonthDataAndYearData(YearDataModel year, MonthDataModel month) {
        this.year = year;
        this.month = month;
    }

    public MonthDataAndYearData(AmountMonthDataModel estimate) {
        this.estimate = estimate;
    }

    public MonthDataAndYearData(YearDataModel year, MonthDataModel month, AmountMonthDataModel estimate) {
        this.year = year;
        this.month = month;
        this.estimate = estimate;
    }

    public AmountMonthDataModel getEstimate() {
        return estimate;
    }

    public void setEstimate(AmountMonthDataModel estimate) {
        this.estimate = estimate;
    }

    public YearDataModel getYear() {
        return year;
    }

    public void setYear(YearDataModel year) {
        this.year = year;
    }

    public MonthDataModel getMonth() {
        return month;
    }

    public void setMonth(MonthDataModel month) {
        this.month = month;
    }
}
