package org.phelps.flightapi.service;

import org.phelps.flightapi.exception.ApiException;

import java.text.ParseException;
import java.util.Date;

public interface ValidationService {
    void validateInputDate(String input) throws ApiException;
    void validateInputCurrency(String curr) throws ApiException;
    void validateLocation(String location) throws ApiException;
    Date convertInputDate(String date) throws ParseException;
}
