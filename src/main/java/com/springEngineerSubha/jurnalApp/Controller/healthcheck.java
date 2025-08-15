package com.springEngineerSubha.jurnalApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthcheck {
    @GetMapping("/health-check")
    public String healthckeck()
    {
        return "subhajit";
    }

}
