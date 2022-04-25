package com.example.springboot_webshop.controllers;

import com.example.springboot_webshop.models.Item;
import com.example.springboot_webshop.models.ShopOrder;
import com.example.springboot_webshop.repositories.CustomerRepository;
import com.example.springboot_webshop.repositories.ItemRepository;
import com.example.springboot_webshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;


    @RequestMapping("")
    public Iterable<Item> showAllItems(){
        return itemRepository.findAll();
    }

    @RequestMapping("/:{id}")
    public Item getById(@PathVariable Long id){
        if(itemRepository.findById(id).isPresent()){
            return itemRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @PostMapping("")
    public String addItem(@RequestBody Item i){
        String name = i.getName();
        String productNumber = i.getProductNumber();
        if(name != null && productNumber != null){
            if(!name.isEmpty() && !productNumber.isEmpty()){
                itemRepository.save(i);
                return i.getName() + " is added to items";
            }
        }
        return "Could not add item, please check if name and product number are present";
    }

    @PostMapping("/buy")
    public String addOrder(@RequestBody Map<String, Long> json){
        ShopOrder order = new ShopOrder();
        order.setOrderNumber(generateOrderNumber());
        Long customerId = json.get("customer");
        Long itemId = json.get("item");

        if(customerRepository.findById(customerId).isPresent()){
            order.setCustomer(customerRepository.findById(customerId).get());
        }

        if(itemRepository.findById(itemId).isPresent()){
            order.setItem(itemRepository.findById(itemId).get());
        }

        if(order.getCustomer() != null && order.getItem() != null) {
            orderRepository.save(order);
            return "Order is added";
        } else{
            return "Could not add order. Make sure that customer id and item id are present and correct.";
        }
    }

    public String generateOrderNumber(){
        Random r = new Random();
        int low = 100;
        int high = 999;
        int result = r.nextInt(high-low) + low;
        return String.valueOf(result);
    }
}
