package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.RequirementsNotMetException;
import com.example.exception.UsernameAlreadyExistsException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    protected AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Register a new account entity.
     * @param newAccount 
     * @return account entity without account_id.
     * @throws UsernameAlreadyExistsException in case username existed
     * @throws RequirementsNotMetException in case username is blank or null, and password length is less than 4.
     */
    public Account registerAccount(Account newAccount) throws UsernameAlreadyExistsException, RequirementsNotMetException {
        if (newAccount.getUsername() == "" || newAccount.getUsername() == null || newAccount.getPassword().length() < 4) {
            throw new RequirementsNotMetException("Registration details not filled correctly."); 
        }
        if (accountRepository.existsByUsername(newAccount.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists, please login or use a different username."); 
        }
        Account createdAccount = accountRepository.save(newAccount);
        createdAccount.setAccountId(null);       
        return createdAccount;
    }
}
