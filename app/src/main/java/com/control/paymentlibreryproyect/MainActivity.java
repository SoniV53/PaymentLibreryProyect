package com.control.paymentlibreryproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.control.paymentlibrery.database.repository.BaseResponse;
import com.control.paymentlibrery.service.CreateDataBaseRoom;
import com.control.paymentlibrery.service.controller.ui.PaymentsController;
import com.control.paymentlibrery.service.controller.ui.YearAndMonthController;
import com.control.paymentlibrery.service.model.AmountMonthDataModel;
import com.control.paymentlibrery.service.model.PaymentDataModel;
import com.control.paymentlibrery.service.model.PaymentDataMonthModel;
import com.control.paymentlibrery.service.model.YearDataModel;
import com.control.paymentlibrery.service.model.with.MonthDataAndYearData;
import com.control.paymentlibreryproyect.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RetrofitConect repository = RetrofitConect.getInstance(this);


        binding.button.setOnClickListener(view -> {
            repository.getYearAll().observe(this,yearDataModel -> {
                System.out.println("RESPONSE DATA: " + new Gson().toJson(yearDataModel));
            });
        });

        binding.button2.setOnClickListener(view -> {
            repository.saveYear(new YearDataModel("2024")).observe(this, yearDataModel -> {
                System.out.println("RESPONSE DATA: " + new Gson().toJson(yearDataModel));
            });
        });
    }

    private void dataDummy(){

        new CreateDataBaseRoom(this);
        PaymentsController pas = new PaymentsController(this);
        YearAndMonthController ye = new YearAndMonthController(this);
        MonthDataAndYearData yearData = ye.getYearAndMonth();
        Gson gson = new Gson();

        pas.createEstimate(new AmountMonthDataModel("1000",1,7));

        System.out.println("YEART DATA: " + gson.toJson(yearData));
        System.out.println("ESTIMATE DATA: " + gson.toJson(pas.getEstimate(7,1)));
        int st = 2;

        System.out.println("GASTOS DATA: " + gson.toJson(pas.getPaymentMonthsResponse(st)));

        binding.button.setOnClickListener(view -> {
            BaseResponse response = pas.updatePayment(
                    new PaymentDataModel(1,"sat","","M",200,"1",1,0,1),
                    new PaymentDataMonthModel(3,3,"false","1",1,st));

            System.out.println("RESONSE DATA: " + gson.toJson(response));
            Toast.makeText(this, gson.toJson(response), Toast.LENGTH_SHORT).show();
        });

        binding.button2.setOnClickListener(view -> {
            BaseResponse response = pas.createNewPayment(
                    new PaymentDataModel("sat","","U",200,"1",1,0,1),
                    new PaymentDataMonthModel(200,"false","",st,new ArrayList<>()));

            System.out.println("RESONSE DATA: " + gson.toJson(response));
            Toast.makeText(this, gson.toJson(response), Toast.LENGTH_SHORT).show();
        });
    }
}