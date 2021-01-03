package com.emersonhora.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emersonhora.algamoney.api.entity.Person;

public interface PersonReporitory extends JpaRepository<Person, Long> {

}
