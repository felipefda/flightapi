package org.phelps.flightapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.Calendar;

@Data
@AllArgsConstructor
public class Request {
    private Long id;
    private Date createdOn;
    private String ipAddress;
    private String from;
    private String destiny;
    private String dateFrom;
    private String dateTo;
    private String currency;

    public Request(
            String ipAddress,
            String from,
            String destiny,
            String dateFrom,
            String dateTo,
            String currency){
        super();
        this.ipAddress = ipAddress;
        this.from = from;
        this.destiny = destiny;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.currency = currency;
        this.createdOn = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }
}
