package com.ihm18.bookmaker.presentation.albumdetailcomponent;

import com.ihm18.bookmaker.businessobject.Album;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class AlbumDetailModel {

    private final ObjectProperty<String> albumName = new SimpleObjectProperty<String>(this, "albumName", null);
    private final ObjectProperty<String> albumDescription = new SimpleObjectProperty<String>(this, "albumDescription", null);
    private Album album;
    
    public ObjectProperty<String> albumNameProperty() {
        return albumName ;
    }
    
    public final String albumName() {
        return albumName.get();
    }
    
    public final void setAlbumName(String albumName) {
        this.albumName.set(albumName);
    }
    
    public ObjectProperty<String> albumDescriptionProperty() {
        return albumDescription ;
    }
    
    public final String getAlbumDescription() {
        return albumDescription.get();
    }
    
    public final void setAlbumDescription(String albumDescription) {
        this.albumDescription.set(albumDescription);
    }

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		albumName.set(album.getName());
		albumDescription.set(album.getDescription());
		this.album = album;
	}
    
    
    
}
