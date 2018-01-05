package com.ihm18.bookmaker.presentation.albumdetailcomponent;

import com.ihm18.bookmaker.businessobject.Album;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Modéle du composant responsable sur l'affichage du detail d'un album.
 * @author qannoufoualid
 *
 */
public class AlbumDetailModel {

	/**
	 * proprieté du nom de l'album.
	 */
    private final ObjectProperty<String> albumName = new SimpleObjectProperty<String>(this, "albumName", null);
    /**
     * Proprieté de la description de l'album.
     */
    private final ObjectProperty<String> albumDescription = new SimpleObjectProperty<String>(this, "albumDescription", null);
    /**
     * L'album à affiché.
     */
    private Album album;
    
    /**
     * getter de la proprieté albumNameProperty
     * @return
     */
    public ObjectProperty<String> albumNameProperty() {
        return albumName ;
    }
    /**
     * getter du nom de l'album
     * @return le nom de l'album
     */
    public final String albumName() {
        return albumName.get();
    }
    /**
     * setter du nom de l'album.
     * @param albumName nom de l'album
     */
    public final void setAlbumName(String albumName) {
        this.albumName.set(albumName);
    }
    
    /**
     * getter de la proprieté albumDescriptionProperty
     * @return  la proprieté albumDescriptionProperty
     */
    public ObjectProperty<String> albumDescriptionProperty() {
        return albumDescription ;
    }
    /**
     * getter de la description de l'album.
     * @return la description de l'album.
     */
    public final String getAlbumDescription() {
        return albumDescription.get();
    }
    
    /**
     * setter de la description de l'album.
     * @param albumDescription la description de l'album.
     */
    public final void setAlbumDescription(String albumDescription) {
        this.albumDescription.set(albumDescription);
    }

    /**
     * getter de l'album.
     * @return l'album.
     */
	public Album getAlbum() {
		return album;
	}
	
	/**
	 * setter de l'album.
	 * @param album l'album
	 */
	public void setAlbum(Album album) {
		albumName.set(album.getName());
		albumDescription.set(album.getDescription());
		this.album = album;
	}
    
    
}
