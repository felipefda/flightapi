package org.phelps.flightapi.service.impl;

import org.phelps.flightapi.entity.AvgBagsPrice;
import org.phelps.flightapi.entity.AvgDest;
import org.phelps.flightapi.entity.skypicker.flight.FlightData;
import org.phelps.flightapi.entity.skypicker.flight.FlightResult;
import org.phelps.flightapi.exception.ApiException;
import org.phelps.flightapi.service.FlightApiService;
import org.phelps.flightapi.service.IntegrationApiService;
import org.phelps.flightapi.service.RequestService;
import org.phelps.flightapi.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FlightApiServiceImpl implements FlightApiService {
    @Autowired
    ValidationService validationService;
    @Autowired
    IntegrationApiService integrationApiService;
    @Autowired
    RequestService requestService;

    @Override
    public List<AvgDest> getAverageFlight(HttpServletRequest request, String input_date_from, String input_date_to, String curr, String from, String[] dest) throws ApiException {
        if (input_date_from != null) {
            this.validationService.validateInputDate(input_date_from);
        }
        if (input_date_to != null) {
            this.validationService.validateInputDate(input_date_to);
        }
        this.validationService.validateInputCurrency(curr);
        this.validationService.validateLocation(from);
        if (dest.length < 1) {
            throw new ApiException("NO_INPUT_DESTINATION");
        }
        for (String row : dest) {
            this.validationService.validateLocation(row);
        }

        /**
         * Get Sky Picker Results
         */
        try {
            Date dateFrom = null;
            Date dateTo = null;

            if (input_date_from != null) {
                dateFrom = this.validationService.convertInputDate(input_date_from);
            }
            if (input_date_to != null) {
                dateTo = this.validationService.convertInputDate(input_date_to);
            }

            //prepare result object
            List<AvgDest> result = new ArrayList<>();

            for (String row : dest) {
                FlightResult quote = this.integrationApiService.getQuotesFromSkyPicker(from, row, dateFrom, dateTo, curr);

                AvgDest obj = this.mountDestination(quote);
                obj.setCode(row);
                result.add(obj);
            }

            this.requestService.create(request.getRemoteAddr(),from,dest,input_date_from,input_date_to,curr);

            return result;

        } catch (ParseException e) {
            throw new ApiException("INVALID_INPUT_DATE");
        }
    }

    private AvgDest mountDestination(FlightResult result) {
        AvgDest destination = new AvgDest();
        destination.setCurrency(result.getCurrency());

        Double total_price_flight = 0d;
        Double total_price_bag1 = 0d;
        Double total_price_bag2 = 0d;

        int count_flights = 0;
        int count_bag1_prices = 0;
        int count_bag2_prices = 0;

        if (result.getData().size() > 0) {
            for (FlightData row : result.getData()) {
                if (row.getPrice() != null) {
                    count_flights++;
                    total_price_flight = total_price_flight + row.getPrice();
                }

                if(row.getBags_price().containsKey(1)){
                    count_bag1_prices++;
                    total_price_bag1 = total_price_bag1 + row.getBags_price().get(1);
                }

                if(row.getBags_price().containsKey(2)){
                    count_bag2_prices++;
                    total_price_bag2 = total_price_bag2 + row.getBags_price().get(2);
                }

            }

            //calc
            double average_price_flight = total_price_flight / count_flights;
            double average_price_bag1 = total_price_bag1 / count_bag1_prices;
            double average_price_bag2 = total_price_bag2 / count_bag2_prices;

            destination.setPrice_average(this.round1(average_price_flight,2));
            destination.setBags_price(new AvgBagsPrice(this.round1(average_price_bag1,2), this.round1(average_price_bag2,2)));
            destination.setStatus("SUCCESS");
        } else {
            destination.setStatus("NO_FLIGHTS_AVAILABLE");
        }

        return destination;
    }

    public double round1(double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }


}

