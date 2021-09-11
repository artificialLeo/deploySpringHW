package com.example.demo.controllers;

import com.example.demo.dto.PageableResponse;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.services.AccountService;
import com.example.demo.services.CustomerService;
import com.example.demo.services.EmployerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(AccountController.class)
//@SpringBootTest
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService accountService;
//    @MockBean
//    private CustomerService customerService;
//    @MockBean
//    private EmployerService employerService;
//    @MockBean
//    private ModelMapper modelMapper;
    @MockBean
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).setControllerAdvice(new ControllerAdvice()).build();
    }

    @Test
    void getAllAccountsTest() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders.get("/account/get");
//        MvcResult mvcResult = mockMvc.perform(request).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//
//        String test = mvcResult.getResponse().getContentAsString();
//        System.out.println(test);
//        ResponseEntity<PageableResponse<Account>> data = new ObjectMapper().readValue(test, new TypeReference<ResponseEntity<PageableResponse<Account>>>(){});

        RequestBuilder request = MockMvcRequestBuilders.get("/account/get");

        this.mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void deleteAccountByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/account/delete/1");

        this.mockMvc.perform(request).andExpect(status().isMethodNotAllowed());
    }

    @Test
    void putMoneyToAccountByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/account/put");

        this.mockMvc.perform(request).andExpect(status().isMethodNotAllowed());
    }

    @Test
    void addAccountTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/account/create");

        this.mockMvc.perform(request).andExpect(status().isMethodNotAllowed());
    }

    @Test
    void withdrawMoneyFromAccountByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/account/withdraw");

        this.mockMvc.perform(request).andExpect(status().isMethodNotAllowed());
    }

    @Test
    void transferMoneyBetweenAccountsTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/account/transfer");

        this.mockMvc.perform(request).andExpect(status().isMethodNotAllowed());
    }



}

/*
getAllAccountsTest
deleteAccountByIdTest
putMoneyToAccountByIdTest
addAccountTest
withdrawMoneyFromAccountByIdTest
transferMoneyBetweenAccountsTest
*/
