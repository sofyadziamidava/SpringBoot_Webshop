package com.example.springboot_webshop.controllers;

import com.example.springboot_webshop.models.Customer;
import com.example.springboot_webshop.models.Item;
import com.example.springboot_webshop.models.ShopOrder;
import com.example.springboot_webshop.repositories.CustomerRepository;
import com.example.springboot_webshop.repositories.ItemRepository;
import com.example.springboot_webshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("")
    public Iterable<ShopOrder> showAllOrders(){
        return orderRepository.findAll();
    }

    @RequestMapping("/:{customerId}")
    public List<ShopOrder> getByCustomerId(@PathVariable Long customerId){
        List<ShopOrder> ordersToReturn = new ArrayList<>();
        Iterable<ShopOrder> orders = orderRepository.findAll();
        for (ShopOrder order: orders) {
            if(Objects.equals(order.getCustomer().getId(), customerId)){
                ordersToReturn.add(order);
            }
        }
        return ordersToReturn;
    }
}
