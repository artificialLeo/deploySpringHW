package com.example.demo.services;

import com.example.demo.entity.Employer;
import com.example.demo.repository.EmployerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployerServiceTest {

    private EmployerService employerService;
    private EmployerRepository employerRepository;

    @Autowired
    public EmployerServiceTest(EmployerService employerService, EmployerRepository employerRepository) {
        this.employerService = employerService;
        this.employerRepository = employerRepository;
    }

    @Test
    public void getAllEmployersTest() {
        Assertions.assertNotNull(employerService.getAllEmployers(1, 1));
    }

    @Test
    public void getEmployerByIdTest() {
        Assertions.assertNotNull(employerService.getEmployerById(1L));
    }

    @Test
    public void addNewEmployerTest() {
        employerService.addNewEmployer(new Employer());

        Assertions.assertEquals(employerRepository.findAll().size(), 4);
    }

    @Test
    public void deleteEmployerByIdTest() {
        employerService.deleteEmployerById(1L);

        Assertions.assertEquals(employerRepository.findAll().size(), 3);
    }


    /*
    getAllEmployersTest
    getEmployerByIdTest
    addNewEmployerTest
    deleteEmployerByIdTest
 */
}
