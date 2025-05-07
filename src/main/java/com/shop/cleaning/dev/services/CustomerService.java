package com.shop.cleaning.dev.services;

import com.shop.cleaning.dev.dtos.request.CustomerRequestDto;
import com.shop.cleaning.dev.dtos.response.CustomerResponseDto;
import com.shop.cleaning.dev.dtos.response.CustomerUpdateResponseDto;
import com.shop.cleaning.dev.entities.Customer;
import com.shop.cleaning.dev.repositories.CustomerRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Transactional
    public UUID createCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer(UUID.randomUUID(), customerRequestDto.fullName(), customerRequestDto.address(), Instant.now(), null);
        var savedCustomer = customerRepo.save(customer);
        return savedCustomer.getId();
    }

    public CustomerResponseDto getCustomerById(UUID id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        return new CustomerResponseDto(customer.getId(), customer.getFullName(), customer.getAddress(), customer.getCreateTime(), customer.getUpdateTime());
    }

    public List<CustomerResponseDto> getCustomerAll() {
        return customerRepo.findAll().stream()
                .map(customer -> new CustomerResponseDto(
                        customer.getId(),
                        customer.getFullName(),
                        customer.getAddress(),
                        customer.getCreateTime(),
                        customer.getUpdateTime()
                ))
                .collect(Collectors.toList());
    }

    public CustomerUpdateResponseDto updateCustomer(UUID id, CustomerRequestDto customerRequestDto) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setFullName(customerRequestDto.fullName());
        customer.setAddress(customerRequestDto.address());
        var updatedCustomer = customerRepo.save(customer);

        return new CustomerUpdateResponseDto(updatedCustomer.getId(), updatedCustomer.getFullName(), updatedCustomer.getAddress());
    }

    @Transactional
    public void deleteCustomerById(UUID id) {
        if(!customerRepo.existsById(id)) {
            throw new EntityNotFoundException("Customer not found");
        }
        customerRepo.deleteById(id);
    }




}
