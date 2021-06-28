package com.example.reactivepostgracedemo.coustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactivepostgracedemo.model.Coustomer;
import com.example.reactivepostgracedemo.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coustomer")
public class CoustomerController {

	@Autowired
	CustomerRepository repository;
	/**
	 * Flux when we are trying to return group
	 * @return
	 */
	@GetMapping
	public Flux<Coustomer> getCoustomers(){
		return repository.findAll();
	}
	
	/**
	 * we will use mono when single record
	 */
	@GetMapping("/{id}")
	public Mono<Coustomer> getCoustomer(@PathVariable Integer id){
		return repository.findById(id);
	}
	
	@PostMapping
	public Mono createCustomer(@RequestBody Coustomer coustomer) {
		return repository.save(coustomer);
	}
	
	@PutMapping("/{id}")
	public  Mono<Coustomer> updateCustomer(@RequestBody Coustomer coustomer,@PathVariable Integer id){
		return repository.findById(id).map((c) -> {c.setName(coustomer.getName());
		return c;
		}).flatMap(c->repository.save(c));
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteCoustomer(@PathVariable Integer id) {
		return repository.deleteById(id);
		
	}	
	
}
