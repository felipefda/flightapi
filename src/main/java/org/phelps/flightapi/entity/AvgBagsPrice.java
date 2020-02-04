package org.phelps.flightapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvgBagsPrice {
    private Double bag1_average;
    private Double bag2_average;

}