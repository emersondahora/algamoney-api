package com.emersonhora.algamoney.api.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.emersonhora.algamoney.api.model.Category;
import com.emersonhora.algamoney.api.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource  {

	@Autowired
	private CategoryService service;
	
	@GetMapping
	public List<Category> listar(){
		return service.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Category> insert(@PathVariable Long id ) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Category> insert(@Valid @RequestBody Category category) {
		category = service.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).body(category);
	}
}
