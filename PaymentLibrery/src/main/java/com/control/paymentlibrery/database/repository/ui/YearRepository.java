package com.control.paymentlibrery.database.repository.ui;

import com.control.paymentlibrery.database.DataBaseRoom;
import com.control.paymentlibrery.database.dao.YearDao;
import com.control.paymentlibrery.database.entity.YearEntity;
import com.control.paymentlibrery.database.entity.list.YearList;
import com.control.paymentlibrery.database.repository.BaseRepository;
import com.control.paymentlibrery.database.repository.BaseResponse;
import com.control.paymentlibrery.database.repository.InterfaceCRUD;

import java.util.List;

public class YearRepository extends BaseRepository implements InterfaceCRUD {
    private YearDao dao = dataBaseRoom.yearDao();
    public YearRepository(DataBaseRoom dataBaseRoom) {
        super(dataBaseRoom);
    }

    @Override
    public BaseResponse createOrUpdateRoom(String request) {
        try {
            YearEntity year = gson.fromJson(request,YearEntity.class);
            YearEntity yearDB = dao.findByName(year.name);
            if (yearDB == null){
                dao.insertAll(year);
            }else {
                year.id = yearDB.id;
                dao.updateAll(year);
            }
            YearEntity responseYear = dao.findByName(year.name);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(responseYear)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteRoom(String request) {
        try {
            YearEntity year = gson.fromJson(request,YearEntity.class);
            YearEntity yearDB = dao.findByName(year.name);
            if (yearDB == null){
                dao.delete(year);
                return new BaseResponse(
                        "Success",
                        CODE200,
                        "Se elimino Correctamente"
                );
            }else
                return new BaseResponse("NO HAY DATOS");

        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse getListRoom() {
        try {
            List<YearEntity> yearEntities = dao.getAll();
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(new YearList(yearEntities))
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getListForName(String name) {
        try {
            YearEntity yearEntities = dao.findByName(name);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(yearEntities)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }
}
