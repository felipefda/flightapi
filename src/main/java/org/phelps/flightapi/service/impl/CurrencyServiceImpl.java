package org.phelps.flightapi.service.impl;

import org.phelps.flightapi.entity.Currency;
import org.phelps.flightapi.exception.ApiException;
import org.phelps.flightapi.repository.CurrencyRepository;
import org.phelps.flightapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    CurrencyRepository repository;


    @Override
    public Currency create(String name) throws ApiException {
        if(this.find(name) != null){
            throw new ApiException("CURRENCY_ALREADY_EXIST");
        }
        return repository.create(new Currency(name));
    }

    @Override
    public Currency find(String name) {
        return repository.find(name);
    }

    @Override
    public void checkCurrency(String name) throws ApiException {
        if(this.find(name) == null){
            throw new ApiException("CURRENCY_NOT_FOUND");
        }
    }

    @Override
    public void delete(String name) throws ApiException {
        if(this.find(name) == null){
            throw new ApiException("CURRENCY_NOT_FOUND");
        }
        this.repository.delete(name);
    }

    @Override
    public List<Currency> list() {
        return this.repository.list();
    }
}
