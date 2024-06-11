package com.control.paymentlibrery.database.repository.ui;


import com.control.paymentlibrery.database.DataBaseRoom;
import com.control.paymentlibrery.database.dao.MonthPaymentDao;
import com.control.paymentlibrery.database.entity.MonthPaymentEntity;
import com.control.paymentlibrery.database.entity.list.MonthPaymentList;
import com.control.paymentlibrery.database.repository.BaseRepository;
import com.control.paymentlibrery.database.repository.BaseResponse;
import com.control.paymentlibrery.database.repository.InterfaceCRUD;

import java.util.List;

public class PaymentMonthRepository extends BaseRepository implements InterfaceCRUD {
    private MonthPaymentDao dao = dataBaseRoom.detailsPaymentDao();
    public PaymentMonthRepository(DataBaseRoom dataBaseRoom) {
        super(dataBaseRoom);
    }

    @Override
    public BaseResponse createOrUpdateRoom(String request) {
        try {
            BaseResponse getList = getListRoom();
            if (isSuccess(getList.getCode())){
                MonthPaymentEntity requestEntity = gson.fromJson(request,MonthPaymentEntity.class);
                MonthPaymentEntity entity = dao.findById(requestEntity.id);

                if (entity == null){
                    dao.insertAll(requestEntity);
                }else {
                    requestEntity.id = entity.id;
                    dao.updateAll(requestEntity);
                }
                MonthPaymentEntity response = dao.findByIdMonthAndIdPayment(requestEntity.idAmountMonth,requestEntity.idPayment);
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

    public BaseResponse createOrUpdateRoomPayment(String request) {
        try {
            BaseResponse getList = getListRoom();
            if (isSuccess(getList.getCode())){
                MonthPaymentEntity requestEntity = gson.fromJson(request,MonthPaymentEntity.class);
                MonthPaymentEntity entityMonth = dao.findByIdMonthAndIdPayment(requestEntity.idAmountMonth,requestEntity.idPayment);

                if (entityMonth == null){
                    dao.insertAll(requestEntity);
                }else {
                    requestEntity.id = entityMonth.id;
                    dao.updateAll(requestEntity);
                }
                MonthPaymentEntity response = dao.findByIdMonthAndIdPayment(requestEntity.idAmountMonth,requestEntity.idPayment);
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

    public BaseResponse updateRoomStatus(int id,String status) {
        try {
            MonthPaymentEntity entityMonth = dao.findById(id);
            entityMonth.status = status;
            dao.updateAll(entityMonth);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(entityMonth)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteRoom(String request) {
        try {
            MonthPaymentEntity requestEntity = gson.fromJson(request,MonthPaymentEntity.class);
            MonthPaymentEntity entity = dao.findById(requestEntity.id);
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

    public BaseResponse deleteRoom(int id) {
        try {
            MonthPaymentEntity entity = dao.findById(id);
            if (entity != null){
                dao.delete(entity);
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
            List<MonthPaymentEntity> list = dao.getAll();
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(list)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getListRoomSelectMonth(int idAmountMonth) {
        try {
            List<MonthPaymentEntity> list = dao.findByIdMonth(idAmountMonth);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(new MonthPaymentList(list))
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getRoomSelectMonth(int idAmountMonth) {
        try {
            MonthPaymentEntity list = dao.findByIdAmountMonth(idAmountMonth);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(list)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getRoomSelectMonthFindId(int id) {
        try {
            MonthPaymentEntity list = dao.findById(id);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(list)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }


    public BaseResponse getListRoomMovementPayment(int idPayment) {
        try {
            List<MonthPaymentEntity> list = dao.findByIdPayment(idPayment);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(new MonthPaymentList(list))
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getListRoomYearAndMonth(int idAmountMonth,int idPayment) {
        try {
            MonthPaymentEntity data = dao.findByIdMonthAndIdPayment(idAmountMonth,idPayment);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(data)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }
}
