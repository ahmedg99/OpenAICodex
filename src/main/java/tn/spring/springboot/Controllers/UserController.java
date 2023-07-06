package tn.spring.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.User;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    IServiceUser serviceUser ;


    @GetMapping(value = "/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers() {
        return serviceUser.getAllUsers();
    }

    @GetMapping(value = "/getAllUsers/{offset}/{pageSize}")
    @ResponseBody
    public Page<User> getAllUsers(@PathVariable int offset , @PathVariable int pageSize) {
        return serviceUser.getAllUsers(offset, pageSize);
    }

    @PostMapping("/add")
    @ResponseBody
    public User addUser(@RequestBody User user ) {
        return serviceUser.createUser(user);
    }



}
