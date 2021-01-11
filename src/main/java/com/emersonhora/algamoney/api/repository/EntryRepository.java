package com.emersonhora.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emersonhora.algamoney.api.entity.Entry;

public interface EntryRepository  extends JpaRepository<Entry, Long>  {

}
