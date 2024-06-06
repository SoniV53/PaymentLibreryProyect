package com.control.paymentlibrery.service.model.with;

import com.control.paymentlibrery.service.model.PaymentDataModel;
import com.control.paymentlibrery.service.model.PaymentDataMonthModel;
import com.google.gson.annotations.SerializedName;

public class PaymentMonthDataAndPaymentData {
    @SerializedName("payment")
    private PaymentDataModel payment;
    @SerializedName("paymentMonth")
    private PaymentDataMonthModel paymentMonth;

    public PaymentMonthDataAndPaymentData(PaymentDataModel payment, PaymentDataMonthModel paymentMonth) {
        this.payment = payment;
        this.paymentMonth = paymentMonth;
    }

    public PaymentDataModel getPayment() {
        return payment != null ? payment : new PaymentDataModel();
    }

    public void setPayment(PaymentDataModel payment) {
        this.payment = payment;
    }

    public PaymentDataMonthModel getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(PaymentDataMonthModel paymentMonth) {
        this.paymentMonth = paymentMonth;
    }
}
