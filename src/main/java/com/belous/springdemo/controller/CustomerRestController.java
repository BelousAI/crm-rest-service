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

        Customer theCustomer = customerService.getCustomer(customerId);

        if (theCustomer == null) {
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        return theCustomer;
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

    // add mapping for PUT /customers - update existing customer
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer) {

        customerService.saveCustomer(theCustomer);
        return theCustomer;
    }

    // add mapping for DELETE /customers/{customerId} - delete customer by id
    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {

        Customer theCustomer = customerService.getCustomer(customerId);

        if (theCustomer == null) {
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        customerService.deleteCustomer(customerId);
        return "Deleted customer id - " + customerId;
    }
}
