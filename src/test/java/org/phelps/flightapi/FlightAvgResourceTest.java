package org.phelps.flightapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.phelps.flightapi.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightAvgResourceTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Request without mandatory fields must fail")
    public void failMandatoryFields() throws Exception {
        this.mvc.perform(
                get("/flight/avg"))
                .andExpect(status().is(400));
    }

    @Test
    @DisplayName("Validate past date")
    public void validatePastDateNotPermitted() throws Exception {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            this.mvc.perform(
                    get("/flight/avg?dest=LIS&dateFrom=2018/01/01&dateTo=2018/11/01&curr=GBP").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andExpect(status().reason(containsString("Bad credentials")))
                    .andDo(print());
        }).hasCause(new ApiException("INVALID_INPUT_DATE_FROM"));
    }

}
