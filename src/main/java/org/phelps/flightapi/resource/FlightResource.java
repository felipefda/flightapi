package org.phelps.flightapi.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.phelps.flightapi.entity.AvgDest;
import org.phelps.flightapi.exception.ApiException;
import org.phelps.flightapi.service.FlightApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightResource {

    @Autowired
    FlightApiService flightApiService;

    @ApiOperation("Get average quotes of flights")
    @ApiResponses(value={
            @ApiResponse(code = 500,message = "An exception was found. Check the error message"),
            @ApiResponse(code = 200, message = "Average quotes")
    })
    @GetMapping("/avg")
    public List<AvgDest> average(
            @ApiParam("Optional start date of flights") @RequestParam(value="dateFrom", required = false) String dateFrom,
            @ApiParam("Optional end date of flights") @RequestParam(value="dateTo", required = false) String dateTo,
            @ApiParam("Currency by EUR") @RequestParam(value="curr", required = true) String curr,
            @ApiParam("From location") @RequestParam(value="from", required = true) String from,
            @ApiParam("List of destinations") @RequestParam(value="dest", required = true) String[] dest
            ) throws ApiException {

        return this.flightApiService.getAverageFlight(dateFrom,dateTo,curr,from,dest);
    }
}
