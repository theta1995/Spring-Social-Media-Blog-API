package com.example.service;

import org.h2.security.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.example.entity.Account;
import com.example.exception.RequirementsNotMetException;
import com.example.exception.UnauthorizedException;
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
     * @return account entity without the generated account_id.
     * @throws UsernameAlreadyExistsException in case username existed
     * @throws RequirementsNotMetException in case username is blank or null, and password length is less than 4.
     */
    public Account registerAccount(Account newAccount) {

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

    /**
     * Login using account's username and password.
     * @param account
     * @return account entity with account_id
     * @throws UnauthorizedException in case the password doesn't match with the corresponding username's password.
     */
    public Account loginAccount(Account account) {
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword())
            .orElseThrow(() -> new UnauthorizedException("Password or username doesn't match."));
    }
}
