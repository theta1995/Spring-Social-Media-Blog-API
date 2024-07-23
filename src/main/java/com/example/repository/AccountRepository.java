package com.example.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    /**
     * Returns whether an entity with the given username exists. 
     * @param username
     * @return true if an entity with the given username exists, false otherwise.
     */
    boolean existsByUsername(String username);

    /**
     * Find an account with the given account's username and password.
     * @param username
     * @param password
     * @return account entity if username and password are found.
     */
    Optional<Account> findByUsernameAndPassword(String username, String password);
}
