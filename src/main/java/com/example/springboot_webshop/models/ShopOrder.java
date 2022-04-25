package com.example.springboot_webshop.models;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Item item;

    public ShopOrder() {
    }

    public ShopOrder(String orderNumber, Customer customer, Item item) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.item = item;
    }

    public ShopOrder(Long id, String orderNumber, Customer customer, Item item) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ShopOrder{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", customer=" + customer +
                ", item=" + item +
                '}';
    }
}
