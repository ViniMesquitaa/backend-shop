package com.shop.cleaning.dev.services;

import com.shop.cleaning.dev.dtos.DtoClientRequest;
import com.shop.cleaning.dev.dtos.DtoClientResponse;
import com.shop.cleaning.dev.dtos.DtoItemCartResponse;
import com.shop.cleaning.dev.dtos.DtoProductResponse;
import com.shop.cleaning.dev.entities.Address;
import com.shop.cleaning.dev.entities.Client;
import com.shop.cleaning.dev.repositories.ClientRepository;
import com.shop.cleaning.dev.repositories.ItemCartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ItemCartRepository itemCartRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, ItemCartRepository itemCartRepository) {
        this.clientRepository = clientRepository;
        this.itemCartRepository = itemCartRepository;
    }


    //criar o client
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

    public List<DtoItemCartResponse> getProductsCartByClient(UUID clientId) {
        return itemCartRepository.findByClientId(clientId).stream()
                .map(itemCart -> new DtoItemCartResponse(
                        itemCart.getId(),
                        new DtoProductResponse(
                                itemCart.getProduct().getId(),
                                itemCart.getProduct().getImg(),
                                itemCart.getProduct().getName(),
                                itemCart.getProduct().getDescription(),
                                itemCart.getProduct().getPrice(),
                                itemCart.getProduct().getAtivo()
                        ),
                        itemCart.getItemQuantity(),
                        itemCart.getPrecoUnitario(),
                        itemCart.getTotalItem(),
                        itemCart.getAtivo()
                ))
                .collect(Collectors.toList());
    }



    //deletar cliente pelo id
    public void deleteClientById(UUID clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new EntityNotFoundException("Client not found or no exists");
        }
        clientRepository.deleteById(clientId);
    }


}

