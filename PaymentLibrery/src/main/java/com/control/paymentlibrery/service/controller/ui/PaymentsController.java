package com.control.paymentlibrery.service.controller.ui;

import android.content.Context;

import com.control.paymentlibrery.database.repository.BaseResponse;
import com.control.paymentlibrery.database.repository.ui.AmountMonthRepository;
import com.control.paymentlibrery.database.repository.ui.PaymentMonthRepository;
import com.control.paymentlibrery.database.repository.ui.PaymentRepository;
import com.control.paymentlibrery.database.repository.ui.SubPaymentRepository;
import com.control.paymentlibrery.service.controller.BaseController;
import com.control.paymentlibrery.service.model.AmountMonthDataModel;
import com.control.paymentlibrery.service.model.PaymentDataModel;
import com.control.paymentlibrery.service.model.PaymentDataMonthModel;
import com.control.paymentlibrery.service.model.list.PaymentDataList;
import com.control.paymentlibrery.service.model.list.PaymentDataMonthList;
import com.control.paymentlibrery.service.model.list.SubPaymentDataMonthList;
import com.control.paymentlibrery.service.model.with.PaymentMonthDataAndPaymentData;

import java.util.ArrayList;
import java.util.List;

public class PaymentsController extends BaseController {

    public PaymentsController(Context context) {
        super(context);
    }

    public AmountMonthDataModel createEstimate(AmountMonthDataModel request){
        AmountMonthRepository repository = new AmountMonthRepository(dataBaseRoom);
        BaseResponse response = repository.createOrUpdateRoom(printJson(request));
        return responseJson(response.getResponse(), AmountMonthDataModel.class);
    }

    public AmountMonthDataModel getEstimate(int idMonth, int idYear){
        AmountMonthRepository repository = new AmountMonthRepository(dataBaseRoom);
        BaseResponse response =  repository.getListRoomYearAndMonth(idMonth,idYear);
        return responseJson(response.getResponse(), AmountMonthDataModel.class);
    }


    private PaymentDataModel getCreatePayment(PaymentDataModel request) throws Exception {
        PaymentRepository repository = new PaymentRepository(dataBaseRoom);
        BaseResponse response =  repository.createOrUpdateRoom(printJson(request));
        return responseJson(response.getResponse(),PaymentDataModel.class);
    }

    public BaseResponse createNewPaymentUncle(PaymentDataModel requestPayment,PaymentDataMonthModel requestMonth) {
        try {
            PaymentRepository repository = new PaymentRepository(dataBaseRoom);
            PaymentMonthRepository repositoryMonths = new PaymentMonthRepository(dataBaseRoom);

            PaymentDataList responseListExt = responseJson(repository.getPaymentTitleList(requestPayment.getTitle()).getResponse(), PaymentDataList.class);

            int idPayment = 0;
            if (responseListExt != null && responseListExt.getDataList() != null){
                for (PaymentDataModel item :responseListExt.getDataList()) {
                    PaymentDataMonthModel responseExist = responseJson(repositoryMonths.getListRoomYearAndMonth(requestMonth.getIdAmountMonth(),item.getId()).getResponse(), PaymentDataMonthModel.class);
                    if (responseExist != null && item.getCategory().equalsIgnoreCase("U")){
                        idPayment = item.getId();
                        break;
                    }
                }
            }

            PaymentDataMonthModel responseSe = responseJson(repositoryMonths.getListRoomYearAndMonth(requestMonth.getIdAmountMonth(),idPayment).getResponse(), PaymentDataMonthModel.class);
            if (responseSe != null)
                requestPayment.setId(idPayment);


            //crea valores iniciales para pago
            PaymentDataModel paymentResponse = getCreatePayment(requestPayment);
            //crea valores para un mes seleccionado llama a los gastos unicos

            if ((responseSe == null)){
                createPayment(paymentResponse,requestMonth,repositoryMonths);
            }else if (paymentResponse.getCategory().equals("U") && responseSe.getIdPayment() != paymentResponse.getId()){
                createPayment(paymentResponse,requestMonth,repositoryMonths);
            }else if (paymentResponse.getCategory().equals("M") && responseSe.getIdPayment() != paymentResponse.getId()){
                createPayment(paymentResponse,requestMonth,repositoryMonths);
            }else {
                requestMonth.setId(responseSe.getId());
                createPayment(paymentResponse,requestMonth,repositoryMonths);
            }

            return new BaseResponse("Creado Exitosamente","200");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void createPayment(PaymentDataModel paymentResponse,PaymentDataMonthModel requestMonth,PaymentMonthRepository repositoryMonths){
        if (paymentResponse != null){
            requestMonth.setIdPayment(paymentResponse.getId());

            if (requestMonth.getSubPaymentDataMonthModels() != null && requestMonth.getSubPaymentDataMonthModels().size() > 0)
                requestMonth.setAmountPaid(0);

            BaseResponse res = repositoryMonths.createOrUpdateRoom(printJson(requestMonth));
            PaymentDataMonthModel paymentMonthResponse = responseJson(res.getResponse(),PaymentDataMonthModel.class);
            SubPaymentRepository subPaymentRepository = new SubPaymentRepository(dataBaseRoom);

            if (requestMonth.getSubPaymentDataMonthModels() != null && requestMonth.getSubPaymentDataMonthModels().size() > 0){
                requestMonth.getSubPaymentDataMonthModels().forEach(item  -> {
                    item.setIdDetailsPay(paymentMonthResponse.getId());
                    subPaymentRepository.createOrUpdateRoom(gson.toJson(item));
                });
            }else {
                BaseResponse subResponse = subPaymentRepository.getListRoomSubPayment(paymentMonthResponse.getId());
                SubPaymentDataMonthList subPaymentListResponse = responseJson(subResponse.getResponse(), SubPaymentDataMonthList.class);
                subPaymentListResponse.getDataList().forEach(subPaymentSendModel -> {
                    subPaymentRepository.deleteRoomId(subPaymentSendModel.getId());
                });
            }
        }
    }

    public BaseResponse addPaymentMonthlyAnUncle(List<PaymentDataModel> listSelectPayment,int idAmountMonth) {
        listSelectPayment.forEach(item -> {
            PaymentMonthRepository repositoryMonths = new PaymentMonthRepository(dataBaseRoom);
            PaymentDataMonthList respPaymentMonth = responseJson(repositoryMonths.getListRoomMovementPayment(item.getId()).getResponse(),PaymentDataMonthList.class);
            PaymentDataMonthModel paymentDataMonthExist = responseJson(repositoryMonths.getListRoomYearAndMonth(idAmountMonth,item.getId()).getResponse(),PaymentDataMonthModel.class);

            if (paymentDataMonthExist == null){
                PaymentDataMonthModel requestPayment = new PaymentDataMonthModel(idAmountMonth,item.getId());
                requestPayment.setQuotesPay(quotesExist(respPaymentMonth.getDataList(),paymentDataMonthExist));
                requestPayment.setAmountPaid(0.0);
                requestPayment.setStatus("false");
                repositoryMonths.createOrUpdateRoomPayment(gson.toJson(requestPayment));
            }

        });
        return new BaseResponse("Se creo Corretamente","200");
    }

    private String quotesExist( List<PaymentDataMonthModel> getDataList,PaymentDataMonthModel paymentDataMonthExist){
        if (paymentDataMonthExist == null){
            int quoteM = 0;
            if (getDataList != null){
                for (PaymentDataMonthModel itemRe:getDataList) {
                    int quote = !itemRe.getQuotesPay().isEmpty() ? Integer.parseInt(itemRe.getQuotesPay()) : 0;
                    if (quote > quoteM)
                        quoteM = quote;
                }
            }
            return String.valueOf((quoteM + 1));
        }else return "0";
    }

    public BaseResponse createPaymentMonthly(PaymentDataModel request) {
        try {
            getCreatePayment(request);
            return new BaseResponse("Creado Exitosamente","200");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PaymentDataMonthModel updateRoomStatus(int id, String status) {
        PaymentMonthRepository repositoryMonths = new PaymentMonthRepository(dataBaseRoom);
        BaseResponse base = repositoryMonths.updateRoomStatus(id,status);
        PaymentDataMonthModel response = responseJson(base.getResponse(),PaymentDataMonthModel.class);
        return response;
    }
    public List<PaymentMonthDataAndPaymentData> getPaymentMonthsResponse(int idMonth) {
        List<PaymentMonthDataAndPaymentData> responses = new ArrayList<>();
        PaymentMonthRepository repositoryMonths = new PaymentMonthRepository(dataBaseRoom);

        try {
            PaymentDataMonthList respPaymentMonth = responseJson(repositoryMonths.getListRoomSelectMonth(idMonth).getResponse(), PaymentDataMonthList.class);
            if (respPaymentMonth != null){
                PaymentRepository repositoryPayment = new PaymentRepository(dataBaseRoom);

                respPaymentMonth.getDataList().forEach(item -> {
                    PaymentDataModel payment = responseJson(repositoryPayment.getListPaymentId(item.getIdPayment()).getResponse(),PaymentDataModel.class);
                    SubPaymentRepository supRepo = new SubPaymentRepository(dataBaseRoom);
                    SubPaymentDataMonthList subPaymentListResponse = responseJson(supRepo.getListRoomSubPayment(item.getId()).getResponse(), SubPaymentDataMonthList.class);
                    item.setSubPaymentDataMonthModels(subPaymentListResponse.getDataList());
                    PaymentMonthDataAndPaymentData newDataPay = new PaymentMonthDataAndPaymentData(payment,item);

                    responses.add(newDataPay);
                });
            }
            return responses;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<PaymentDataModel> getPaymentMonthsResponseFilterMonthly(String category) {
        PaymentRepository repository = new PaymentRepository(dataBaseRoom);
        PaymentDataList responses = responseJson(repository.getListRoomMonthly(category).getResponse(), PaymentDataList.class);
        return responses.getDataList();
    }

    public BaseResponse deletePaymentCas(int id){
        try {
            PaymentRepository repository = new PaymentRepository(dataBaseRoom);
            PaymentMonthRepository repositoryMonths = new PaymentMonthRepository(dataBaseRoom);
            PaymentDataMonthList responseExist = responseJson(repositoryMonths.getListRoomMovementPayment(id).getResponse(), PaymentDataMonthList.class);

            if (responseExist != null && responseExist.getDataList() != null){
                responseExist.getDataList().forEach(paymentDataMonthModel -> {
                    repositoryMonths.deleteRoom(paymentDataMonthModel.getId());
                });
            }

            repository.deleteRoom(id);

            return new BaseResponse("Se Elimino Exitosamente","200");
        }catch (Exception e){
            return new BaseResponse("Error ","400");
        }
    }

    public BaseResponse deletePaymentUni(int id){
        try {
            //repositiorios
            PaymentRepository repository = new PaymentRepository(dataBaseRoom);
            PaymentMonthRepository repositoryMonths = new PaymentMonthRepository(dataBaseRoom);

            PaymentDataMonthModel responseExist = responseJson(repositoryMonths.getRoomSelectMonthFindId(id).getResponse(), PaymentDataMonthModel.class);

            if (responseExist != null){
                repositoryMonths.deleteRoom(responseExist.getId());

                PaymentDataModel gene = responseJson(repository.getListPaymentId(responseExist.getIdPayment()).getResponse(), PaymentDataModel.class);
                if (gene != null && !gene.getCategory().equalsIgnoreCase("M"))
                    repository.deleteRoom(gene.getId());
            }

            return new BaseResponse("Se Elimino Exitosamente","200");
        }catch (Exception e){
            return new BaseResponse("Error ","400");
        }
    }

    public BaseResponse deleteSubPaymentUni(int id){
        try {
            //repositiorios
            SubPaymentRepository repository = new SubPaymentRepository(dataBaseRoom);
            repository.deleteRoomId(id);
            return new BaseResponse("Se Elimino Exitosamente","200");
        }catch (Exception e){
            return new BaseResponse("Error ","400");
        }
    }
}
