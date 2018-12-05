package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ExchangeValue;
import com.example.demo.repo.ExchangeValueRepository;

@RestController
public class CurrencyController {

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepository repository;

	@RequestMapping(value = "/currency-exchange/{from}/{to}")
	public ExchangeValue getValue(final @PathVariable(required = true, name = "from") String from,
			@PathVariable(required = true, name = "to") String to) {
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

		return exchangeValue;
	}
}
