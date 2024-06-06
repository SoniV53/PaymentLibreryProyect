package com.control.paymentlibrery.database.repository;

public interface InterfaceCRUD {
    BaseResponse createOrUpdateRoom(String request);
    BaseResponse deleteRoom(String request);
    BaseResponse getListRoom();
}
