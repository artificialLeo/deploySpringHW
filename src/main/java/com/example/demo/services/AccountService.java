package com.example.demo.services;

import com.example.demo.dto.PageableResponse;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.exceptions.BalanceException;
import com.example.demo.exceptions.NoSuchItemException;
import com.example.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //
    public PageableResponse<Account> getAllAccounts(int size, int page) {
        Page<Account> accounts = this.accountRepository.findAll(PageRequest.of(page, size));

        return new PageableResponse<>(accounts.getTotalElements(), accounts.getContent());
    }

    public void deleteAccountById(Long id) {
        this.accountRepository.deleteById(id);
    }

    public void addAccount(Account account) {
        this.accountRepository.save(account);
    }

    public Optional<Account> putMoneyToAccountById(Long accountId, Double amount) {
        if (amount < 0) {
            return Optional.empty();
        }

        return accountRepository.findById(accountId).map(account -> {
            account.setBalance(account.getBalance() + amount);
            return accountRepository.save(account);
        });
    }


    public Account withdrawMoneyFromAccountById(Long accountId, Double amount) {
        return accountRepository.findById(accountId)
                .filter(account -> account.getBalance() >= amount)
                .map(account -> {
                    account.setBalance(account.getBalance() - amount);
                    return accountRepository.save(account);
                }).orElseThrow(() -> new NoSuchItemException("Item with Id not Found"));


    }

    public Account transferMoneyBetweenAccounts(Long fromId, Long toId, Double amount) {
        Optional<Account> from = accountRepository.findById(fromId);
        Optional<Account> to = accountRepository.findById(toId);

        Account accountFrom = from.filter(account -> account.getBalance() >= amount).orElseThrow(() -> new BalanceException("Not enough money!"));

        return to
                .map(t -> {
                    accountFrom.setBalance(accountFrom.getBalance() - amount);
                    t.setBalance(t.getBalance() + amount);

                    accountRepository.saveAll(List.of(accountFrom, t));

                    return t;
                }).orElseThrow(() -> new NoSuchItemException("From account or to Account does not exist!"));
    }
}
