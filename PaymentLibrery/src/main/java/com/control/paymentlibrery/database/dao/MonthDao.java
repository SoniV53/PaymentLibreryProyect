package com.control.paymentlibrery.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.control.paymentlibrery.database.entity.MonthEntity;

import java.util.List;

@Dao
public interface MonthDao {
    @Query("SELECT * FROM monthentity")
    List<MonthEntity> getAll();

    @Query("SELECT * FROM monthentity WHERE name LIKE :name LIMIT 1")
    MonthEntity findByName(String name);

    @Insert
    void insertAll(MonthEntity object);

    @Update
    void updateAll(MonthEntity object);

    @Delete
    void delete(MonthEntity object);
}
