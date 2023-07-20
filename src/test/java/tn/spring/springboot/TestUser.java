package tn.spring.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.User;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestUser {

    @Autowired
    IServiceUser iServiceUser ;

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users =  iServiceUser.getAllUsers() ;
        Assertions.assertNotNull(users);
    }

    @Test
    public void addUser() throws Exception {
         List<String> roles = new ArrayList<>() ;
        roles.add("ROLE_USER") ;
        User user1 = iServiceUser.createUser("John Doe", "johndoe", "password123", "johndoe@example.com",roles ,null);
        Assertions.assertNotNull(user1);
    }







}