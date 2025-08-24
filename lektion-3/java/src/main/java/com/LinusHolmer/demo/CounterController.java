package com.LinusHolmer.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {

    @GetMapping("/test")
    public Counter counter(){

        return new Counter(1,2);
    }
}
