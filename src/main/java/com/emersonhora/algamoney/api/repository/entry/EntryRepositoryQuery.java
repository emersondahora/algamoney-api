package com.emersonhora.algamoney.api.repository.entry;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emersonhora.algamoney.api.entity.Entry;
import com.emersonhora.algamoney.api.repository.filter.EntryFilter;

public interface EntryRepositoryQuery {

	public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable); 
}
