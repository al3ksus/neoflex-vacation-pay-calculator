package edu.neoflex.vacation_pay_calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class VacationPayControllerTest {

    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void calculateTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate").param("salary", "30000.0").param("vacationDays", "10")
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }
}
