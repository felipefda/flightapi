package org.phelps.flightapi.service.impl;

import org.phelps.flightapi.entity.Request;
import org.phelps.flightapi.repository.RequestRepository;
import org.phelps.flightapi.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestRepository repository;

    @Override
    public void create(String ipAddress, String from, String[] destiny, String dateFrom, String dateTo, String currency) {
        Request request = new Request(ipAddress,from, destiny!=null?Arrays.toString(destiny):null,dateFrom,dateTo,currency);
        this.repository.create(request);
    }

    @Override
    public List<Request> list() {
        return this.repository.list();
    }

    @Override
    public void clearRequests() {
        this.repository.clearRequests();
    }
}
