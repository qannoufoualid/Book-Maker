package com.ihm18.bookmaker.businessobject;

import java.util.ArrayList;
import java.util.List;

public class Page {

	private Long id;
	private int number;
	private List<IHMImage> images = new ArrayList<IHMImage>();
	private Album album;
	
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<IHMImage> getImages() {
		return images;
	}
	public void setImages(List<IHMImage> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Page [id=" + id + ", number=" + number + ", images=" + images + ", album=" + album + "]";
	}
	
	
	
	
}
