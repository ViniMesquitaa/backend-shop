package com.shop.cleaning.dev.services;

import com.shop.cleaning.dev.dtos.requestDtos.ClientRequestDTO;
import com.shop.cleaning.dev.dtos.responseDtos.ClientUpdateResponseDTO;
import com.shop.cleaning.dev.dtos.responseDtos.ClientResponseDTO;
import com.shop.cleaning.dev.entities.Cart;
import com.shop.cleaning.dev.entities.Client;
import com.shop.cleaning.dev.repositories.CartRepository;
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
    private final CartRepository cartRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, CartRepository cartRepository) {
        this.clientRepository = clientRepository;
        this.cartRepository = cartRepository;
    }

    // Criar o client e também o cart associado
    @Transactional
    public UUID createClient(ClientRequestDTO request) {
        Client client = new Client(UUID.randomUUID(), request.name(), request.address(), Instant.now(), null);
        var savedClient = clientRepository.save(client);

        // Cria o carrinho vinculado ao cliente
        Cart cart = new Cart();
        cart.setClient(savedClient);
        cartRepository.save(cart);

        return savedClient.getId();
    }

    // Buscar o cliente pelo ID e retornar também o ID do cart
    public ClientResponseDTO getClientInfoById(UUID clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Busca o carrinho do cliente
        Cart cart = cartRepository.findByClientId(client.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found for this client"));

        return new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getAddress(),
                client.getCreationTimesStamp(),
                client.getUpdateTimesStamp(),
                cart.getId() // retorna apenas o ID do carrinho
        );
    }

    // Buscar todos os clientes
    public List<ClientResponseDTO> getAllClients() {
        return clientRepository.findAll().stream().map(client -> {
            UUID cartId = cartRepository.findByClientId(client.getId())
                    .map(Cart::getId)
                    .orElse(null);

            return new ClientResponseDTO(
                    client.getId(),
                    client.getName(),
                    client.getAddress(),
                    client.getCreationTimesStamp(),
                    client.getUpdateTimesStamp(),
                    cartId
            );
        }).collect(Collectors.toList());
    }

    public ClientUpdateResponseDTO updateClients(UUID uuid, ClientRequestDTO request) {
        Client client = clientRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        client.setName(request.name());
        client.setAddress(request.address());
        Client updatedClient = clientRepository.save(client);

        return new ClientUpdateResponseDTO(updatedClient.getId(), updatedClient.getName(), updatedClient.getAddress());
    }

    @Transactional
    public void deleteClientById(UUID clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new EntityNotFoundException("Client not found or no exists");
        }
        clientRepository.deleteById(clientId);
    }
}
