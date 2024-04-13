package com.company.sporty.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
@Scope("prototype")
public class Product {

    @Id
    private int id;
    private String name;
    private Float price;

    private String category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")				// Link to FK
    private List<Orders> listOfOrders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Orders> getListOfOrders() {
        return listOfOrders;
    }

    public void setListOfOrders(List<Orders> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", listOfOrders=" + listOfOrders +
                '}';
    }
}
