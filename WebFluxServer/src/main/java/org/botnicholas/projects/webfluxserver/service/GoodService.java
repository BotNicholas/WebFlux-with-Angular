package org.botnicholas.projects.webfluxserver.service;

import lombok.extern.slf4j.Slf4j;
import org.botnicholas.projects.webfluxserver.model.Good;
import org.botnicholas.projects.webfluxserver.repo.GoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class GoodService {
  private final GoodRepo goodRepo;

  @Autowired
  public GoodService(GoodRepo goodRepo) {
    this.goodRepo = goodRepo;
  }

  public Mono<Good> getGood(Long id) {
    log.info("Get good {}", id);
    return goodRepo.findById(id);
  }

  public Flux<Good> getAllGoods() {
    log.info("Get all goods");
    return goodRepo.findAll();
  }

  public Mono<Good> createGood(Good good) {
    log.info("Create good {}", good);
    return goodRepo.save(good);
  }

  public Mono<Good> updateGood(Long id, Good good) {
    log.info("Update good {} to {}", id, good);
    return goodRepo.findById(id).flatMap(g -> {
      good.setId(g.getId());
      return goodRepo.save(good);
    });
  }
//  Or this way
//public Mono<Good> updateGood(Long id, Good good) {
//  good.setId(id);
//  return goodRepo.save(good);
//}

  public Mono<Void> deleteGood(Long id) {
    log.info("Delete good {}", id);
    return goodRepo.deleteById(id);
  }
}
