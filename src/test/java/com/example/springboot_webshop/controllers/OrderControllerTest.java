package com.example.springboot_webshop.controllers;

import com.example.springboot_webshop.models.Customer;
import com.example.springboot_webshop.models.Item;
import com.example.springboot_webshop.models.ShopOrder;
import com.example.springboot_webshop.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderRepository mockRepository;

    @BeforeEach
    public void init(){
        ShopOrder order1 = new ShopOrder(1L, "111", new Customer(2L,"Monica", "New-York"), new Item(1L,"111", "Serum"));
        ShopOrder order2 = new ShopOrder(2L, "222", new Customer(1L,"Phoebe", "New-York"), new Item(2L,"222", "Ring"));
        when(mockRepository.findById(1L)).thenReturn(Optional.of(order1));
        when(mockRepository.findAll()).thenReturn(Arrays.asList(order1, order2));
    }

    @Test
    void showAllOrdersTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/orders");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("[{\"id\":1,\"orderNumber\":\"111\",\"customer\":{\"id\":2,\"name\":\"Monica\"," +
               "\"adress\":\"New-York\"},\"item\":{\"id\":1,\"productNumber\":\"111\",\"name\":\"Serum\"}}," +
               "{\"id\":2,\"orderNumber\":\"222\",\"customer\":{\"id\":1,\"name\":\"Phoebe\",\"adress\":\"New-York\"}," +
               "\"item\":{\"id\":2,\"productNumber\":\"222\",\"name\":\"Ring\"}}]"
               , result.getResponse().getContentAsString());
    }

    @Test
    void getByCustomerIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/orders/:2");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("[{\"id\":1,\"orderNumber\":\"111\",\"customer\":{\"id\":2,\"name\":\"Monica\"," +
                        "\"adress\":\"New-York\"},\"item\":{\"id\":1,\"productNumber\":\"111\",\"name\":\"Serum\"}}]",
                result.getResponse().getContentAsString());
    }
    }
