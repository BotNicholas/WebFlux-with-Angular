package org.botnicholas.projects.webfluxserver.repo;

import org.botnicholas.projects.webfluxserver.model.Good;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepo extends ReactiveCrudRepository<Good, Long> {

}
