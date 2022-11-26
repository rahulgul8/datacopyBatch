package com.example.demo.launcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private JobService service;

    @GetMapping("/job/start")
    public Long startJob(Long id) throws Exception {
        return service.restartJob(id);
    }
}
