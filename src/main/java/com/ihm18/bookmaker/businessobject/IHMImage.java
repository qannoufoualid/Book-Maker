package com.ihm18.bookmaker.businessobject;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class IHMImage {

	
	/**
	 * L'id de l'image
	 */
	private Long id;
	/**
	 * Le nom de l'image.
	 */
	private String title;
	/**
	 * La description de l'image
	 */
	private String description;
	/**
	 * La date de création de l'image
	 */
	private LocalDateTime dateCreation;
	/**
	 * Le fichier de l'image.
	 */
	private File file;
	
	/**
	 * page de l'image
	 */
	private Page page;
	
	/**
	 * getter de fichier de l'image
	 * @return
	 */
	public File getFile() {
		return file;
	}

	/**
	 * setter de fichier de l'image.
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}


	
	/**
	 * getter de la page de l'image.
	 * @return
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * setter de la page de l'image.
	 * @return
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * construire une IHMImage
	 * @param name nom de l'image
	 * @param description description de l'image
	 * @param dateCreation date de craation de l'image
	 */
	public IHMImage(String name, String description, LocalDateTime dateCreation) {
		this.id = System.currentTimeMillis();
		this.title = name;
		this.description = description;
		this.dateCreation = dateCreation;
	}
	
	/**
	 * getter de l'id de l'image
	 * @return l'id de l'image
	 */
	public Long getId() {
		return id;
	}
	/**
	 * setter de l'id de l'image
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * getter du titre de l'image
	 * @return le  titre de l'image
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * setter du  titre de l'image
	 * @param name  titre de l'image
	 */
	public void setTitle(String name) {
		this.title = name;
	}
	/**
	 * getter de la description de l'image
	 * @return la description de l'image
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * setter de la description de l'image
	 * @param description la description de l'image
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * getter de la date de création d'une image.
	 * @return la date de création d'une image.
	 */
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}
	/**
	 * setter de la date de création d'une image.
	 * @param dateCreation la date de création d'une image.
	 */
	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * pour afficher l'image dans la console.
	 */
	@Override
	public String toString() {
		return "Image  [id=" + id + ", title=" + title + ", description=" + description + ", dateCreation=" + dateCreation
				+ "]";
	}
	
	
	
	
	
}
