package org.phelps.flightapi.service.impl;

import org.phelps.flightapi.entity.skypicker.flight.FlightResult;
import org.phelps.flightapi.entity.skypicker.locations.LocationResult;
import org.phelps.flightapi.exception.ApiException;
import org.phelps.flightapi.service.IntegrationApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class IntegrationApiServiceImpl implements IntegrationApiService {
    @Autowired
    RestTemplate restTemplate;

    private final String sky_picker_api_endpoint = "https://api.skypicker.com/";

    @Override
    @Cacheable("flights")
    public FlightResult getQuotesFromSkyPicker(String flyFrom, String flyTo, Date dateFrom, Date dateTo, String curr) {
        String url = this.getSkyPickerFlightURL(flyFrom,flyTo,dateFrom,dateTo,curr);
        ResponseEntity<FlightResult> response = this.restTemplate.getForEntity(
                url, FlightResult.class
        );

        return response.getBody();
    }

    @Override
    public boolean isLocation(String location) throws ApiException {
        try{
            ResponseEntity<LocationResult> response = this.restTemplate.getForEntity(
                    this.getSkyPickerLocationURL(location), LocationResult.class
            );

            return Objects.requireNonNull(response.getBody()).getLocations() != null && response.getBody().getLocations().size() != 0;
        }catch (RestClientException e){
            throw new ApiException("IMPOSSIBLE_TO_RETRIEVE_RESULTS_FROM_SKYPICKER");
        }

    }

    private String getSkyPickerLocationURL(String location){
        return this.sky_picker_api_endpoint+"locations?type=id&id="+location;
    }

    private String getSkyPickerFlightURL(String flyFrom, String flyTo, Date dateFrom, Date dateTo, String curr){
        //format
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");

        String url = this.sky_picker_api_endpoint + "flights?"+
                "flyFrom="+flyFrom+"&"+
                "to="+flyTo+"&"+
                "partner=picky&"+
                "curr="+curr+"&";

        if(dateFrom != null){
            url+= "dateFrom="+date_format.format(dateFrom)+"&";
        }
        if(dateTo != null){
            url+= "dateTo="+date_format.format(dateTo)+"&";
        }

        return url;
    }
}
