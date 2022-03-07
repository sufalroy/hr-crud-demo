package io.bitscope.example.hr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @RequestMapping
    public String getWelcomeString() {
        return "Welcome to HR Web Manager";
    }

}
