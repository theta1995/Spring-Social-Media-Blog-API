package com.example.repository;

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
}
