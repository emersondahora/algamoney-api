package com.emersonhora.algamoney.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emersonhora.algamoney.api.entity.Person;
import com.emersonhora.algamoney.api.repository.PersonReporitory;
import com.emersonhora.algamoney.api.services.exceptions.ResourceNotFoundException;

@Service
public class PersonService {
	
	@Autowired
	private PersonReporitory repository;
	
	public List<Person> findAll() {
		return repository.findAll();
	}
	public Person findById(Long id) {
		Optional<Person> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	public Person insert(Person obj) {
		return repository.save(obj);
	}
		
}
