package com.shop.cleaning.dev.services;

import com.shop.cleaning.dev.dtos.requestDtos.DtoClientRequest;
import com.shop.cleaning.dev.dtos.responseDtos.ClientUpdateDtoResponse;
import com.shop.cleaning.dev.dtos.responseDtos.DtoClientResponse;
import com.shop.cleaning.dev.entities.Client;
import com.shop.cleaning.dev.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

    }


    //criar o client
    @Transactional
    public UUID createClient(DtoClientRequest request) {
        Client client = new Client(UUID.randomUUID(), request.name(), request.address(), Instant.now(), null);
        var userSaved = clientRepository.save(client);
        return userSaved.getId();
    }

    //Buscar o cliente pelo o id
    public DtoClientResponse getClientInfoById(UUID clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return new DtoClientResponse(client.getId(), client.getName(), client.getAddress(), client.getCreationTimesStamp(), client.getUpdateTimesStamp());
    }

    //Buscar todos os clientes "cadastrados"
    public List<DtoClientResponse> getAllClients() {
        return clientRepository.findAll().stream()
                .map(client -> new DtoClientResponse(client.getId(), client.getName(), client.getAddress(), client.getCreationTimesStamp(), client.getUpdateTimesStamp()))
                .collect(Collectors.toList());
    }
    public ClientUpdateDtoResponse updateClients(UUID uuid, DtoClientRequest request) {
        Client client = clientRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Client not found"));

        client.setName(request.name());
        client.setAddress(request.address());
        Client updatedClient = clientRepository.save(client);

        return new ClientUpdateDtoResponse(updatedClient.getId(), updatedClient.getName(), updatedClient.getAddress());
    }

    //deletar cliente pelo id
    @Transactional
    public void deleteClientById(UUID clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new EntityNotFoundException("Client not found or no exists");
        }
        clientRepository.deleteById(clientId);
    }


}

