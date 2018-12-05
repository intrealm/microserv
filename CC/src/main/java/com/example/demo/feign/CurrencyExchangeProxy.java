package com.example.demo.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.bean.CurrencyConversionBean;

@FeignClient(name="forex-service")
@RibbonClient(name="forex-service")
public interface CurrencyExchangeProxy {


  @GetMapping("/currency-exchange/{from}/{to}")
  public CurrencyConversionBean retrieveExchangeValue
    (@PathVariable("from") String from, @PathVariable("to") String to);
}