package com.ihm18.bookmaker.presentation.albumformcomponent;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.ihm18.bookmaker.businessobject.Album;
import com.ihm18.bookmaker.presentation.albumslistcomponent.AlbumsListModel;
import com.ihm18.bookmaker.service.AlbumService;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


/**
 * Le presentateur du composant de création d'un album.
 * @author qannoufoualid
 *
 */
public class AlbumFormPresenter implements Initializable {

	/**
	 * Le service des albums.
	 */
	@Inject
	private AlbumService albumService;

	/**
	 * Le modéle de la liste des albums pour pouvoir mettre à jours la liste aprés l'ajout.
	 */
	@Inject
	private AlbumsListModel albumsListModel;
	
	/**
	 * fx:id=albumNameTextField nom de l'album.
	 */
	@FXML
	private TextField albumNameTextField;
	
	/**
	 * fx:id=albumDescriptionTextField description de l'album.
	 */
	@FXML
	private TextArea albumDescriptionTextField;
	
    public void launch() {
    }

    /**
     * Permet d'initialiser le composant.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	/**
	 * Permet de créer un album.
	 * @param event
	 */
	public void createAlbum(ActionEvent event){

		Album album = new Album(albumNameTextField.getText(), albumDescriptionTextField.getText());
		albumService.add(album);
		albumsListModel.albumsProperty().set(FXCollections.observableArrayList(albumService.getAlbums()));

	}
	
}
