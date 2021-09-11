package com.example.demo.services;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceTest {
    private EmployerService employerService;
    private EmployerRepository employerRepository;

    private CustomerService customerService;
    private CustomerRepository customerRepository;

    private AccountService accountService;
    private AccountRepository accountRepository;

    @Autowired
    public CustomerServiceTest(EmployerService employerService, EmployerRepository employerRepository, CustomerService customerService, CustomerRepository customerRepository, AccountService accountService, AccountRepository accountRepository) {
        this.employerService = employerService;
        this.employerRepository = employerRepository;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @Test
    public void getAllCustomersTest() {
        Assertions.assertNotNull(customerService.getAllCustomers(1, 1));
    }

    @Test
    public void deleteCustomerByIdTest() {
        customerService.deleteCustomerById(1L);

        Assertions.assertEquals(customerRepository.findAll().size(), 3);
    }

    @Test
    public void getCustomerByIdTest() {
        Assertions.assertNotNull(customerService.getCustomerById(2L));
    }

    @Test
    public void addCustomerTest() {
        customerService.addCustomer(new Customer());

        Assertions.assertEquals(customerRepository.findAll().size(), 4);
    }

    @Test
    public void editCustomerByIdTest() throws Exception {
        customerService.editCustomerById(new Customer());

        Assertions.assertEquals(customerRepository.findAll().size(), 4);
    }

    @Test
    public void addAccountToCustomerByIdTest() throws Exception {
        customerService.addAccountToCustomerById(2L, new Account());

        Assertions.assertEquals(accountRepository.findAll().size(), 6);
    }



}

/*
getAllCustomersTest
deleteCustomerByIdTest
getCustomerByIdTest
addCustomerTest
editCustomerByIdTest
addAccountToCustomerByIdTest
 */