package tn.spring.springboot.Controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.spring.springboot.entities.Device;

import java.net.URI;

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