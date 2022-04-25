package com.example.springboot_webshop.repositories;

import com.example.springboot_webshop.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
