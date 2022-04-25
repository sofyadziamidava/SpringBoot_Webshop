package com.example.springboot_webshop.repositories;

import com.example.springboot_webshop.models.ShopOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ShopOrder, Long> {
}
