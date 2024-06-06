package com.control.paymentlibrery.database.repository.ui;

import com.control.paymentlibrery.database.DataBaseRoom;
import com.control.paymentlibrery.database.dao.MonthDao;
import com.control.paymentlibrery.database.entity.MonthEntity;
import com.control.paymentlibrery.database.entity.list.MonthsList;
import com.control.paymentlibrery.database.repository.BaseRepository;
import com.control.paymentlibrery.database.repository.BaseResponse;
import com.control.paymentlibrery.database.repository.InterfaceCRUD;

import java.util.List;

public class MonthRepository extends BaseRepository implements InterfaceCRUD {
    private MonthDao dao = dataBaseRoom.monthDao();
    public MonthRepository(DataBaseRoom dataBaseRoom) {
        super(dataBaseRoom);
    }

    @Override
    public BaseResponse createOrUpdateRoom(String request) {
        try {
            BaseResponse getList = getListRoom();
            if (isSuccess(getList.getCode())){
                MonthEntity requestEntity = gson.fromJson(request,MonthEntity.class);
                MonthEntity entity = dao.findByName(requestEntity.name);
                if (entity == null){
                    dao.insertAll(requestEntity);
                }else {
                    requestEntity.id = entity.id;
                    dao.updateAll(requestEntity);
                }
                MonthEntity response = dao.findByName(requestEntity.name);
                return new BaseResponse(
                        "Success",
                        CODE200,
                        gson.toJson(response)
                );

            }else {
                return new BaseResponse("NO HAY DATOS");
            }


        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteRoom(String request) {
        try {
            MonthEntity requestEntity = gson.fromJson(request,MonthEntity.class);
            MonthEntity entity = dao.findByName(requestEntity.name);
            if (entity == null){
                dao.delete(requestEntity);
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
            List<MonthEntity> list = dao.getAll();
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(new MonthsList(list))
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getListMonthForName(int number) {
        try {
            List<MonthEntity> list = dao.getAll();
            MonthEntity entity = list.get(number);

            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(entity)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }
}
