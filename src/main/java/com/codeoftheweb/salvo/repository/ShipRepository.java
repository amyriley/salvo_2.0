package com.codeoftheweb.salvo.repository;

import com.codeoftheweb.salvo.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ShipRepository extends JpaRepository<Ship, Long> {
    List<Ship> findById(long id);
}
