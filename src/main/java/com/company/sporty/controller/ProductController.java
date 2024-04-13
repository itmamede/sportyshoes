package com.company.sporty.controller;

import com.company.sporty.entity.Login;
import com.company.sporty.entity.LoginRegistry;
import com.company.sporty.entity.Orders;
import com.company.sporty.entity.Product;
import com.company.sporty.service.LoginRegistryService;
import com.company.sporty.service.LoginService;
import com.company.sporty.service.OrdersService;
import com.company.sporty.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    LoginRegistryService loginRegistryService;

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public String addProductDetails(Model model, Product product,HttpServletRequest req) {
        String b1 = req.getParameter("b1");
        String result="";
        String name="";
        if(b1.equals("Store Product")) {
            result = productService.saveProduct(product);
        }else {
            result = productService.updateProduct(product);
        }
        name = "Store Product";
        product.setId(0);
        product.setName("");
        product.setCategory("");
        product.setPrice((float) 0.0);
        model.addAttribute("product", product);
        List<Product> listOfProduct = productService.getAllProducts();
        List<Object[]> orderdetails = productService.getOrderDetails();
        List<LoginRegistry> logdetails = loginRegistryService.getAllLoggingInfo();
        List<Login> users = loginService.getAllUsers();

        model.addAttribute("orderdetails", orderdetails);
        model.addAttribute("products", listOfProduct);
        model.addAttribute("msg", result);
        model.addAttribute("buttonValue", name);
        model.addAttribute("logdetails", logdetails);
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping(value = "/deleteProduct",method = RequestMethod.GET)
    public String deleteProductById(Model model, Product product,HttpServletRequest req) {
        int pid = Integer.parseInt(req.getParameter("id"));
        System.out.println("pid is "+pid);
        String name = "Store Product";
        String result = productService.removeProduct(pid);
        List<Product> listOfProduct = productService.getAllProducts();
        List<LoginRegistry> logdetails = loginRegistryService.getAllLoggingInfo();
        List<Login> users = loginService.getAllUsers();

        product.setId(0);
        product.setName("");
        product.setCategory("");
        product.setPrice((float) 0.0);

        model.addAttribute("products", listOfProduct);
        model.addAttribute("product", product);
        model.addAttribute("msg", result);
        model.addAttribute("buttonValue", name);
        List<Object[]> orderdetails = productService.getOrderDetails();
        model.addAttribute("orderdetails", orderdetails);
        model.addAttribute("logdetails", logdetails);
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping(value = "/updateProduct",method = RequestMethod.GET)
    public String searchProductById(Model model, HttpServletRequest req) {
        int pid = Integer.parseInt(req.getParameter("id"));
        String name="Update Product";
        Product product = productService.searchProductById(pid);
        List<Product> listOfProduct = productService.getAllProducts();

        model.addAttribute("products", listOfProduct);
        model.addAttribute("product", product);
        model.addAttribute("buttonValue", name);
        List<Object[]> orderdetails = productService.getOrderDetails();
        model.addAttribute("orderdetails", orderdetails);

        return "admin";
    }

    @RequestMapping(value = "/orderPlace",method = RequestMethod.GET)
    public String placeOrder(Model model, HttpServletRequest req, Orders order, Product product) {
        int pid = Integer.parseInt(req.getParameter("id"));
        order.setPid(pid);

        UserDetails userContext = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userContext.getUsername();
        order.setUsername(username);

        String name="Store Product";
        String result = ordersService.placeOrder(order);
        List<Product> listOfProduct = productService.getAllProducts();

        product.setId(0);
        product.setName("");
        product.setCategory("");
        product.setPrice((float) 0.0);

        model.addAttribute("products", listOfProduct);
        model.addAttribute("product", product);
        model.addAttribute("msg", result);
        model.addAttribute("buttonValue", name);
        //model.addAttribute("msg", result);
        List<Object[]> orderdetails = productService.getUserOrderDetails(username);
        model.addAttribute("orderdetails", orderdetails);
        return "user";
    }
}