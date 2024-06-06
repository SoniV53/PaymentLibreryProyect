package com.control.paymentlibrery.database.repository;

import com.control.paymentlibrery.database.DataBaseRoom;
import com.google.gson.Gson;

public class BaseRepository {
    protected static String CODE200  = "200";
    protected static String CODE400  = "400";
    protected Gson gson;
    protected DataBaseRoom dataBaseRoom;

    public BaseRepository(DataBaseRoom dataBaseRoom) {
        this.dataBaseRoom = dataBaseRoom;
        gson = new Gson();
    }

    protected boolean isSuccess(String value){
        return value.equals(CODE200);
    }

}
