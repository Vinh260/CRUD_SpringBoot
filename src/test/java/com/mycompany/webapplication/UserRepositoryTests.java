package com.mycompany.webapplication;


import com.mycompany.webapplication.user.User;
import com.mycompany.webapplication.user.UserRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;


    //test add new user
    @Test
    public void testAddNew() {

       User user = new User();

       user.setEmail("vinhkaka@gmail.com");
       user.setPassword("123321");
       user.setFirstName("Vinh");
       user.setLastName("Kaka");

       User saveUser = repo.save(user);

        Assertions.assertThat(saveUser).isNotNull();
        Assertions.assertThat(saveUser.getId()).isGreaterThan(0);


    }


    //test get all user
    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }

    }

    //test update
    @Test
    public void testUpdateEmail() {
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setEmail("vinhkaku@gmail.com");
        user.setPassword("vinhdayne11233");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getEmail()).isEqualTo("vinhkaku@gmail.com");
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("vinhdayne11233");

    }

    //test get
    @Test
    public void testGet() {
        Integer UserId = 2;
        Optional<User> optionalUser = repo.findById(UserId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());


    }
    //test delete
    @Test
    public void testDelete() {
        Integer UserId = 3;
        repo.deleteById(UserId);
        Optional<User> optionalUser = repo.findById(UserId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }




}
