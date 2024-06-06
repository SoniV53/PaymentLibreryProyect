package com.control.paymentlibrery.database.repository.ui;

import com.control.paymentlibrery.database.DataBaseRoom;
import com.control.paymentlibrery.database.dao.AmountMonthDao;
import com.control.paymentlibrery.database.entity.AmountMonthEntity;
import com.control.paymentlibrery.database.entity.list.AmountMonthList;
import com.control.paymentlibrery.database.repository.BaseRepository;
import com.control.paymentlibrery.database.repository.BaseResponse;
import com.control.paymentlibrery.database.repository.InterfaceCRUD;

import java.util.List;

public class AmountMonthRepository extends BaseRepository implements InterfaceCRUD {
    private AmountMonthDao dao = dataBaseRoom.amountMonthDao();

    public AmountMonthRepository(DataBaseRoom dataBaseRoom) {
        super(dataBaseRoom);
    }

    @Override
    public BaseResponse createOrUpdateRoom(String request) {
        try {
            BaseResponse getList = getListRoom();
            if (isSuccess(getList.getCode())){
                AmountMonthEntity requestEntity = gson.fromJson(request,AmountMonthEntity.class);
                AmountMonthEntity entityMonth = dao.findByIdMonthAndIdYear(requestEntity.idMonth,requestEntity.idYear);

                if (entityMonth == null){
                    dao.insertAll(requestEntity);
                }else {
                    requestEntity.id = entityMonth.id;
                    dao.updateAll(requestEntity);
                }
                AmountMonthEntity response = dao.findByIdMonthAndIdYear(requestEntity.idMonth,requestEntity.idYear);
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
            AmountMonthEntity requestEntity = gson.fromJson(request,AmountMonthEntity.class);
            AmountMonthEntity entity = dao.findById(requestEntity.id);
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
            List<AmountMonthEntity> list = dao.getAll();
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(new AmountMonthList(list))
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getListRoomYearAndMonth(int idMonth,int year) {
        try {
            AmountMonthEntity entityMonth = dao.findByIdMonthAndIdYear(idMonth,year);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(entityMonth)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }
}
