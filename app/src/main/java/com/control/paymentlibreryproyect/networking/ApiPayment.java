package com.control.paymentlibreryproyect.networking;

import com.control.paymentlibrery.service.model.YearDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiPayment {

    @GET("year/list")
    Call<List<YearDataModel>> getAllYears();

    @POST("year/save")
    Call<YearDataModel> saveYear(@Body YearDataModel request);
}
