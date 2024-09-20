package edu.neoflex.vacation_pay_calculator.controller;

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

    @Autowired
    MockMvc mockMvc;

    @Test
    public void calculateTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("salary", "100000.0")
                        .param("vacationDays", "10")
        )
                .andExpect(MockMvcResultMatchers.status().is(200));

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("salary", "100000.0")
                        .param("vacationDays", "10")
                        .param("vacationDate", "2024-20-09")
                )
                .andExpect(MockMvcResultMatchers.status().is(200));

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("salary", "100000.0")
                        .param("vacationDays", "10")
                        .param("vacationDate", "2024.20.09")
                )
                .andExpect(MockMvcResultMatchers.status().is(400));

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("salary", "100000.0")
                        .param("vacationDays", "100")
                        .param("vacationDate", "2024.20.09")
                )
                .andExpect(MockMvcResultMatchers.status().is(400));

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("salary", "-100000.0")
                        .param("vacationDays", "10")
                        .param("vacationDate", "2024.20.09")
                )
                .andExpect(MockMvcResultMatchers.status().is(400));
    }
}
