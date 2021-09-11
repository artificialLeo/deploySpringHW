package com.example.demo.services;

import com.example.demo.entity.Account;
import com.example.demo.entity.Currency;
import com.example.demo.entity.Customer;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {
    private EmployerService employerService;
    private EmployerRepository employerRepository;

    private CustomerService customerService;
    private CustomerRepository customerRepository;

    private AccountService accountService;
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceTest(EmployerService employerService, EmployerRepository employerRepository, CustomerService customerService, CustomerRepository customerRepository, AccountService accountService, AccountRepository accountRepository) {
        this.employerService = employerService;
        this.employerRepository = employerRepository;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @Test
    public void getAllAccountsTest() {
        Assertions.assertNotNull(accountService.getAllAccounts(1, 1));
    }

    @Test
    public void deleteAccountByIdTest() {
        accountService.deleteAccountById(1L);

        Assertions.assertEquals(accountRepository.findAll().size(), 4);
    }

    @Test
    public void addAccountTest() {
        Customer customer = new Customer();
        Account account = new Account("123213123", Currency.GBP, 200D, customer);
        customer.addAccount(account);

        accountService.addAccount(account); // null exception

        Assertions.assertEquals(accountRepository.findAll().size(), 6);
    }

    @Test
    public void putMoneyToAccountByIdTest() {
        accountService.putMoneyToAccountById(2L, 100D);

        Assertions.assertEquals(accountRepository.findAll().get(1).getBalance(), 1100);
    }

    @Test
    public void withdrawMoneyFromAccountByIdTest() {
        accountService.withdrawMoneyFromAccountById(2L, 100D);

        Assertions.assertEquals(accountRepository.findAll().get(1).getBalance(), 1000);
    }

    @Test
    public void transferMoneyBetweenAccountsTest() {
        accountService.transferMoneyBetweenAccounts(2L, 1L, 100D);

        Assertions.assertEquals(accountRepository.findAll().get(1).getBalance(), 900);
        Assertions.assertEquals(accountRepository.findAll().get(0).getBalance(), 200);
    }

}

/*
getAllAccountsTest
deleteAccountByIdTest
addAccountTest
putMoneyToAccountByIdTest
withdrawMoneyFromAccountByIdTest
transferMoneyBetweenAccountsTest
 */