package org.phelps.flightapi.resource;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.phelps.flightapi.entity.Currency;
import org.phelps.flightapi.entity.Request;
import org.phelps.flightapi.exception.ApiException;
import org.phelps.flightapi.service.CurrencyService;
import org.phelps.flightapi.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminResource {
    @Autowired
    CurrencyService currencyService;

    @Autowired
    RequestService requestService;

    @ApiOperation("List available currencies to use in API")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Currency list")
    })
    @GetMapping("/currency/list")
    public List<Currency> listCurrencies() {
        return this.currencyService.list();
    }

    @ApiOperation("Create currency in API")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Currency created"),
            @ApiResponse(code = 500, message = "API Exception")
    })
    @PostMapping("/currency/create")
    public Currency createCurrency(@ApiParam("Currency name") @RequestParam("name") String name) throws ApiException {
        return this.currencyService.create(name);
    }

    @ApiOperation("Delete currency in API")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Currency deleted"),
            @ApiResponse(code = 500, message = "API Exception")
    })
    @DeleteMapping("/currency/delete")
    public void deleteCurrency(@ApiParam("Currency name") @RequestParam("name") String name) throws ApiException {
        this.currencyService.delete(name);
    }

    @ApiOperation("List requests in API")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Request list")
    })
    @GetMapping("/request/list")
    public List<Request> listRequest() {
        return this.requestService.list();
    }

    @ApiOperation("Clear all requests")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Requests cleared"),
            @ApiResponse(code = 500, message = "API Exception")
    })
    @DeleteMapping("/request/clear")
    public void deleteRequest() {
        this.requestService.clearRequests();
    }
}
