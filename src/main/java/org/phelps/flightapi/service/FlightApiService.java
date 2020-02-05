package org.phelps.flightapi.service;

import org.phelps.flightapi.entity.AvgDest;
import org.phelps.flightapi.exception.ApiException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FlightApiService {
    List<AvgDest> getAverageFlight(HttpServletRequest request, String dateFrom, String dateTo, String curr, String from, String[] dest) throws ApiException;
}
