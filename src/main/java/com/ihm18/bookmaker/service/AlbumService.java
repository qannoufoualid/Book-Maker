package com.ihm18.bookmaker.service;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import com.ihm18.bookmaker.businessobject.Album;
import com.ihm18.bookmaker.dao.AlbumDao;

/**
 * 
 * @author qannoufoualid
 *	Le service des albums.
 */
public class AlbumService {

	/**
	 * Le dao des albums.
	 */
	private AlbumDao albumDao = new AlbumDao();

	public AlbumService() {
		
		init();
	}

	/**
	 * Permet d'initialiser la liste des albums. pour le moment c'est un mock.
	 */
	public void init(){
		
	}
	
	/**
	 * Permet d'ajouter un album.
	 * @param album
	 */
	public void add(Album album){
		albumDao.getAlbums().add(album);
	}

	/**
	 * getter des albums
	 * @return
	 */
	public List<Album> getAlbums() {
		return albumDao.getAlbums();
	}

	/**
	 * Setter des albums.
	 * @param albums
	 */
	public void setAlbums(List<Album> albums) {
		this.albumDao.setAlbums(albums);
	}

	/**
	 * Permet de retrouver la derniere image dans album.
	 * @param rootDirectory
	 * @param albumId
	 * @return
	 */
	public long findLastImageIdOfAlbum(String rootDirectory,Long albumId) {
		
		File folder = new File(rootDirectory);
		
		long lastImageId = 0;
		File[] listOfFiles = folder.listFiles();
	
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile() && listOfFiles[i].getName().startsWith(albumId.toString())) {
		    	  lastImageId = Long.valueOf(listOfFiles[i].getName().split("_")[1]);
		      } 
		    }
		
		return lastImageId;
	}

	/**
	 * Permet de retrouver un album.
	 * @param id
	 * @return
	 */
	public Album findById(Long id) {
		
		for(Album album : albumDao.getAlbums())
			if(album.getId().equals(id))
				return album;
		return null;
	}

	/**
	 * Permet de modifier un album.
	 * @param album
	 */
	public void edit(Album album) {
		Album a = findById(album.getId());
		a.setName(album.getName());
		a.setDescription(album.getDescription());
		a.setDateCreation(album.getDateCreation());
	}
	
	/**
	 * Permet de modifier un album.
	 * @param id l'identifiant de l'album
	 * @param name le nom de l'album
	 * @param description la description de l'album
	 */
	public void edit(Long id, String name, String description){
		Album a = findById(id);
		if(a!=null){
			a.setName(name);
			a.setDescription(description);
		}
	}

	@Override
	public String toString() {
		return "AlbumService [albumDao.getAlbums()=" + albumDao.getAlbums() + "]";
	}

}
