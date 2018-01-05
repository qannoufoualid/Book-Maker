package com.ihm18.bookmaker.presentation.dao;

import java.util.ArrayList;
import java.util.List;

import com.ihm18.bookmaker.businessobject.Album;

/**
 * Le DAO de l'album.
 * @author qannoufoualid
 *
 */
public class AlbumDao {

	/**
	 * Liste des albums.
	 */
	private List<Album> albums = new ArrayList<Album>();

	/**
	 * getter de la liste des albums.
	 * @return
	 */
	public List<Album> getAlbums() {
		return albums;
	}

	/**
	 * Setter de la liste des albums.
	 * @param albums
	 */
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
	
	
}
