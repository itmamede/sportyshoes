package com.company.sporty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//Remover o radio do signup

// Mensagens de erro no login
// bootstrap no login
//... não da

// produzir relatório de vendas??
//se houver tempo

//The admin should be able to change his password if he wants, he should be able to:
//        ● Manage the products in the store including categorizing them
//	● Browse the list of users who have signed up and be able to search users
//● See purchase reports filtered by date and category

@Controller
@RequestMapping("toDelete")
//@RequestMapping("admin2")
public class TODO {

    @GetMapping(value = "")
    public String open() {
        return "admin";
    }
}

