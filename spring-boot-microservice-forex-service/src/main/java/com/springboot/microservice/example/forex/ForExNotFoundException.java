package com.springboot.microservice.example.forex;

public class ForExNotFoundException extends RuntimeException{

	ForExNotFoundException(String from, String to) {
		super("Could not find exchange value from: "+from + " to: "+to);
	}
}
