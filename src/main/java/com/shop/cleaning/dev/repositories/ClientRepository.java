package com.shop.cleaning.dev.repositories;

import com.shop.cleaning.dev.dtos.responseDtos.DtoClientResponse;
import com.shop.cleaning.dev.entities.Client;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

}
