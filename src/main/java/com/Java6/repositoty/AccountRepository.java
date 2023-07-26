package com.Java6.repositoty;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Java6.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

    @Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.name IN ('DIRE', 'STAF') ")
    List<Account> getAdministrators();

    Optional<Account> findByUsername(String name);
	
}
