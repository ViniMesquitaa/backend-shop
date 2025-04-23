package com.shop.cleaning.dev.services;

import com.shop.cleaning.dev.dtos.DtoClient;
import com.shop.cleaning.dev.entities.Client;
import com.shop.cleaning.dev.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
public class ClientService {

    private ClientRepository clientRepository;

    public DtoClient getClientInfo(UUID clientId) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return new DtoClient(client.getId(), client.getName(), client.getAddress());
    }

}

