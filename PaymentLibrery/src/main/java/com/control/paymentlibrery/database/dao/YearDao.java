package com.control.paymentlibrery.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.control.paymentlibrery.database.entity.YearEntity;

import java.util.List;
@Dao
public interface YearDao {
    @Query("SELECT * FROM YearEntity")
    List<YearEntity> getAll();

    @Query("SELECT * FROM yearentity WHERE name LIKE :name LIMIT 1")
    YearEntity findByName(String name);

    @Insert
    void insertAll(YearEntity object);

    @Update
    void updateAll(YearEntity object);

    @Delete
    void delete(YearEntity object);
}
