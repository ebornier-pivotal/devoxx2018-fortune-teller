package io.spring.cloud.samples.fortuneteller.fortuneservice.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import io.spring.cloud.samples.fortuneteller.fortuneservice.domain.Fortune;

public interface FortuneRepository extends MongoRepository<Fortune, String> {


}
