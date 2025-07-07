package com.shop.cleaning.dev.dtos.response;

import com.shop.cleaning.dev.entities.utilClass.Address;

import java.time.Instant;
import java.util.UUID;

public record CustomerResponseDto(UUID id,
                                  String fullName,
                                  String phoneNumber,
                                  Address address,
                                  String userName,
                                  String password,
                                  Instant createTime,
                                  Instant updateTime
                                  ) {

}
