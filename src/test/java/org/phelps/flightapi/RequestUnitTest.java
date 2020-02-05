package org.phelps.flightapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.phelps.flightapi.entity.Request;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RequestUnitTest {
    @Test
    @DisplayName("Test request mount")
    public void testRequestMount() {
        String ipAddress = "127.0.0.1";
        String from = "GRU";
        String destiny = "LIS";
        String dateFrom = "2022/01/01";
        String dateTo = "2022/01/22";
        String currency = "USD";
        Request request = new Request(ipAddress,from,destiny,dateFrom,dateTo,currency);
        assertEquals(ipAddress,request.getIpAddress());
        assertEquals(from,request.getFrom());
        assertEquals(destiny,request.getDestiny());
        assertEquals(dateFrom,request.getDateFrom());
        assertEquals(dateTo,request.getDateTo());
        assertEquals(currency,request.getCurrency());
    }
}
