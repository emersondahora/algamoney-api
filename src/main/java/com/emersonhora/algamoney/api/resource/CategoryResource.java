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

import com.emersonhora.algamoney.api.entity.Category;
import com.emersonhora.algamoney.api.event.AddResourceEvent;
import com.emersonhora.algamoney.api.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource  {

	@Autowired
	private CategoryService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Category> list(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> show(@PathVariable Long id ) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Category> insert(@Valid @RequestBody Category category, HttpServletResponse response) {
		category = service.insert(category);

		publisher.publishEvent(new AddResourceEvent(this, response, category.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(category);

	}
}
