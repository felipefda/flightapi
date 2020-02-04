package org.phelps.flightapi.entity.skypicker.flight;

import lombok.Data;

import java.util.List;

@Data
public class FlightResult {
    private String search_id;
    private List<FlightData> data;
    private String currency;

}
