package com.emersonhora.algamoney.api.repository.entry;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.emersonhora.algamoney.api.entity.Entry;
import com.emersonhora.algamoney.api.entity.Entry_;
import com.emersonhora.algamoney.api.repository.filter.EntryFilter;

public class EntryRepositoryImpl implements EntryRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Entry> criteria = builder.createQuery(Entry.class);
		
		Root<Entry> root = criteria.from(Entry.class);
		
		// create restrictions
		Predicate[] predicates = criateRestrictions(entryFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Entry> query = manager.createQuery(criteria);
		addPageRestriction(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(entryFilter)) ;
	}


	@SuppressWarnings("deprecation")
	private Predicate[] criateRestrictions(EntryFilter entryFilter, CriteriaBuilder builder, Root<Entry> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(entryFilter.getDescription())) {
			predicates.add(builder.like(
					builder.lower(root.get(Entry_.description)), 
					"%" + entryFilter.getDescription().toLowerCase() +"%"
				));
		}

		if(entryFilter.getDueDateStart() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Entry_.dueDate), entryFilter.getDueDateStart()));
		}

		if(entryFilter.getDueDateEnd() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Entry_.dueDate), entryFilter.getDueDateEnd()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void addPageRestriction(TypedQuery<Entry> query, Pageable pageable) {

		int initialPage = pageable.getPageNumber();
		int totalEntriesByPage = pageable.getPageSize();
		int firstEntryPage = initialPage * totalEntriesByPage;
		
		query.setFirstResult(firstEntryPage);
		query.setMaxResults(totalEntriesByPage);
	}
	private Long total(EntryFilter entryFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
		Root<Entry> root = criteria.from(Entry.class);
		
		Predicate[] predicates = criateRestrictions(entryFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
