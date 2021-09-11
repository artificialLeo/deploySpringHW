package com.example.demo.controllers;

import com.example.demo.services.AccountService;
import com.example.demo.services.CustomerService;
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
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).setControllerAdvice(new ControllerAdvice()).build();
    }

    @Test
    void getAllCustomersTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/customer/get");

        this.mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void getCustomerByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/customer/get/1");

        this.mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void addCustomerTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/customer/create");

        this.mockMvc.perform(request).andExpect(status().isMethodNotAllowed());
    }

    @Test
    void deleteCustomerByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/delete/1");

        this.mockMvc.perform(request).andExpect(status().isNotFound());
    }

    @Test
    void addAccountToCustomerByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/1/account");

        this.mockMvc.perform(request).andExpect(status().isNotFound());
    }

    @Test
    void editCustomerByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/edit");

        this.mockMvc.perform(request).andExpect(status().isNotFound());
    }

}

/*

getAllCustomersTest
getCustomerByIdTest
addCustomerTest
deleteCustomerByIdTest
addAccountToCustomerByIdTest
editCustomerByIdTest

*/