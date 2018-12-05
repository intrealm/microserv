package com.example.demo.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.CurrencyConversionBean;
import com.example.demo.feign.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;

	@RequestMapping("/currency-converter/{from}/{to}/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		
		
		CurrencyConversionBean response = currencyExchangeProxy.retrieveExchangeValue(from, to);

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());

		/*Map<String, String> map = new HashMap<>();
		map.put("from", from);
		map.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate()
				.getForEntity("http://localhost:8010/currency-exchange/{from}/{to}", CurrencyConversionBean.class, map);

		CurrencyConversionBean response = responseEntity.getBody();

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
*/
	}

}
