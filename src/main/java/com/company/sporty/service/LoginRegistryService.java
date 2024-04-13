package com.company.sporty.service;

import com.company.sporty.entity.LoginRegistry;
import com.company.sporty.repository.LoginRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginRegistryService {

    @Autowired
    LoginRegistryRepository loginRegistryRepository;

    public List<LoginRegistry> getAllLoggingInfo() {
        return loginRegistryRepository.findAll();
    }

}
