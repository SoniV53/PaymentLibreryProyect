package com.control.paymentlibrery.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.control.paymentlibrery.database.entity.PaymentEntity;

import java.util.List;

@Dao
public interface PaymentDao {
    @Query("SELECT * FROM paymententity")
    List<PaymentEntity> getAll();

    @Query("SELECT * FROM paymententity WHERE category LIKE :category")
    List<PaymentEntity> getAllFindByCategory(String category);

    @Query("SELECT * FROM paymententity WHERE title LIKE :name LIMIT 1")
    PaymentEntity findByTitle(String name);

    @Query("SELECT * FROM paymententity WHERE title LIKE :title")
    List<PaymentEntity> findByTitleList(String title);


    @Query("SELECT * FROM paymententity WHERE title LIKE :title AND category LIKE :category LIMIT 1")
    PaymentEntity findByTitleAndCategory(String title,String category);

    @Query("SELECT * FROM paymententity WHERE id LIKE :id AND category LIKE :category LIMIT 1")
    PaymentEntity findByIdAndCategory(int id,String category);


    @Query("SELECT * FROM paymententity WHERE title LIKE :title AND category LIKE :category")
    List<PaymentEntity> findByTitleAndCategoryList(String title,String category);

    @Query("SELECT * FROM paymententity WHERE id LIKE :id LIMIT 1")
    PaymentEntity findById(int id);


    @Insert
    void insertAll(PaymentEntity object);

    @Update
    void updateAll(PaymentEntity object);

    @Delete
    void delete(PaymentEntity object);
}
