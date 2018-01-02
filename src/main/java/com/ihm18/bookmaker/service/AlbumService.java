package com.ihm18.bookmaker.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ihm18.bookmaker.businessobject.Album;
import com.ihm18.bookmaker.businessobject.Page;

public class AlbumService {

	private List<Album> albums = new ArrayList<Album>();

	public AlbumService() {
		
		init();
	}

	public void init(){
		albums.add(new Album("walid album", "bla bla"));
		albums.add(new Album("zac album", "bla bla"));
	}
	
	
	public void add(Album album){
		albums.add(album);
	}


	@Override
	public String toString() {
		return "AlbumService [albums=" + albums + "]";
	}


	public List<Album> getAlbums() {
		return albums;
	}


	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public long findLastImageIdOfAlbum(String rootDirectory,Long albumId) {
		
		File folder = new File(rootDirectory);
		
		long lastImageId = 0;
		File[] listOfFiles = folder.listFiles();
		System.out.println(listOfFiles);
	
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile() && listOfFiles[i].getName().startsWith(albumId.toString())) {
		    	  lastImageId = Long.valueOf(listOfFiles[i].getName().split("_")[1]);
		      } 
		    }
		
		return lastImageId;
	}

	public Album findById(Long id) {
		
		for(Album album : albums)
			if(album.getId().equals(id))
				return album;
		return null;
	}

	public void edit(Album album) {
		Album a = findById(album.getId());
		a.setName(album.getName());
		a.setDescription(album.getDescription());
		a.setDateCreation(album.getDateCreation());
	}
	
	public void edit(Long id, String name, String description){
		Album a = findById(id);
		if(a!=null){
			a.setName(name);
			a.setDescription(description);
		}
	}

}
