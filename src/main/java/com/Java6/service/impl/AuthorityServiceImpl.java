package com.Java6.service.impl;

import java.util.List;

import com.Java6.repositoty.AccountRepository;
import com.Java6.repositoty.AuthorityRepository;
import com.Java6.entity.Account;
import com.Java6.entity.Authority;
import com.Java6.service.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Authority> findByAccount(Account account) {
        return authorityRepository.findByAccount(account);
    }

    @Override
    public List<Authority> findAuthoritiesOfAdmintrators() {
        List<Account> accounts = accountRepository.getAdministrators();
        return authorityRepository.authoritiesOf(accounts);
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority create(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public void delete(Long id) {
        authorityRepository.deleteById(id);
    }

}
