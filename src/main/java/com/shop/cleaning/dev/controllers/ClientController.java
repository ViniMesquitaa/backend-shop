package com.shop.cleaning.dev.controllers;

import com.shop.cleaning.dev.dtos.DtoClient;
import com.shop.cleaning.dev.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<DtoClient> getClientInfo(@PathVariable UUID clientId) {
        DtoClient clientDTO = clientService.getClientInfo(clientId);

        return ResponseEntity.ok(clientDTO);
    }

}
