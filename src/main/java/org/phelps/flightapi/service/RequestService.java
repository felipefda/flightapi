package org.phelps.flightapi.service;

import org.phelps.flightapi.entity.Request;

import java.util.List;

public interface RequestService {
    void create(String ipAddress, String from, String[] destiny, String dateFrom, String dateTo, String currency);
    List<Request> list();
    void clearRequests();
}
