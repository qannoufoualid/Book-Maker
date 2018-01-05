package com.ihm18.bookmaker.businessobject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * L'entité Album.
 * @author qannoufoualid
 *
 */
public class Album {

	/**
	 * L'id de l'album
	 */
	private Long id;
	/**
	 * Le nom de l'album.
	 */
	private String name;
	/**
	 * La description de l'album.
	 */
	private String description;
	/**
	 * La date de création de l'album.
	 */
	private LocalDateTime dateCreation;
	/**
	 * la liste des pages de l'album.
	 */
	private List<Page> pages = new ArrayList<Page>();
	
	/**
	 * Le constructeur de l'album.
	 * @param name nom de l'album
	 * @param description la description de l'album.
	 */
	public Album(String name, String description) {
		this.id = System.currentTimeMillis();
		this.name = name;
		this.description = description;
		this.dateCreation = LocalDateTime.now();
	}
	
	/**
	 * getter de l'id de l'album.
	 * @return l'id de l'album
	 */
	public Long getId() {
		return id;
	}
	/**
	 * setter de l'id de l'album.
	 * @param id identifiant de l'album.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * getter du nom de l'album.
	 * @return le nom de l'album.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setter de nom de l'album
	 * @param name le nom de l'album
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter de la description de l'album.
	 * @return la description de l'album.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * setter de la description de l'album.
	 * @param description la description de l'album.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * getter de la date de création d'un album.
	 * @return la date de création d'un album.
	 */
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}
	/**
	 * setter de la date de création d'un album.
	 * @param dateCreation la date de création d'un album.
	 */
	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	/**
	 * getter de la liste des pages
	 * @return la liste des pages
	 */
	public List<Page> getPages() {
		return pages;
	}

	/**
	 * sette de la liste des pages
	 * @param pages la liste des pages
	 */
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	/**
	 * Methode pour afficher l'album dans la console.
	 */
	@Override
	public String toString() {
		return "Album  [id=" + id + ", name=" + name + ", description=" + description + ", dateCreation=" + dateCreation
				+ "]";
	}

	/**
	 * Permet d'ajouter une page dans l'album.
	 * @param page la page à ajouter
	 */
	public void addPage(Page page) {
		pages.add(page);
	}
	
	
	
	
}
