package com.ihm18.bookmaker.businessobject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Album {

	
	private Long id;
	private String name;
	private String description;
	private LocalDateTime dateCreation;
	private List<Page> pages = new ArrayList<Page>();
	
	public Album(String name, String description) {
		this.id = System.currentTimeMillis();
		this.name = name;
		this.description = description;
		this.dateCreation = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	
	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "Album  [id=" + id + ", name=" + name + ", description=" + description + ", dateCreation=" + dateCreation
				+ "]";
	}

	public void addPage(Page page) {
		pages.add(page);
	}
	
	
	
	
}
