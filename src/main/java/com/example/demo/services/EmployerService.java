package com.example.demo.services;

import com.example.demo.dto.PageableResponse;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Employer;
import com.example.demo.repository.EmployerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {
    private EmployerRepository employerRepository;

    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    //
    public PageableResponse<Employer> getAllEmployers(int size, int page) {
        Page<Employer> employers = this.employerRepository.findAll(PageRequest.of(page, size));

        return new PageableResponse<>(employers.getTotalElements(), employers.getContent());
    }

    public Optional<Employer> getEmployerById(Long id) {
        return this.employerRepository.findById(id);
    }

    public void addNewEmployer(Employer employer) {
        this.employerRepository.save(employer);
    }

    public void deleteEmployerById(Long id) {
        this.employerRepository.deleteById(id);
    }
}
