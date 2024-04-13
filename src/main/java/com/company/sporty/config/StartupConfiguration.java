package com.company.sporty.config;

import com.company.sporty.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class StartupConfiguration {

    @Autowired
    LoginService loginService;

    @EventListener(ApplicationReadyEvent.class)
    public void startup() {

        // Create admin account if doesn't exist already
        loginService.createAdminAccount();

    }

}
