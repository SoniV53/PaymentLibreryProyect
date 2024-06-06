package com.control.paymentlibrery.database.repository.ui;


import com.control.paymentlibrery.database.DataBaseRoom;
import com.control.paymentlibrery.database.dao.SubPaymentDao;
import com.control.paymentlibrery.database.entity.SubPaymentEntity;
import com.control.paymentlibrery.database.entity.list.MonthSubPaymentList;
import com.control.paymentlibrery.database.repository.BaseRepository;
import com.control.paymentlibrery.database.repository.BaseResponse;
import com.control.paymentlibrery.database.repository.InterfaceCRUD;

import java.util.List;

public class SubPaymentRepository extends BaseRepository implements InterfaceCRUD {
    private SubPaymentDao dao = dataBaseRoom.subPaymentDao();
    public SubPaymentRepository(DataBaseRoom dataBaseRoom) {
        super(dataBaseRoom);
    }

    @Override
    public BaseResponse createOrUpdateRoom(String request) {
        try {
            SubPaymentEntity requestEntity = gson.fromJson(request,SubPaymentEntity.class);
            SubPaymentEntity entity = dao.findByTitleAndIdPay(requestEntity.title,requestEntity.idDetailsPay);

            if (entity == null){
                dao.insertAll(requestEntity);
            }else {
                requestEntity.id = entity.id;
                dao.updateAll(requestEntity);
            }

            SubPaymentEntity response = dao.findByTitleAndIdPay(requestEntity.title,requestEntity.idDetailsPay);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(response)
            );

        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteRoom(String request) {
        try {
            SubPaymentEntity requestEntity = gson.fromJson(request,SubPaymentEntity.class);
            SubPaymentEntity entity = dao.findByTitle(requestEntity.title);
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

    public BaseResponse deleteRoomId(int id) {
        try {
            SubPaymentEntity entity = dao.findById(id);
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
            List<SubPaymentEntity> list = dao.getAll();
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(list)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getListRoomSubPayment(int idMonth) {
        try {
            List<SubPaymentEntity> list = dao.findByIdMonth(idMonth);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(new MonthSubPaymentList(list))
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }
}
