package com.emersonhora.algamoney.api.services;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	public void delete(Long id) {
		//Optional<Person> obj = repository.findById(id);
		//Person person = obj.get();
		repository.deleteById(id);
	}
	public Person update(Long id, Person person) {
		Person updatedPerson = this.findById(id);
		BeanUtils.copyProperties(person, updatedPerson, "id");
		return repository.save(updatedPerson);
	}
	public Person updateActivePerson(Long id, Boolean active) {
		Person person = this.findById(id);
		person.setActive(active);
		
		return repository.save(person);
	}
		
}
