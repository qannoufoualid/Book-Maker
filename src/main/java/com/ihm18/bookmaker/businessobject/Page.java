package com.ihm18.bookmaker.businessobject;

import java.util.ArrayList;
import java.util.List;

public class Page {

	/**
	 * L'id de la page
	 */
	private Long id;
	/**
	 * Le numéro de la page dans l'album.
	 */
	private int number;
	/**
	 * liste des images de la page.
	 */
	private List<IHMImage> images = new ArrayList<IHMImage>();
	/**
	 * L'album de la page.
	 */
	private Album album;
	
	/**
	 * getter de  L'album de la page.
	 * @return  L'album de la page.
	 */
	public Album getAlbum() {
		return album;
	}
	/**
	 * setter de  L'album de la page.
	 * @param album  L'album de la page.
	 */
	public void setAlbum(Album album) {
		this.album = album;
	}
	/**
	 * getter de l'id de la page
	 * @return
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Setter l'id de la page
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * getter du numéro de la page
	 * @return numéro de la page
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * setter du numéro de la page
	 * @param number numéro de la page
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * getter de la liste des images de la page.
	 * @return la liste des images de la page.
	 */
	public List<IHMImage> getImages() {
		return images;
	}
	/**
	 * setter de la liste des images de la page.
	 * @param images la liste des images de la page.
	 */
	public void setImages(List<IHMImage> images) {
		this.images = images;
	}
	/**
	 * Pour affiche la page dans la console.
	 */
	@Override
	public String toString() {
		return "Page [id=" + id + ", number=" + number + ", images=" + images + ", album=" + album + "]";
	}
	
	
	
	
}
