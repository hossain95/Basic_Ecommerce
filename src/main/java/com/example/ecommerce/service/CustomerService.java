package com.example.ecommerce.service;

import com.example.ecommerce.model.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.response.GetRequestResponse;
import com.example.ecommerce.response.RequestResponse;
import com.example.ecommerce.response.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public GetRequestResponse<Customer> customerList(){
        return new GetRequestResponse<>(Status.OK, customerRepository.findAll());
    }

    public ResponseEntity<RequestResponse> customerCreate(Customer customer){

        Customer cust = customerRepository.findCustomerByEmail(customer.getEmail());
        if (Objects.nonNull(cust)){
            return new ResponseEntity<>(new RequestResponse("customer already exist!", false), HttpStatus.BAD_REQUEST);
        }
        customerRepository.save(customer);
        return new ResponseEntity<>(new RequestResponse("customer created successfully", true), HttpStatus.CREATED);
    }
}
