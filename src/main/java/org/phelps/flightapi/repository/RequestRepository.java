package org.phelps.flightapi.repository;

import org.phelps.flightapi.entity.Request;

import java.util.List;

public interface RequestRepository {
    void create(Request request);
    List<Request> list();
    void clearRequests();
}
