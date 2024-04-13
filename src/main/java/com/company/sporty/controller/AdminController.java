package com.company.sporty.controller;

import com.company.sporty.entity.Login;
import com.company.sporty.entity.LoginRegistry;
import com.company.sporty.entity.Product;
import com.company.sporty.service.LoginRegistryService;
import com.company.sporty.service.LoginService;
import com.company.sporty.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    ProductService productService;

    @Autowired
    LoginRegistryService loginRegistryService;

    @Autowired
    LoginService loginService;

    @GetMapping(value = "")
    public String open(Model model, Product product) {

        String name="Store Product";

        List<Product> listOfProduct = productService.getAllProducts();
        List<Object[]> orderdetails = productService.getOrderDetails();
        List<LoginRegistry> logdetails = loginRegistryService.getAllLoggingInfo();
        List<Login> users = loginService.getAllUsers();

        product.setId(0);
        product.setName("");
        product.setCategory("");
        product.setPrice((float) 0.0);

        model.addAttribute("products", listOfProduct);
        model.addAttribute("buttonValue", name);
        model.addAttribute("product", product);
        model.addAttribute("orderdetails", orderdetails);
        model.addAttribute("logdetails", logdetails);
        model.addAttribute("users", users);

        System.out.println(listOfProduct);
        return "admin";
    }
}

