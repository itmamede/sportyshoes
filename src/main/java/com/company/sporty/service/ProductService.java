package com.company.sporty.service;

import com.company.sporty.entity.Product;
import com.company.sporty.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public String saveProduct(Product product) {

        Optional<Product> result = productRepository.findById(product.getId());

        if(result.isPresent()) {
            return "Product id must be unique";
        } else {
            productRepository.save(product);
            return "Product record stored successfully";
        }
    }

    public String removeProduct(int id) {

        System.out.println("product id is " + id);

        Optional<Product> result=productRepository.findById(id);

        if(result.isPresent()) {
            Product p = result.get();
            productRepository.delete(p);
            return "Product deleted successfully";
        } else {
            return "Product record not present";
        }
    }

    public Product searchProductById(int id) {

        Optional<Product> result = productRepository.findById(id);
        if(result.isPresent()) {
            Product product = result.get();
            return product;
        }else {
            return null;
        }
    }

    public String updateProduct(Product product) {

        Optional<Product> result = productRepository.findById(product.getId());

        if(result.isPresent()) {
            Product p = result.get();
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            productRepository.saveAndFlush(p);
            return "Product updated successfully";
        }else {
            return "Product record not present";
        }
    }

    public List<Object[]> getOrderDetails() {
        return productRepository.getOrderDetails();		// custom methods
    }

    public List<Object[]> getUserOrderDetails(String username) {
        return productRepository.getUserOrderDetails(username);		// custom methods
    }

}
