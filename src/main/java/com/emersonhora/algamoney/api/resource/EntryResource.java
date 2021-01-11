package com.emersonhora.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emersonhora.algamoney.api.entity.Entry;
import com.emersonhora.algamoney.api.event.AddResourceEvent;
import com.emersonhora.algamoney.api.services.EntryService;

@RestController
@RequestMapping("/entries")
public class EntryResource  {

	@Autowired
	private EntryService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Entry> list(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Entry> show(@PathVariable Long id ) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Entry> insert(@Valid @RequestBody Entry entry, HttpServletResponse response) {
		entry = service.insert(entry);

		publisher.publishEvent(new AddResourceEvent(this, response, entry.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(entry);

	}
	
}
