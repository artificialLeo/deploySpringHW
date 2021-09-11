package com.example.demo.controllers;

import com.example.demo.dto.*;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.exceptions.NoSuchItemException;
import com.example.demo.services.AccountService;
import com.example.demo.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Validated
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService;
    private CustomerService customerService;
    private ModelMapper modelMapper;

    public AccountController(AccountService accountService, ModelMapper modelMapper, CustomerService customerService) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
        this.customerService = customerService;
    }

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    public void socketAccount(Account account) {
        simpMessagingTemplate.convertAndSend("/account/changes", account);
    }

    @GetMapping("/get")
    ResponseEntity<PageableResponse<Account>> getAllAccounts(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "2") int size) {

        LOGGER.info("Was called account/get");
        return ResponseEntity.ok(this.accountService.getAllAccounts(size, page));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteAccountById(@PathVariable Long id) {
        this.accountService.deleteAccountById(id);

        LOGGER.info("Was called account/delete/{id}");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Validated(Marker.OnUpdate.class)
    @PutMapping("/put")
    ResponseEntity<Void> putMoneyToAccountById(@RequestBody @Valid AccountRequest accountRequest) {
        Account account = modelMapper.map(accountRequest, Account.class);
        this.accountService.putMoneyToAccountById(account.getId(), account.getBalance());

        LOGGER.info("Was called account/put");
        socketAccount(account);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Validated(Marker.OnCreate.class)
    @PostMapping("/create")
    ResponseEntity<Void> addAccount(@RequestBody @Valid AccountRequest accountRequest) {
        Account account = modelMapper.map(accountRequest, Account.class);
        Customer customer = customerService.getCustomerById(accountRequest.getCustomerId()).orElseThrow(() -> new NoSuchItemException("No customer with this ID."));
        account.setCustomer(customer);

        accountService.addAccount(account);

        LOGGER.info("Was called account/create");
        socketAccount(account);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Validated(Marker.OnUpdate.class)
    @PutMapping("/withdraw")
    ResponseEntity<Void> withdrawMoneyFromAccountById(@RequestBody @Valid AccountRequest accountRequest) {
        Account account = modelMapper.map(accountRequest, Account.class);

        this.accountService.withdrawMoneyFromAccountById(account.getId(), account.getBalance());

        LOGGER.info("Was called account/withdraw");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Validated(Marker.OnUpdate.class)
    @PutMapping("/transfer")
    ResponseEntity<Void> transferMoneyBetweenAccounts(@RequestBody @Valid AccountDtoRequest accountRequest) {
        AccountDto accountDto = modelMapper.map(accountRequest, AccountDto.class);
        this.accountService.transferMoneyBetweenAccounts(accountDto.getFrom(), accountDto.getTo(), accountDto.getBalance());
        Account account = new Account();
        account.setBalance(accountDto.getBalance());

        LOGGER.info("Was called account/transfer");
        socketAccount(account);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
