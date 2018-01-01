package com.ihm18.bookmaker.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ihm18.bookmaker.businessobject.IHMImage;

/**
 * 
 * @author oualidqannouf
 *
 */
public class ImageService {

	private List<IHMImage> images = new ArrayList<IHMImage>();

	public ImageService() {
		
		init();
	}

	public void init(){
		
		
	}
	
	public void add(IHMImage image){
		images.add(image);
	}
	
	@Override
	public String toString() {
		return "ImageService [images=" + images + "]";
	}


	public List<IHMImage> getAllImages() {
		return images;
	}


	public void setImages(List<IHMImage> images) {
		this.images = images;
	}
}
