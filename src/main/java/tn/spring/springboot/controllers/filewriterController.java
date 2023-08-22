package tn.spring.springboot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.springboot.services.Implementation.FileLocationService;

import java.io.IOException;

@RequestMapping("/fileWriter")
@RestController
@Slf4j
public class filewriterController {


    @Autowired
    FileLocationService iFileLocationService ;


    @GetMapping(value = "/getAllUsers")
    @ResponseBody
    public void  generaterclass(String content , String filename) throws IOException {
         iFileLocationService.writeStringToFile(content , filename) ;
    }

    }
