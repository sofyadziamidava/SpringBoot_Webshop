package com.example.springboot_webshop.controllers;

import com.example.springboot_webshop.models.Customer;
import com.example.springboot_webshop.repositories.CustomerRepository;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("")
    public Iterable<Customer> showAllCustomers(){
        return customerRepository.findAll();
    }

    @RequestMapping("/:{id}")
    public Customer getById(@PathVariable Long id){
        if(customerRepository.findById(id).isPresent()){
            return customerRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @PostMapping("")
    public String addCustomer(@RequestBody Customer c){
        String name = c.getName();
        String adress = c.getAdress();

        if (name != null && adress != null){
            if(! name.isEmpty() && ! adress.isEmpty()){
                customerRepository.save(c);
                return "New Customer - " + c.getName() + " was added";
            }
        }
            return "Could not save customer. Make sure that customer name and adress are present";

    }

}
