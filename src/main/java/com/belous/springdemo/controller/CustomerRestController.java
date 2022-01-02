package com.belous.springdemo.controller;

import com.belous.springdemo.entity.Customer;
import com.belous.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    // need to inject the customer service
    @Autowired
    CustomerService customerService;

    // add mapping for GET /customers
    @GetMapping("/customers")
    public List<Customer> getCustomers() {

        return customerService.getCustomers();
    }

    // add mapping for GET /customers/{customerId}
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {

        return customerService.getCustomer(customerId);
    }
}
