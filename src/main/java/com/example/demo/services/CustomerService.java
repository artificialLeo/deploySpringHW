package com.example.demo.services;

import com.example.demo.dto.PageableResponse;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //
    public PageableResponse<Customer> getAllCustomers(int size, int page) {
        Page<Customer> customers = this.customerRepository.findAll(PageRequest.of(page, size));

        return new PageableResponse<>(customers.getTotalElements(), customers.getContent());
    }

    public void deleteCustomerById(Long id) {
        this.customerRepository.deleteById(id);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return this.customerRepository.findById(id);
    }

    public void addCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        this.customerRepository.save(customer);
    }

    public Customer editCustomerById(Customer customer) throws Exception {
        customer.setAccounts(this.customerRepository.findById(customer.getId()).orElseThrow(Exception::new).getAccounts());

        return this.customerRepository.save(customer);
    }

    public void addAccountToCustomerById(Long id, Account account) {
        this.customerRepository.findById(id).map(customer -> {
            customer.addAccount(account);

            return this.customerRepository.save(customer);
        });
    }

}
