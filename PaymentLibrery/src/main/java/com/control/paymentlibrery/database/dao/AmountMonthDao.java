package com.control.paymentlibrery.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.control.paymentlibrery.database.entity.AmountMonthEntity;

import java.util.List;
@Dao
public interface AmountMonthDao {
    @Query("SELECT * FROM amountmonthentity")
    List<AmountMonthEntity> getAll();

    @Query("SELECT * FROM amountmonthentity WHERE id LIKE :id LIMIT 1")
    AmountMonthEntity findById(int id);

    @Query("SELECT * FROM amountmonthentity WHERE idMonth LIKE :idMonth AND idYear LIKE :idYear LIMIT 1")
    AmountMonthEntity findByIdMonthAndIdYear(int idMonth, int idYear);

    @Insert
    void insertAll(AmountMonthEntity object);

    @Update
    void updateAll(AmountMonthEntity object);

    @Delete
    void delete(AmountMonthEntity object);
}
