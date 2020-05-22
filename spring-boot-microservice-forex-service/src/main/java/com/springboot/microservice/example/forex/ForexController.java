package com.springboot.microservice.example.forex;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservice.common.ExchangeValue;

@RestController
public class ForexController {
  
  @Autowired
  private Environment environment;
  
  @Autowired
  private ExchangeValueRepository repository;
  
  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public ExchangeValue retrieveExchangeValue
    (@PathVariable String from, @PathVariable String to){
    
    ExchangeValue exchangeValue = 
        repository.findByFromAndTo(from, to);
    
    exchangeValue.setPort(
        Integer.parseInt(environment.getProperty("local.server.port")));
    
    return exchangeValue;
  }
  
  @GetMapping("/currency-exchange")
  public List<ExchangeValue> retrieveAll(){
    
    List<ExchangeValue> exchangeValueList = 
        repository.findAll();
    
    return exchangeValueList;
  }
  
	@PostMapping("/currency-exchange")
	ExchangeValue newExchangeValue(@RequestBody ExchangeValue newExchangeValue) {
		return repository.save(newExchangeValue);
	}

}