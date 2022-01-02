package com.belous.springdemo.controller;

import com.belous.springdemo.entity.Customer;
import com.belous.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    // need to inject the customer service
    @Autowired
    CustomerService customerService;

    // add mapping for GET /customers - get list of customers
    @GetMapping("/customers")
    public List<Customer> getCustomers() {

        return customerService.getCustomers();
    }

    // add mapping for GET /customers/{customerId} - get single customer
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {

        return customerService.getCustomer(customerId);
    }

    // add mapping for POST /customers - add new customer
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {

        // also just in case the pass an id in json ... set id to 0
        // this is force a save of new item ... instead of update

        theCustomer.setId(0);
        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }
}
