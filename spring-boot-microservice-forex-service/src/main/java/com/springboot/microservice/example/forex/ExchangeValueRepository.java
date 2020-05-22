package com.springboot.microservice.example.forex;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.microservice.common.ExchangeValue;

public interface ExchangeValueRepository extends 
    JpaRepository<ExchangeValue, Long>{
  ExchangeValue findByFromAndTo(String from, String to);
}