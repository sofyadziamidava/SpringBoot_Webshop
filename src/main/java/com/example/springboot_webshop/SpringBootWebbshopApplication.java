package com.example.springboot_webshop;

import com.example.springboot_webshop.models.Customer;
import com.example.springboot_webshop.models.Item;
import com.example.springboot_webshop.models.ShopOrder;
import com.example.springboot_webshop.repositories.CustomerRepository;
import com.example.springboot_webshop.repositories.ItemRepository;
import com.example.springboot_webshop.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootWebbshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebbshopApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository customerRepository, OrderRepository orderRepository) {
        return (args) -> {
            Customer customer = new Customer(1L, "Evelyn Hugo", "Manhattan, New York");
            Customer customer1 = new Customer(2L, "Celia St James", "Los Angeles, California");
            customerRepository.save(customer);
            Item item = new Item(3L, "111", "Green dress");
            ShopOrder o = new ShopOrder(4L, "369", customer1, item);
            orderRepository.save(o);
        };
    }
}
