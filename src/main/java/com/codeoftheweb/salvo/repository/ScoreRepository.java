package com.codeoftheweb.salvo.repository;

import com.codeoftheweb.salvo.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findById(long id);
}
