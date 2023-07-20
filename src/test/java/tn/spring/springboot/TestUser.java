package tn.spring.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import tn.spring.springboot.Services.Implementation.UserServiceImp;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.User;
import tn.spring.springboot.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TestUser {
    @Mock
    UserRepository userRepository;


    @InjectMocks
    UserServiceImp iServiceUser;

    @Autowired
    IServiceUser serviceUser ;

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users =  iServiceUser.getAllUsers() ;
        Assertions.assertNotNull(users);
    }

    @Test
    public void addUser() throws Exception {
         List<String> roles = new ArrayList<>() ;
        roles.add("ROLE_USER") ;
        User user1 = serviceUser.createUser("John Doe", "johndoe", "password123", "johndoe@example.com",roles ,null);
        Assertions.assertNotNull(user1);
    }


    @Test
    public void testGetAllUsersPagination() {
        List<User> userList = userRepository.findAll();
        Page<User> fakePage = new PageImpl<>(userList);
        int offset = 0;
        int pageSize = 10;
        Mockito.when(userRepository.findAll(PageRequest.of(offset, pageSize))).thenReturn(fakePage);
        Page<User> result = iServiceUser.getAllUsers(offset, pageSize);
        System.out.println("fakepage : " + fakePage.getContent().size());
        System.out.println("result : " + result.getContent().size());
        // Assertions
        assertEquals(fakePage.getContent(),result.getContent());

     }


     @Test
     @Order(4)
     @Rollback(value = false)
     public void updateUserTest(){
         List<String> roles = new ArrayList<>() ;
         roles.add("ROLE_USER") ;
         User user1 = serviceUser.createUser("John Doe", "johndoe", "password123", "johndoe@example.com",roles ,null);
         User user = userRepository.findUserByUsername("johndoe");
         if(user!=null) {
             user.setEmail("ram@gmail.com");
             User UserUpdated = iServiceUser.updateUser(1, user);
             Assertions.assertEquals(UserUpdated.getEmail(), ("ram@gmail.com"));
         }
     }




}