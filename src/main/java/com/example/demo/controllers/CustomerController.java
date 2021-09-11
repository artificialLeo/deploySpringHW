package com.example.demo.controllers;


import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.Marker;
import com.example.demo.dto.PageableResponse;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Validated
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;
    private ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/get")
    ResponseEntity<PageableResponse<Customer>> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "2") int size) {

        LOGGER.info("Was called customer/get");
        return ResponseEntity.ok(this.customerService.getAllCustomers(size, page));
    }

    @GetMapping("/get/{id}")
    ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        LOGGER.info("Was called customer/get/{id}");
        return new ResponseEntity<>(customerService.getCustomerById(id).get(), HttpStatus.ACCEPTED);
    }

    @Validated({Marker.OnUpdate.class})
    @PostMapping("/create")
    ResponseEntity<Void> addCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        Customer customer = modelMapper.map(customerRequest, Customer.class);
        customerService.addCustomer(customer);

        LOGGER.info("Was called customer/create");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);

        LOGGER.info("Was called customer/delete/{id}");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Validated(Marker.OnUpdate.class)
    @PostMapping("/{id}/account")
    ResponseEntity<Void> addAccountToCustomerById(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
        Account account = modelMapper.map(accountRequest, Account.class);
        customerService.addAccountToCustomerById(id, account);

        LOGGER.info("Was called customer/{id}/account");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Validated(Marker.OnUpdate.class)
    @PutMapping("/edit")
    ResponseEntity<Void> editCustomerById(@RequestBody CustomerRequest customerRequest) {
        Customer customer = modelMapper.map(customerRequest, Customer.class);

        try {
            customerService.editCustomerById(customer);

            LOGGER.info("Was called customer/edit");
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LOGGER.info("Was called customer/edit");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
