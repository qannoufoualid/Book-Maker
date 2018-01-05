package com.ihm18.bookmaker.presentation.albumslistcomponent;

import com.ihm18.bookmaker.businessobject.Album;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
/**
 * Le modéle du composant responsable sur l'affichage des albums.
 * @author qannoufoualid
 *
 */
public class AlbumsListModel {

	/**
	 * Proprieté qui contient la liste des albums. nous avons fait ça car on doit detecter si les albums ont été modifiés ailleurs pour pouvoir mettre à jour la liste.
	 */
	private ListProperty<Album> albumsProperty = new SimpleListProperty<>();

	/**
	 * getter de la properité albumsProperty
	 * @return albumsProperty
	 */
	public ListProperty<Album> albumsProperty() {
		return albumsProperty;
	}

	/**
	 * setter de la properité albumsProperty
	 * @param albumsProperty albumsProperty
	 */
	public void setAlbumsProperty(ListProperty<Album> albumsProperty) {
		this.albumsProperty = albumsProperty;
	}

	
    
    
}
