package com.emersonhora.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emersonhora.algamoney.api.entity.Person;
import com.emersonhora.algamoney.api.event.AddResourceEvent;
import com.emersonhora.algamoney.api.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonResource  {

	@Autowired
	private PersonService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Person> list(){
		return service.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Person> show(@PathVariable Long id ) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Person> insert(@Valid @RequestBody Person person, HttpServletResponse response) {
		person = service.insert(person);

		publisher.publishEvent(new AddResourceEvent(this, response, person.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id ) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person ) {
 		return ResponseEntity.ok(service.update(id, person));
	}	
	
	@PutMapping("/{id}/active") 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody Boolean active) {
		service.updateActivePerson(id, active);
	}	
}
