package com.emersonhora.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emersonhora.algamoney.api.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
} 
