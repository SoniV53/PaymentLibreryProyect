package com.control.paymentlibrery.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentDataMonthModel {
    @SerializedName("id")
    private int id;
    @SerializedName("amountPaid")
    private double amountPaid;
    @SerializedName("status")
    private String status;
    @SerializedName("quotesPay")
    private String quotesPay;
    @SerializedName("idAmountMonth")
    private int idAmountMonth;
    @SerializedName("idPayment")
    private int idPayment;
    @SerializedName("listSubPayment")
    private List<SubPaymentDataMonthModel> subPaymentDataMonthModels;

    public PaymentDataMonthModel(int idAmountMonth, int idPayment) {
        this.idAmountMonth = idAmountMonth;
        this.idPayment = idPayment;
    }

    public PaymentDataMonthModel(double amountPaid, String status, String quotesPay,List<SubPaymentDataMonthModel> subPaymentDataMonthModels) {
        this.amountPaid = amountPaid;
        this.status = status;
        this.quotesPay = quotesPay;
        this.subPaymentDataMonthModels = subPaymentDataMonthModels;
    }

    public PaymentDataMonthModel(double amountPaid, String status, String quotesPay, int idAmountMonth, List<SubPaymentDataMonthModel> subPaymentDataMonthModels) {
        this.amountPaid = amountPaid;
        this.status = status;
        this.quotesPay = quotesPay;
        this.idAmountMonth = idAmountMonth;
        this.subPaymentDataMonthModels = subPaymentDataMonthModels;
    }

    public List<SubPaymentDataMonthModel> getSubPaymentDataMonthModels() {
        return subPaymentDataMonthModels;
    }

    public void setSubPaymentDataMonthModels(List<SubPaymentDataMonthModel> subPaymentDataMonthModels) {
        this.subPaymentDataMonthModels = subPaymentDataMonthModels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStatus() {
        return status != null ? status : "false";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuotesPay() {
        return quotesPay;
    }

    public void setQuotesPay(String quotesPay) {
        this.quotesPay = quotesPay;
    }

    public int getIdAmountMonth() {
        return idAmountMonth;
    }

    public void setIdAmountMonth(int idAmountMonth) {
        this.idAmountMonth = idAmountMonth;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }
}
