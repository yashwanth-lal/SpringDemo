package com.app.netflixdemoapp.service;

import com.app.netflixdemoapp.dao.CustomerRepository;
import com.app.netflixdemoapp.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer c = repo.findByEmail(email);
        if(c==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(c);
    }

}
