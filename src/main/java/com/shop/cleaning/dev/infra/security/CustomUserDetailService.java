package com.shop.cleaning.dev.infra.security;


import com.shop.cleaning.dev.entities.Customer;
import com.shop.cleaning.dev.repositories.CustomerRepo;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomUserDetailService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = this.customerRepo.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return new org.springframework.security.core.userdetails.User(
                customer.getUserName(),
                customer.getPassword(),
                new ArrayList<>()
        );
    }
}