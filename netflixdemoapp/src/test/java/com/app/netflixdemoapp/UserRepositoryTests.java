package com.app.netflixdemoapp;

import com.app.netflixdemoapp.dao.CustomerRepository;
import com.app.netflixdemoapp.entity.Customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.annotation.Rollback;


import static org.assertj.core.api.Assertions.assertThat;




@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
 class UserRepositoryTests {

    @Autowired
    private CustomerRepository repo;



    @Autowired
    private TestEntityManager entityManager;

    @Test
     void testCreateuser(){
        Customer c = new Customer();
        c.setUserId(3);
        c.setEmail("abc@gmail.com");
        c.setFirstName("abc");
        c.setLastName("def");
        c.setPassword("pass");
        c.setRole("user");

       Customer savedUser= repo.save(c);
       Customer existUser = entityManager.find(Customer.class,savedUser.getUserId());
       assertThat(existUser.getEmail()).isEqualTo(c.getEmail());
    }

    @Test
     void testFindUserByEmail(){
        String email="yashwanth@gmail.com";
        Customer c = repo.findByEmail(email);
        assertThat(c).isNotNull();
    }


}
