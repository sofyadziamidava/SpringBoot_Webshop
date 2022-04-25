package com.example.springboot_webshop.controllers;

import com.example.springboot_webshop.models.Customer;
import com.example.springboot_webshop.models.Item;
import com.example.springboot_webshop.repositories.CustomerRepository;
import com.example.springboot_webshop.repositories.ItemRepository;
import com.example.springboot_webshop.repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemRepository mockRepository;
    @MockBean
    private CustomerRepository mockCustomerRepository;
    @MockBean
    private OrderRepository mockOrderRepository;

    @BeforeEach
    public void init(){
        Item item1 = new Item(1L, "111", "Ring");
        Item item2 = new Item(2L, "222", "Sunglasses");
        Customer customer1 = new Customer(1L, "Harry", "England");
        when(mockRepository.findById(1L)).thenReturn(Optional.of(item1));
        when(mockRepository.findById(2L)).thenReturn(Optional.of(item2));
        when(mockRepository.findAll()).thenReturn(Arrays.asList(item1, item2));
        when(mockCustomerRepository.findById(1L)).thenReturn(Optional.of(customer1));

    }

    @Test
    void showAllItemsTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/items");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("[{\"id\":1,\"productNumber\":\"111\",\"name\":\"Ring\"}," +
                "{\"id\":2,\"productNumber\":\"222\",\"name\":\"Sunglasses\"}]",
                result.getResponse().getContentAsString());
    }

    @Test
    void getByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/items/:1");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"id\":1,\"productNumber\":\"111\",\"name\":\"Ring\"}",
                result.getResponse().getContentAsString());
    }

    @Test
    void addItemTest() throws Exception {
       Item item = new Item("333", "skirt");
        RequestBuilder request = MockMvcRequestBuilders.post("/items")
                .contentType(MediaType.APPLICATION_JSON).content(toJson(item));
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("skirt is added to items", result.getResponse().getContentAsString());
    }

    @Test void addOrderTest() throws Exception {
        Map json = Map.of("customer", "1", "item", "2");
        RequestBuilder request = MockMvcRequestBuilders.post("/items/buy")
                .contentType(MediaType.APPLICATION_JSON).content(toJson(json));
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("Order is added", result.getResponse().getContentAsString());
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);
        return json;
    }
}