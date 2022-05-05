package com.kamiroo.todomanager.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministrationRestController {

    @Autowired
    private Environment environment;

    @Value("${app.version}")
    private String version;

    @GetMapping("/version")
    public String getVerison() {
        return version;
    }

    @GetMapping("/active-profile")
    public String[] getActiveProfile() {
        return this.environment.getActiveProfiles();
    }

}
