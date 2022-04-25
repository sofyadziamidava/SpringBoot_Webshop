package com.example.springboot_webshop.repositories;

import com.example.springboot_webshop.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends  CrudRepository<Customer, Long> {
}
