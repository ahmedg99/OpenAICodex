package tn.spring.springboot.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
public class HelloWorldController
{
    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok().body("Hello World");
    }
}