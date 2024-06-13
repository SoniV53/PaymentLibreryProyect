package com.control.paymentlibreryproyect;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.control.paymentlibrery.service.model.YearDataModel;
import com.control.paymentlibreryproyect.networking.ApiPayment;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConect {
    private static String URL = "http://10.0.2.2:8080/api/";
    private final ApiPayment apiPayment;
    private static RetrofitConect retrofitConect;
    private static Retrofit retrofit = null;

    public static RetrofitConect getInstance(Context context) {
        if (retrofitConect == null) {
            retrofitConect = new RetrofitConect(context);
        }
        return retrofitConect;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public RetrofitConect(Context context) {
        apiPayment = getClient().create(ApiPayment.class);
    }

    public MutableLiveData<List<YearDataModel>> getYearAll() {
        final MutableLiveData<List<YearDataModel>> data = new MutableLiveData<>();
        apiPayment.getAllYears().enqueue(new Callback<List<YearDataModel>>() {
            @Override
            public void onResponse(Call<List<YearDataModel>> call, Response<List<YearDataModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<YearDataModel>> call, Throwable t) {
                data.setValue(new ArrayList<>());
            }
        });
        return data;
    }

    public MutableLiveData<YearDataModel> saveYear(YearDataModel request) {
        final MutableLiveData<YearDataModel> data = new MutableLiveData<>();
        apiPayment.saveYear(request).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<YearDataModel> call, Response<YearDataModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<YearDataModel> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
