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

    //Servicio Para crear Un gasto de tipo Mensual o unico
    public BaseResponse createNewPayment(PaymentDataModel requestPayment,PaymentDataMonthModel requestMonth) {
        try {
            //repository connect
            PaymentRepository repository = new PaymentRepository(dataBaseRoom);
            PaymentMonthRepository repositoryMonths = new PaymentMonthRepository(dataBaseRoom);

            if (requestMonth.getQuotesPay() != null && !requestMonth.getQuotesPay().isEmpty())
                requestPayment.setQuoteStart(Integer.parseInt(requestMonth.getQuotesPay()));

            boolean typeCategory = requestPayment.getCategory().equals("M");

            if (typeCategory){
                PaymentDataModel detailsPayment = responseJson(repository.getPaymentTitleCategory(requestPayment.getTitle(),"M").getResponse(),PaymentDataModel.class);
                return createValidData(detailsPayment,requestPayment,requestMonth,repositoryMonths,"M");
            }else {
                PaymentDataList detailsPaymentList = responseJson(repository.getPaymentTitleCategoryList(requestPayment.getTitle(),"U").getResponse(),PaymentDataList.class);
                PaymentDataModel detailsPayment = null;

                if (detailsPaymentList != null && detailsPaymentList.getDataList() != null){
                    for (PaymentDataModel paymentDataModel:detailsPaymentList.getDataList()) {
                        PaymentDataMonthModel responseSe = responseJson(repositoryMonths.getListRoomYearAndMonth(requestMonth.getIdAmountMonth(),paymentDataModel.getId()).getResponse(), PaymentDataMonthModel.class);
                        if (responseSe != null){
                            detailsPayment = paymentDataModel;
                            break;
                        }
                    }
                }

                return createValidData(detailsPayment,requestPayment,requestMonth,repositoryMonths,"U");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //Metodo valida y crea segun si ya existe en el mes este gasto puede tener dos con el mismo nombre pero no de la misma categoria
    private BaseResponse createValidData(PaymentDataModel detailsPayment,PaymentDataModel requestPayment,PaymentDataMonthModel requestMonth,PaymentMonthRepository repositoryMonths,String category) throws Exception{
        AmountMonthRepository repository = new AmountMonthRepository(dataBaseRoom);
        AmountMonthDataModel amountM = responseJson(repository.getListRoomYearAndMonth(requestMonth.getIdAmountMonth()).getResponse(),AmountMonthDataModel.class);

        if (amountM != null){
            if (detailsPayment == null){
                //crea valores iniciales para pago si no existe pago
                PaymentDataModel paymentResponse = getCreatePayment(requestPayment);
                createPayment(paymentResponse,requestMonth,repositoryMonths);
                return new BaseResponse("Creado Exitosamente","200");
            }else {
                PaymentDataMonthModel responseSe = responseJson(repositoryMonths.getListRoomYearAndMonth(requestMonth.getIdAmountMonth(),detailsPayment.getId()).getResponse(), PaymentDataMonthModel.class);
                responseSe = responseSe != null && detailsPayment.getCategory().equals(category) ? responseSe : null;
                if (responseSe == null){
                    //crea valores iniciales para pago si no existe pago
                    PaymentDataModel paymentResponse = getCreatePayment(requestPayment);
                    createPayment(paymentResponse,requestMonth,repositoryMonths);
                    return new BaseResponse("Creado Exitosamente","200");
                }
            }
            return new BaseResponse("Error: Gasto Existente","400");
        }else {
            return new BaseResponse("Error: ID de Estimación Incorrecta","400");
        }
    }

    //Metodo para crear un gasto segun el mes recopilando, el id del gasto creado recientemente
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
                requestPayment.setQuotesPay(quotesExist(respPaymentMonth.getDataList(),paymentDataMonthExist,item.getQuoteStart()));
                requestPayment.setAmountPaid(0.0);
                requestPayment.setStatus("false");
                repositoryMonths.createOrUpdateRoomPayment(gson.toJson(requestPayment));
            }

        });
        return new BaseResponse("Se creo Corretamente","200");
    }

    private String quotesExist( List<PaymentDataMonthModel> getDataList,PaymentDataMonthModel paymentDataMonthExist, int quoteStart){
        if (paymentDataMonthExist == null){
            int quoteM = quoteStart;
            boolean isExist = false;
            if (getDataList != null){
                for (PaymentDataMonthModel itemRe:getDataList) {
                    int quote = !itemRe.getQuotesPay().isEmpty() ? Integer.parseInt(itemRe.getQuotesPay()) : 0;
                    if (quote > quoteM)
                        quoteM = quote;
                }
                isExist = getDataList.size() > 0;
            }
            return String.valueOf(isExist ? (quoteM + 1) : quoteStart);
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

    //Servicio que trae todos los gastos del mes
    public List<PaymentMonthDataAndPaymentData> getPaymentMonthsResponse(int idMonthSt) {
        List<PaymentMonthDataAndPaymentData> responses = new ArrayList<>();
        PaymentMonthRepository repositoryMonths = new PaymentMonthRepository(dataBaseRoom);

        try {
            PaymentDataMonthList respPaymentMonth = responseJson(repositoryMonths.getListRoomSelectMonth(idMonthSt).getResponse(), PaymentDataMonthList.class);
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
