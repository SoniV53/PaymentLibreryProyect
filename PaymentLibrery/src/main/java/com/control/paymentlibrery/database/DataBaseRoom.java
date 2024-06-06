package com.control.paymentlibrery.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.control.paymentlibrery.database.dao.AmountMonthDao;
import com.control.paymentlibrery.database.dao.MonthDao;
import com.control.paymentlibrery.database.dao.MonthPaymentDao;
import com.control.paymentlibrery.database.dao.PaymentDao;
import com.control.paymentlibrery.database.dao.SubPaymentDao;
import com.control.paymentlibrery.database.dao.YearDao;
import com.control.paymentlibrery.database.entity.AmountMonthEntity;
import com.control.paymentlibrery.database.entity.MonthEntity;
import com.control.paymentlibrery.database.entity.MonthPaymentEntity;
import com.control.paymentlibrery.database.entity.PaymentEntity;
import com.control.paymentlibrery.database.entity.SubPaymentEntity;
import com.control.paymentlibrery.database.entity.YearEntity;

@Database(entities = {YearEntity.class, MonthEntity.class, PaymentEntity.class, MonthPaymentEntity.class, SubPaymentEntity.class, AmountMonthEntity.class}, version = 5)
public abstract class DataBaseRoom extends RoomDatabase {
    public abstract YearDao yearDao();
    public abstract MonthDao monthDao();
    public abstract PaymentDao paymentDao();
    public abstract MonthPaymentDao detailsPaymentDao();
    public abstract SubPaymentDao subPaymentDao();
    public abstract AmountMonthDao amountMonthDao();
}
