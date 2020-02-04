package org.phelps.flightapi.entity.skypicker.flight;

import lombok.Data;

import java.util.HashMap;

@Data
public class FlightData {
    private String id;
    private Double price;
    private HashMap<Integer,Double> bags_price;
    private String flyTo;
}
