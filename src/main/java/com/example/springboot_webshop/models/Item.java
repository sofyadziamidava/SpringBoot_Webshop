package com.example.springboot_webshop.models;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productNumber;
    private String name;

    public Item() {
    }

    public Item(String productNumber, String name) {
        this.productNumber = productNumber;
        this.name = name;
    }

    public Item(Long id, String productNumber, String name) {
        this.id = id;
        this.productNumber = productNumber;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", productNumber='" + productNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
