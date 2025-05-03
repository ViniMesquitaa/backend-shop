package com.shop.cleaning.dev.controllers;

import com.shop.cleaning.dev.dtos.requestDtos.ClientRequestDTO;
import com.shop.cleaning.dev.dtos.responseDtos.ClientUpdateResponseDTO;
import com.shop.cleaning.dev.dtos.responseDtos.ClientResponseDTO;
import com.shop.cleaning.dev.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //registers clients
    @PostMapping("/registers")
    public ResponseEntity<ClientRequestDTO> createClient(@RequestBody @Valid ClientRequestDTO clientRequest) {
        var userId = clientService.createClient(clientRequest);

        return ResponseEntity.created(URI.create("/clients/" + userId.toString())).build();
    }

    //Get client by id
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDTO> getInfosClientById(@PathVariable UUID clientId) {
        ClientResponseDTO clientDTO = clientService.getClientInfoById(clientId);

        return ResponseEntity.ok(clientDTO);
    }

    //Get client all method
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        List<ClientResponseDTO> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }


    //delete client by id
    @DeleteMapping(value = "{clientId}")
    public ResponseEntity<Void> deleteClientById(@PathVariable UUID clientId) {
        clientService.deleteClientById(clientId);
        return ResponseEntity.noContent().build();
    }

    //edit infos clients
    @PutMapping("/edit/{clientId}")
    public ResponseEntity<ClientUpdateResponseDTO> editClientById(@PathVariable UUID clientId, @RequestBody @Valid ClientRequestDTO clientRequest) {
        ClientUpdateResponseDTO clientResponse = clientService.updateClients(clientId, clientRequest);

        return ResponseEntity.ok(clientResponse);
    }




}
