package com.control.paymentlibrery.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.control.paymentlibrery.database.entity.MonthPaymentEntity;

import java.util.List;

@Dao
public interface MonthPaymentDao {
    @Query("SELECT * FROM monthpaymententity")
    List<MonthPaymentEntity> getAll();
    @Query("SELECT * FROM monthpaymententity WHERE id LIKE :id LIMIT 1")
    MonthPaymentEntity findById(int id);

    @Query("SELECT * FROM monthpaymententity WHERE idAmountMonth LIKE :idAmountMonth AND idPayment LIKE :idPayment LIMIT 1")
    MonthPaymentEntity findByIdMonthAndIdPayment(int idAmountMonth,int idPayment);

    @Query("SELECT * FROM monthpaymententity WHERE idPayment LIKE :id")
    List<MonthPaymentEntity> findByIdPayment(int id);

    @Query("SELECT * FROM monthpaymententity WHERE idAmountMonth LIKE :idAmountMonth")
    List<MonthPaymentEntity> findByIdMonth(int idAmountMonth);

    @Query("SELECT * FROM monthpaymententity WHERE idAmountMonth LIKE :idAmountMonth")
    MonthPaymentEntity findByIdAmountMonth(int idAmountMonth);
    @Insert
    void insertAll(MonthPaymentEntity object);

    @Update
    void updateAll(MonthPaymentEntity object);

    @Delete
    void delete(MonthPaymentEntity object);
}
