package tn.spring.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.User;

import java.net.URI;
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
    public ResponseEntity<User> addUser(@RequestBody User user ) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/add").toUriString());
        return ResponseEntity.created(uri).body(serviceUser.createUser(user));
    }


    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser = serviceUser.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/addRoleToUser/{username}/{rolename}")
    @ResponseBody
    public void addRoleToUser(@PathVariable String username , @PathVariable String rolename) {
        serviceUser.addRoleToUser(username, rolename);
    }






}
