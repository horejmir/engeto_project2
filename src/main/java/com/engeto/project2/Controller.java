package com.engeto.project2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Controller {

    @GetMapping("/")
    public String showVatTable(){

        return "HelloWorld";
    }

}
