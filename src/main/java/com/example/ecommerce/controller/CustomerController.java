package com.example.ecommerce.controller;

import com.example.ecommerce.model.Customer;
import com.example.ecommerce.response.GetRequestResponse;
import com.example.ecommerce.response.RequestResponse;
import com.example.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public GetRequestResponse<Customer> customerList(){
        return customerService.customerList();
    }

    @PostMapping("/create")
    public ResponseEntity<RequestResponse> customerCreate(@RequestBody Customer customer){
        return customerService.customerCreate(customer);
    }
}
