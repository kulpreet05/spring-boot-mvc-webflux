package com.ks.springbootmvcwebflux.controller;

import com.ks.springbootmvcwebflux.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/monitor")
public class APIController {
    @Autowired
    private APIService apiService;
    @GetMapping("/job/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String monitorJob(@PathVariable("id") long id) {
        return apiService.monitorJob(id);
    }
}
