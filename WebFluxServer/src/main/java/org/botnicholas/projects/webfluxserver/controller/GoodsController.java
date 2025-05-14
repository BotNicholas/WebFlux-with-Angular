package org.botnicholas.projects.webfluxserver.controller;

import org.botnicholas.projects.webfluxserver.model.Good;
import org.botnicholas.projects.webfluxserver.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@CrossOrigin
@RestController
@RequestMapping("/goods")
public class GoodsController {
  private final GoodService goodService;

  @Autowired
  private GoodsController(GoodService goodService) {
    this.goodService = goodService;
  }

  @GetMapping("/test/flux")
  public Flux<String> testFlux() {
    return Flux.interval(Duration.ofSeconds(2)).map(seq -> "data: " + seq);
  }

  @GetMapping("/test/mono")
  public Mono<String> testMono() {
    return Mono.just(Duration.ofSeconds(2)).map(seq -> "data: " + seq).delayElement(Duration.ofSeconds(2));
  }

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Good> getGoods() throws InterruptedException {
    return goodService.getAllGoods()
        .doOnCancel(() -> System.out.println("Client Canceled subscription!"))
        .doOnComplete(() -> System.out.println("Subscription Completed!"));
  }

  @GetMapping("/{id}")
  public Mono<Good> getGoodById(@PathVariable("id") Long id) {
    return goodService.getGood(id);
  }

  @PostMapping
  public Mono<Good> createGood(@RequestBody Good good) {
    return goodService.createGood(good);
  }

  @PatchMapping("/{id}")
  public Mono<Good> updateGood(@PathVariable("id") Long id, @RequestBody Good good) {
    return goodService.updateGood(id, good);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deleteGood(@PathVariable("id") Long id) {
    return goodService.deleteGood(id);
  }
}
