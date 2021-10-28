package com.example.restdemo.rest;

import com.example.restdemo.model.CustomerDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    private static final List<CustomerDto> customers = new ArrayList<>();

    static {
        customers.add(new CustomerDto(1, "John Smith"));
        customers.add(new CustomerDto(2, "Jack Davis"));
    }

    @GetMapping("customers")
    public List<CustomerDto> getCustomers() {
        return customers;
    }

    @PostMapping("customers")
    public CustomerDto createCustomer(@RequestBody CustomerDto newCustomer) {
        customers.add(newCustomer);
        return newCustomer;
    }

    @PutMapping("customers/{id}")
    public CustomerDto updateCustomer(@PathVariable int id, @RequestBody CustomerDto updatedCustomer) {
        for (CustomerDto customer : customers) {
            if (customer.getId() == id) {
                customer.setFullName(updatedCustomer.getFullName());
                break;
            }
        }
        return updatedCustomer;
    }

    @DeleteMapping("customers/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customers.removeIf(customerDto -> customerDto.getId() == id);
    }
}
