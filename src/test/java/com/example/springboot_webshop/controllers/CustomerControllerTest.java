package com.example.springboot_webshop.controllers;

import com.example.springboot_webshop.models.Customer;
import com.example.springboot_webshop.models.Item;
import com.example.springboot_webshop.repositories.CustomerRepository;
import com.example.springboot_webshop.repositories.ItemRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository mockRepository;

    @BeforeEach
    public void init(){
        Customer customer1 = new Customer(1L, "Monica", "New-York");
        Customer customer2 = new Customer(2L, "Rachel", "New-York");
        when(mockRepository.findById(2L)).thenReturn(Optional.of(customer2));
        when(mockRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));
    }

    @Test
    void showAllCustomersTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/customers");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("[{\"id\":1,\"name\":\"Monica\",\"adress\":\"New-York\"}," +
                "{\"id\":2,\"name\":\"Rachel\",\"adress\":\"New-York\"}]", result.getResponse().getContentAsString());
    }

    @Test
    void getByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/customers/:2");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"id\":2,\"name\":\"Rachel\",\"adress\":\"New-York\"}",
                result.getResponse().getContentAsString());
    }

    @Test
    void addCustomerTest() throws Exception {
        Customer customer = new Customer("Phoebe", "New-York");
        RequestBuilder request = MockMvcRequestBuilders.post("/customers")
                .contentType(MediaType.APPLICATION_JSON).content(toJson(customer));
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("New Customer - Phoebe was added", result.getResponse().getContentAsString());
    }

    private String toJson(Customer customer) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(customer);
        return json;
    }
}