package com.shop.cleaning.dev.dtos.responseDtos;

import com.shop.cleaning.dev.entities.Cart;
import com.shop.cleaning.dev.entities.utilClass.Address;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ClientResponseDTO(UUID id,
                                      String name,
                                      Address address,
                                      Instant timeStamp,
                                      Instant updateTimeStamp,
                                      UUID cartId
                                     ) {
}
