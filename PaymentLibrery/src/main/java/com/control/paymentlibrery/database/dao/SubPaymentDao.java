package com.control.paymentlibrery.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.control.paymentlibrery.database.entity.SubPaymentEntity;

import java.util.List;

@Dao
public interface SubPaymentDao {
    @Query("SELECT * FROM subpaymententity")
    List<SubPaymentEntity> getAll();

    @Query("SELECT * FROM subpaymententity WHERE title LIKE :name LIMIT 1")
    SubPaymentEntity findByName(String name);

    @Query("SELECT * FROM subpaymententity WHERE title LIKE :title LIMIT 1")
    SubPaymentEntity findByTitle(String title);

    @Query("SELECT * FROM subpaymententity WHERE id LIKE :id LIMIT 1")
    SubPaymentEntity findById(int id);

    @Query("SELECT * FROM subpaymententity WHERE title LIKE :title  AND idMonthPay LIKE :idMonthPay LIMIT 1")
    SubPaymentEntity findByTitleAndIdPay(String title,int idMonthPay);

    @Query("SELECT * FROM subpaymententity WHERE idMonthPay LIKE :idMonthPay")
    List<SubPaymentEntity> findByIdMonth(int idMonthPay);

    @Insert
    void insertAll(SubPaymentEntity object);

    @Update
    void updateAll(SubPaymentEntity object);

    @Delete
    void delete(SubPaymentEntity object);
}
