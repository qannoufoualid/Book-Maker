package com.ihm18.bookmaker.businessobject;

import java.util.ArrayList;
import java.util.List;

public class Page {

	private Long id;
	private int number;
	private List<Image> images = new ArrayList<Image>();
	
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
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	
	
	
}
