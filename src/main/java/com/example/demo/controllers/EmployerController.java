package com.example.demo.controllers;

import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.EmployerRequest;
import com.example.demo.dto.Marker;
import com.example.demo.dto.PageableResponse;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Employer;
import com.example.demo.services.EmployerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Validated
@RequestMapping("/employer")
public class EmployerController {
    private EmployerService employerService;
    private ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerController.class);

    public EmployerController(EmployerService employerService, ModelMapper modelMapper) {
        this.employerService = employerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/get")
    ResponseEntity<PageableResponse<Employer>> getAllEmployers(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "2") int size) {

        LOGGER.info("Was called employer/get");
        return ResponseEntity.ok(this.employerService.getAllEmployers(size, page));
    }

    @GetMapping("/get/{id}")
    ResponseEntity<Employer> getEmployerById(@PathVariable Long id) {
        LOGGER.info("Was called employer/get/{id}");
        return new ResponseEntity<>(employerService.getEmployerById(id).get(), HttpStatus.ACCEPTED);
    }

    @Validated(Marker.OnUpdate.class)
    @PostMapping("/create")
    ResponseEntity<Void> addEmployer(@RequestBody @Valid EmployerRequest employerRequest) {
        Employer employer = modelMapper.map(employerRequest, Employer.class);
        employerService.addNewEmployer(employer);

        LOGGER.info("Was called employer/create");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        employerService.deleteEmployerById(id);

        LOGGER.info("Was called employer/delete/{id}");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
