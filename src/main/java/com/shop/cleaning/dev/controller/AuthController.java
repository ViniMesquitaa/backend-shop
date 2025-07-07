package com.shop.cleaning.dev.controller;

import com.shop.cleaning.dev.dtos.request.CustomerLoginRequestDTO;
import com.shop.cleaning.dev.dtos.request.CustomerRequestDto;
import com.shop.cleaning.dev.dtos.response.CustomerLoginResponseDTO;
import com.shop.cleaning.dev.dtos.response.CustomerRegisterDto;
import com.shop.cleaning.dev.entities.Customer;
import com.shop.cleaning.dev.infra.security.TokenService;
import com.shop.cleaning.dev.repositories.CustomerRepo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final CustomerRepo customerRepo;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    AuthController(CustomerRepo customerRepo, PasswordEncoder passwordEncoder) {
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = new TokenService();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid CustomerLoginRequestDTO customerLoginRequestDTO) {
        Customer customer = customerRepo.findByUserName(customerLoginRequestDTO.username())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (passwordEncoder.matches(customerLoginRequestDTO.password(), customer.getPassword())) {
            String token = tokenService.generateToken(customer);
            CustomerLoginResponseDTO response = new CustomerLoginResponseDTO(
                    customer.getId(),
                    customer.getFullName(),
                    customer.getUserName(),
                    customer.getPhoneNumber(),
                    token
            );
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario ou senha inválidos");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid CustomerRequestDto body) {
        Optional<Customer> existingCustomer = customerRepo.findByUserName(body.userName());

        if (existingCustomer.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username já registrado");
        }

        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setFullName(body.fullName());
        customer.setAddress(body.address());
        customer.setPhoneNumber(body.phoneNumber());
        customer.setUserName(body.userName());
        customer.setPassword(passwordEncoder.encode(body.password()));

        customerRepo.save(customer);

        String token = tokenService.generateToken(customer);

        CustomerRegisterDto response = new CustomerRegisterDto(
                customer.getId(),
                customer.getFullName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getUserName(),
                customer.getPassword(),
                customer.getCreateTime(),
                customer.getUpdateTime(),
                token
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}