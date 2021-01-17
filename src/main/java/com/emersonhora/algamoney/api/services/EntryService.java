package com.emersonhora.algamoney.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emersonhora.algamoney.api.entity.Category;
import com.emersonhora.algamoney.api.entity.Entry;
import com.emersonhora.algamoney.api.entity.Person;
import com.emersonhora.algamoney.api.repository.CategoryRepository;
import com.emersonhora.algamoney.api.repository.EntryRepository;
import com.emersonhora.algamoney.api.repository.PersonReporitory;
import com.emersonhora.algamoney.api.repository.filter.EntryFilter;
import com.emersonhora.algamoney.api.services.exceptions.PersonInaciveException;
import com.emersonhora.algamoney.api.services.exceptions.ResourceNotFoundException;

@Service
public class EntryService {
	
	@Autowired
	private EntryRepository repository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PersonService personService;
	
	public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable) {
		return repository.filter(entryFilter, pageable);
	}
	public Entry findById(Long id) {
		Optional<Entry> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	public Entry insert(Entry entry) {
		Person person = personService.findById(entry.getPerson().getId());
		Category category =categoryService.findById(entry.getCategory().getId()); 
		if(person.isInactive()) {
			throw new PersonInaciveException();
		}
		entry.setCategory(category);
		entry.setPerson(person);
		
		return repository.save(entry);
	}
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
