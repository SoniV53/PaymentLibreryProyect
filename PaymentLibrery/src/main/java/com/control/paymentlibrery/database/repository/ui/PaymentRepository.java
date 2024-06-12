package com.control.paymentlibrery.database.repository.ui;

import com.control.paymentlibrery.database.DataBaseRoom;
import com.control.paymentlibrery.database.dao.PaymentDao;
import com.control.paymentlibrery.database.entity.PaymentEntity;
import com.control.paymentlibrery.database.entity.list.PaymentList;
import com.control.paymentlibrery.database.repository.BaseRepository;
import com.control.paymentlibrery.database.repository.BaseResponse;
import com.control.paymentlibrery.database.repository.InterfaceCRUD;

import java.util.List;

public class PaymentRepository extends BaseRepository implements InterfaceCRUD {
    private PaymentDao dao = dataBaseRoom.paymentDao();
    public PaymentRepository(DataBaseRoom dataBaseRoom) {
        super(dataBaseRoom);
    }

    @Override
    public BaseResponse createOrUpdateRoom(String request) {
        try {
            PaymentEntity requestEntity = gson.fromJson(request,PaymentEntity.class);
            PaymentEntity response;
            if (requestEntity.category.equals("M")){
                PaymentEntity entityCa = dao.findByTitleAndCategory(requestEntity.title,requestEntity.category);
                if (entityCa == null){
                    dao.insertAll(requestEntity);
                }else {
                    requestEntity.id = entityCa.id;
                    dao.updateAll(requestEntity);
                }
                response = dao.findByTitleAndCategory(requestEntity.title,requestEntity.category);
            }else {
                PaymentEntity entityCa = dao.findById(requestEntity.id);
                if (entityCa == null){
                    dao.insertAll(requestEntity);
                    List<PaymentEntity> listLast = dao.getAll();
                    response = listLast.get(listLast.size() - 1);
                }else {
                    requestEntity.id = entityCa.id;
                    dao.updateAll(requestEntity);
                    response = dao.findById(requestEntity.id);
                }

            }

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
            PaymentEntity requestEntity = gson.fromJson(request,PaymentEntity.class);
            PaymentEntity entity = dao.findByTitle(requestEntity.title);
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
            PaymentEntity entity = dao.findById(id);
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
            List<PaymentEntity> list = dao.getAll();
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(list)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getListRoomMonthly(String category) {
        try {
            List<PaymentEntity> list = dao.getAll();
            if (!category.isEmpty())
               list = dao.getAllFindByCategory(category);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(new PaymentList(list))
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getPaymentTitleCategory(String title,String category) {
        try {
            PaymentEntity payment = dao.findByTitleAndCategory(title,category);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(payment)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getPaymentIDCategory(int id,String category) {
        try {
            PaymentEntity payment = dao.findByIdAndCategory(id,category);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(payment)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getPaymentTitleCategoryList(String title,String category) {
        try {
            List<PaymentEntity> payment = dao.findByTitleAndCategoryList(title,category);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(new PaymentList(payment))
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getPaymentTitle(String title) {
        try {
            PaymentEntity payment = dao.findByTitle(title);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(payment)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }

    public BaseResponse getPaymentTitleList(String title) {
        try {
            List<PaymentEntity> payment = dao.findByTitleList(title);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(new PaymentList(payment))
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }


    public BaseResponse getListPaymentId(int idPayment) {
        try {
            PaymentEntity payment = dao.findById(idPayment);
            return new BaseResponse(
                    "Success",
                    CODE200,
                    gson.toJson(payment)
            );
        }catch (Exception e){
            return new BaseResponse(e.getMessage());
        }
    }
}
