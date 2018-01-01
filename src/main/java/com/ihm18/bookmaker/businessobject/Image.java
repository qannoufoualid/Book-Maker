package com.ihm18.bookmaker.businessobject;

import java.time.LocalDateTime;

public class Image {

	
	private Long id;
	private String title;
	private String description;
	private LocalDateTime dateCreation;
	
	public Image(String name, String description, LocalDateTime dateCreation) {
		this.id = System.currentTimeMillis();
		this.title = name;
		this.description = description;
		this.dateCreation = dateCreation;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String name) {
		this.title = name;
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

	@Override
	public String toString() {
		return "Image  [id=" + id + ", title=" + title + ", description=" + description + ", dateCreation=" + dateCreation
				+ "]";
	}
	
	
	
	
	
}
