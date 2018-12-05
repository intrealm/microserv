package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ExchangeValue;

public interface ExchangeValueRepository extends 
JpaRepository<ExchangeValue, Long>{

	ExchangeValue findByFromAndTo(String from, String to);
}