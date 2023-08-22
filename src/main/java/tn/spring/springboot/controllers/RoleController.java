package tn.spring.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.spring.springboot.services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.Role;

import java.net.URI;

@RequestMapping("/role")
@RestController
public class RoleController {

    @Autowired
    IServiceUser serviceUser ;


    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Role> addRole(@RequestBody Role role ) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/add").toUriString());
        return ResponseEntity.created(uri).body(serviceUser.saveRole(role));
    }



}
