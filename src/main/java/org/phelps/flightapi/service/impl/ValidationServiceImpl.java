package org.phelps.flightapi.service.impl;

import org.phelps.flightapi.exception.ApiException;
import org.phelps.flightapi.service.CurrencyService;
import org.phelps.flightapi.service.IntegrationApiService;
import org.phelps.flightapi.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    IntegrationApiService integrationApiService;
    @Autowired
    CurrencyService currencyService;

    public void validateInputDate(String input) throws ApiException {
        if (input == null) {
            throw new ApiException("INPUT_DATE_ISNULL");
        }
        if (input.length() != 10) {
            throw new ApiException("INPUT_DATE_INVALID_SIZE");
        }

        try{
            Date date = this.convertInputDate(input);

            if(date.before(new Date())){
                throw new ApiException("INPUT_DATE_IN_THE_PAST");
            }
        }catch (ParseException e){
            throw new ApiException("INPUT_DATE_NOT_A_DATE");
        }
    }

    public void validateInputCurrency(String curr) throws ApiException {
        if (curr == null) {
            throw new ApiException("INPUT_CURRENCY_ISNULL");
        }
        if (curr.length() != 3) {
            throw new ApiException("INPUT_CURRENCY_INVALID_SIZE");
        }
        //check currency
        this.currencyService.checkCurrency(curr);
    }

    public void validateLocation(String location) throws ApiException {
        if(location == null){
            throw new ApiException("INPUT_LOCATION_IS_NULL");
        }

        if(!this.integrationApiService.isLocation(location)){
            throw new ApiException("INPUT_LOCATION_NOT_FOUND:"+location);
        }

    }

    public Date convertInputDate(String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.parse(date);
    }
}
