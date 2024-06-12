package com.control.paymentlibrery.service.controller;

import android.content.Context;

import androidx.room.Room;

import com.control.paymentlibrery.database.DataBaseRoom;
import com.google.gson.Gson;

public class BaseController {
    protected DataBaseRoom dataBaseRoom;
    protected Gson gson;

    public BaseController(Context context) {
        gson = new Gson();
        if (dataBaseRoom == null){
            dataBaseRoom = Room.databaseBuilder(context,
                    DataBaseRoom.class, "controlPayment").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
    }

    protected String printJson(Object obj){
       return gson.toJson(obj);
    }

    protected <T>T responseJson(String decrypted,Class<T> clazz){
        return gson.fromJson(decrypted, clazz);
    }

    protected int parseInger(String value){
        return value != null && !value.isEmpty() ? Integer.parseInt(value) : 0;
    }
}
