package com.mr1.task_manager_spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @GetMapping("/hello")
    String hello(){
        return "Hello this is Spring!";
    }
}
