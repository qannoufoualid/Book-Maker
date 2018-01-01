package com.ihm18.bookmaker.businessobject;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class IHMImage {

	
	private Long id;
	private String title;
	private String description;
	private LocalDateTime dateCreation;
	private File file;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	private Page page;
	
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public IHMImage(String name, String description, LocalDateTime dateCreation) {
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
