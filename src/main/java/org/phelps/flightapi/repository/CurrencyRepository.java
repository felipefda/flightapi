package org.phelps.flightapi.repository;

import org.phelps.flightapi.entity.Currency;

import java.util.List;

public interface CurrencyRepository {
    Currency create(Currency currency);
    List<Currency> list();
    void delete(String name);
    Currency find(String name);
}
