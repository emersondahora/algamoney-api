package com.emersonhora.algamoney.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class EntryFilter {

	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateEnd;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDateStart() {
		return dueDateStart;
	}
	public void setDueDateStart(LocalDate dueDateStart) {
		this.dueDateStart = dueDateStart;
	}
	public LocalDate getDueDateEnd() {
		return dueDateEnd;
	}
	public void setDueDateEnd(LocalDate dueDateEnd) {
		this.dueDateEnd = dueDateEnd;
	}
	
	
	
}
