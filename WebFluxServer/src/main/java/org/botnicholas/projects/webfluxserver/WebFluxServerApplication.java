package org.botnicholas.projects.webfluxserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WebFluxServerApplication {

  public static void main(String[] args) {
    log.debug("HELLO FROM MAIN METHOD!!!");


//    If we don't use Lombok with @Sl4J
//    Logger logger = LoggerFactory.getLogger(WebFluxServerApplication.class);
//    logger.info("HELLO FROM MAIN METHOD!!!");

    SpringApplication.run(WebFluxServerApplication.class, args);
  }

}
