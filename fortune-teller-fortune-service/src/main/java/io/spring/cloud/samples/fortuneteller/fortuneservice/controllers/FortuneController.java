package io.spring.cloud.samples.fortuneteller.fortuneservice.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.cloud.samples.fortuneteller.fortuneservice.domain.Fortune;
import io.spring.cloud.samples.fortuneteller.fortuneservice.repositories.FortuneRepository;
import java.util.Random;

@RestController
public class FortuneController {

     private static Logger log = org.slf4j.LoggerFactory.getLogger(FortuneController.class);

    @Autowired
    FortuneRepository repository;

    @RequestMapping("/myFortunes")
    public Iterable<Fortune> myFortunes() {
        log.info("/fortunes called");
        return repository.findAll();
    }

    @RequestMapping("/random")
    public Fortune randomFortune() {
        log.info("/randomFortune called");
        Random random = new Random();
        int index = random.nextInt(10);
        List<Fortune> randomFortunes = repository.findAll();
        return randomFortunes.get(index);
    }
}
