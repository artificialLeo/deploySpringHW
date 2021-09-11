package com.example.demo.controllers;

import com.example.demo.services.CustomerService;
import com.example.demo.services.EmployerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployerService employerService;

    @MockBean
    private EmployerController employerController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employerController).setControllerAdvice(new ControllerAdvice()).build();
    }

    @Test
    void getAllEmployersTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/employer/get");

        this.mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void getEmployerByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/employer/get/1");

        this.mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void addEmployerTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/employer/create");

        this.mockMvc.perform(request).andExpect(status().isMethodNotAllowed());
    }

    @Test
    void deleteCustomerByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/employer/delete/1");

        this.mockMvc.perform(request).andExpect(status().isMethodNotAllowed());
    }


}

/*

getAllEmployersTest
getEmployerByIdTest
addEmployerTest
deleteCustomerByIdTest

*/