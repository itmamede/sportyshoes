package com.company.sporty.controller;

import com.company.sporty.entity.Product;
import com.company.sporty.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "")
    public String open(Model model, Product product) {

        String name="Store Product";

        UserDetails userContext = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userContext.getUsername();

        List<Product> listOfProduct = productService.getAllProducts();
        List<Object[]> orderdetails = productService.getUserOrderDetails(username);

        product.setId(0);
        product.setName("");
        product.setCategory("");
        product.setPrice((float) 0.0);

        model.addAttribute("products", listOfProduct);
        model.addAttribute("buttonValue", name);
        model.addAttribute("product", product);
        model.addAttribute("orderdetails", orderdetails);

        System.out.println(listOfProduct);
        return "user";
    }
}
