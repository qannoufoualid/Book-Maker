package com.ihm18.bookmaker.presentation.albumslistcomponent;

import com.ihm18.bookmaker.businessobject.Album;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

public class AlbumsListModel {

	private ListProperty<Album> albumsProperty = new SimpleListProperty<>();

	public ListProperty<Album> albumsProperty() {
		return albumsProperty;
	}

	public void setAlbumsProperty(ListProperty<Album> albumsProperty) {
		this.albumsProperty = albumsProperty;
	}

	
    
    
}
