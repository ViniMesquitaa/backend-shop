package com.shop.cleaning.dev.controllers;

import com.shop.cleaning.dev.dtos.requestDtos.DtoClientRequest;
import com.shop.cleaning.dev.dtos.responseDtos.ClientUpdateDtoResponse;
import com.shop.cleaning.dev.dtos.responseDtos.DtoClientResponse;
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
@CrossOrigin(origins = "http://127.0.0.1:5500") // ← permite requisições desse frontend

public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //registers clients
    @PostMapping("/registers")
    public ResponseEntity<DtoClientRequest> createClient(@RequestBody @Valid DtoClientRequest clientRequest) {
        var userId = clientService.createClient(clientRequest);

        return ResponseEntity.created(URI.create("/clients/" + userId.toString())).build();
    }

    //Get client by id
    @GetMapping("/{clientId}")
    public ResponseEntity<DtoClientResponse> getInfosClientById(@PathVariable UUID clientId) {
        DtoClientResponse clientDTO = clientService.getClientInfoById(clientId);

        return ResponseEntity.ok(clientDTO);
    }

    //Get client all
    @GetMapping
    public ResponseEntity<List<DtoClientResponse>> getAllClients() {
        List<DtoClientResponse> clients = clientService.getAllClients();
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
    public ResponseEntity<ClientUpdateDtoResponse> editClientById(@PathVariable UUID clientId, @RequestBody @Valid DtoClientRequest clientRequest) {
        ClientUpdateDtoResponse clientResponse = clientService.updateClients(clientId, clientRequest);

        return ResponseEntity.ok(clientResponse);
    }




}
