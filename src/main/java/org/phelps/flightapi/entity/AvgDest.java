package org.phelps.flightapi.entity;

import lombok.Data;

@Data
public class AvgDest {
    private String status;
    private String code;
    private String currency;
    private Double price_average = 0d;
    private AvgBagsPrice bags_price = new AvgBagsPrice(0d,0d);

}
