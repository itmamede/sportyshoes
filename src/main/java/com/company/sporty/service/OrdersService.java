package com.company.sporty.service;

import com.company.sporty.entity.Orders;
import com.company.sporty.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    public String placeOrder(Orders orders) {
        orders.setLdt(LocalDateTime.now());

        ordersRepository.save(orders);
        return "Order placed successfully for product " + orders.getPid();
    }

}
