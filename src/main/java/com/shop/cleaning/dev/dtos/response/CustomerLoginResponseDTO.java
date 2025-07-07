package com.shop.cleaning.dev.dtos.response;

import java.util.UUID;

public record CustomerLoginResponseDTO(UUID id, String fullName, String userName, String phoneNumber, String token) {

}
