package com.shop.cleaning.dev.controller;

import com.shop.cleaning.dev.dtos.request.CustomerRequestDto;
import com.shop.cleaning.dev.dtos.response.CustomerResponseDto;
import com.shop.cleaning.dev.dtos.response.CustomerUpdateResponseDto;
import com.shop.cleaning.dev.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:5173")

public class CustomerController {
    CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerRequestDto> registerCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        var customerId = customerService.createCustomer(customerRequestDto);
        return ResponseEntity.created(URI.create("/customer" + customerId.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable UUID id) {
        CustomerResponseDto customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        var customerAll = customerService.getCustomerAll();
        return ResponseEntity.ok(customerAll);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerUpdateResponseDto> updateCustomer(@PathVariable UUID id, @RequestBody @Valid CustomerRequestDto customerRequestDto) {
        var customer = customerService.updateCustomer(id, customerRequestDto);
        return ResponseEntity.ok(customer);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable UUID id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<Void> deleteAllCustomers() {
        customerService.deleteCustomerAll();
        return ResponseEntity.noContent().build();
    }





}
