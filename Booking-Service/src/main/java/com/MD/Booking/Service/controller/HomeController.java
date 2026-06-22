package com.MD.Booking.Service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String HomeControllerHandler(){
        return  "Booking microservice is up and running";
    }
}
