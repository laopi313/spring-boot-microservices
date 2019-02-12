package com.springboot.microservice.example.currencyconversion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.*;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
//@WebMvcTest(CurrencyConversionController.class)
public class CurrencyConversionControllerTest {

	//@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private CurrencyExchangeServiceProxy proxy;
	
	@InjectMocks
	private CurrencyConversionController controller;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new CurrencyConversionController()).build();
	}
	
	@Test
	public void testGet() throws Exception {
		String from = "EUR";
		String to = "INR";
		String quantity = "10000";
		mockMvc.perform(get("/currency-converter/from/{from}/to/{to}/quantity/10000", from, to, quantity))
				.andExpect(status().isOk())            
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$.id", is(10002)))
	            .andExpect(jsonPath("$.from", is("EUR")))
	            .andExpect(jsonPath("$.to", is("INR")))
	            .andExpect(jsonPath("$.quantity", is(10000)))
	            .andExpect(jsonPath("totalCalculatedAmount", is(750000.0)));
	}

}
