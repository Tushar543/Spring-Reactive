package com.example.reactivepostgracedemo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.reactivepostgracedemo.model.Coustomer;

// No need to provide a implementation spring will provide a sample implementation 
//logic for CRUD services
public interface CustomerRepository extends ReactiveCrudRepository<Coustomer, Integer>{

}
