package com.control.paymentlibrery.service.controller.ui;

import android.content.Context;

import com.control.paymentlibrery.database.repository.BaseResponse;
import com.control.paymentlibrery.database.repository.ui.AmountMonthRepository;
import com.control.paymentlibrery.database.repository.ui.MonthRepository;
import com.control.paymentlibrery.database.repository.ui.YearRepository;
import com.control.paymentlibrery.service.controller.BaseController;
import com.control.paymentlibrery.service.model.AmountMonthDataModel;
import com.control.paymentlibrery.service.model.MonthDataModel;
import com.control.paymentlibrery.service.model.YearDataModel;
import com.control.paymentlibrery.service.model.list.MonthDataList;
import com.control.paymentlibrery.service.model.list.YearDataList;
import com.control.paymentlibrery.service.model.with.MonthDataAndYearData;

import java.util.Calendar;

public class YearAndMonthController extends BaseController {

    public YearAndMonthController(Context context) {
        super(context);
    }

    public void createYearAndMonths(){
        YearRepository repository = new YearRepository(dataBaseRoom);
        Calendar calendar = Calendar.getInstance();
        repository.createOrUpdateRoom(printJson(new YearDataModel(String.valueOf(calendar.get(Calendar.YEAR)))));
        try {
            MonthRepository repositoryMonths = new MonthRepository(dataBaseRoom);
            BaseResponse response = repositoryMonths.getListRoom();
            MonthDataList listMonth = responseJson(response.getResponse(),MonthDataList.class);
            if (listMonth == null || (listMonth.getDataList().isEmpty())){
                repositoryMonths.createOrUpdateRoom(printJson(new MonthDataModel("enero")));
                repositoryMonths.createOrUpdateRoom(printJson(new MonthDataModel("febrero")));
                repositoryMonths.createOrUpdateRoom(printJson(new MonthDataModel("marzo")));
                repositoryMonths.createOrUpdateRoom(printJson(new MonthDataModel("abril")));
                repositoryMonths.createOrUpdateRoom(printJson(new MonthDataModel("mayo")));
                repositoryMonths.createOrUpdateRoom(printJson(new MonthDataModel("junio")));
                repositoryMonths.createOrUpdateRoom(printJson(new MonthDataModel("julio")));
                repositoryMonths.createOrUpdateRoom(printJson(new MonthDataModel("agosto")));
                repositoryMonths.createOrUpdateRoom(printJson(new MonthDataModel("septiembre")));
                repositoryMonths.createOrUpdateRoom(printJson(new MonthDataModel("octubre")));
                repositoryMonths.createOrUpdateRoom(printJson(new MonthDataModel("noviembre")));
                repositoryMonths.createOrUpdateRoom(printJson(new MonthDataModel("diciembre")));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public MonthDataAndYearData getYearAndMonth() {
        YearRepository repository = new YearRepository(dataBaseRoom);
        MonthRepository repositoryMonths = new MonthRepository(dataBaseRoom);
        AmountMonthRepository repositoryAmount = new AmountMonthRepository(dataBaseRoom);
        Calendar calendar = Calendar.getInstance();

        BaseResponse response = repository.getListForName(String.valueOf(calendar.get(Calendar.YEAR)));
        BaseResponse responseMonth = repositoryMonths.getListMonthForName(calendar.get(Calendar.MONTH));

        if ((response != null) && (response.getResponse() != null) && (responseMonth != null) && responseMonth.getResponse() != null){
            YearDataModel year = responseJson(response.getResponse(),YearDataModel.class);
            MonthDataModel month = responseJson(responseMonth.getResponse(),MonthDataModel.class);

            BaseResponse responseEstimate =  repositoryAmount.getListRoomYearAndMonth(month.getId(),year.getId());
            if (responseEstimate.getResponse() == null || (responseEstimate.getResponse() != null && responseEstimate.getResponse().isEmpty()) ||
                    responseEstimate.getResponse().equals("null"))
                responseEstimate =  repositoryAmount.createOrUpdateRoom(printJson(new AmountMonthDataModel("0",year.getId(),month.getId())));

            AmountMonthDataModel amount = responseJson(responseEstimate.getResponse(),AmountMonthDataModel.class);

            return new MonthDataAndYearData(year,month,amount);
        }

        return new MonthDataAndYearData();
    }

    public MonthDataList getMonths() {
        MonthRepository repositoryMonths = new MonthRepository(dataBaseRoom);
        MonthDataList monthList = responseJson(repositoryMonths.getListRoom().getResponse(),MonthDataList.class);
        return monthList;
    }

    public YearDataList getYears() {
        YearRepository repository = new YearRepository(dataBaseRoom);
        YearDataList list = responseJson(repository.getListRoom().getResponse(),YearDataList.class);
        return list;
    }
}
