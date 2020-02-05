package org.phelps.flightapi.service;

import org.phelps.flightapi.entity.skypicker.flight.FlightResult;
import org.phelps.flightapi.exception.ApiException;

import java.util.Date;

public interface IntegrationApiService {
    FlightResult getQuotesFromSkyPicker(String flyFrom, String flyTo, Date dateFrom, Date dateTo, String curr ) throws ApiException;
    boolean isLocation(String location) throws ApiException;
}
