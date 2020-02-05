package org.phelps.flightapi.service;

import org.phelps.flightapi.entity.Currency;
import org.phelps.flightapi.exception.ApiException;

import java.util.List;

public interface CurrencyService {
    Currency create(String name) throws ApiException;
    Currency find(String name);
    void checkCurrency(String name) throws ApiException;
    void delete(String name) throws ApiException;
    List<Currency> list();
}
