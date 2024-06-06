package com.control.paymentlibrery.service.model;


import com.google.gson.annotations.SerializedName;

public class AmountMonthDataModel {
    @SerializedName("id")
    private int id;
    @SerializedName("amount")
    private String amount;

    @SerializedName("idYear")
    private String idYear;

    @SerializedName("idMonth")
    private String idMonth;

    public AmountMonthDataModel() {
    }

    public AmountMonthDataModel(String amount, int idYear, int idMonth) {
        this.id = 0;
        this.amount = amount;
        this.idYear = String.valueOf(idYear);
        this.idMonth = String.valueOf(idMonth);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount != null ? amount : "0.0";
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIdYear() {
        return idYear;
    }

    public void setIdYear(String idYear) {
        this.idYear = idYear;
    }

    public String getIdMonth() {
        return idMonth;
    }

    public void setIdMonth(String idMonth) {
        this.idMonth = idMonth;
    }
}