package com.steven.demo.customer;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping(path="api")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/getcustomers", produces = "application/json")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping(path = "/putcustomer")
    public void putCustomer(@RequestBody Customer customer){
        customerService.putCustomers(customer);
    }

    @GetMapping(path = "/getcustomerbyref/{customerRef}", produces = "application/json")
    public List<Customer> getCustomersByCustomerRef(@PathVariable Long customerRef){
        return customerService.getCustomerByRef(customerRef);
    }
}
