package com.ihm18.bookmaker.presentation.albumformcomponent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.inject.Inject;

import com.ihm18.bookmaker.businessobject.Album;
import com.ihm18.bookmaker.presentation.albumslistcomponent.AlbumsListModel;
import com.ihm18.bookmaker.service.AlbumService;

/**
 *
 * @author oualidqannouf
 */
public class AlbumFormPresenter implements Initializable {

	@Inject
	private AlbumService albumService;

	@Inject
	private AlbumsListModel albumsListModel;
	
	@FXML
	private TextField albumNameTextField;
	
	@FXML
	private TextArea albumDescriptionTextField;
	
    public void launch() {
    	System.out.println("WelcomePresenter started");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void createAlbum(ActionEvent event){

		Album album = new Album(albumNameTextField.getText(), albumDescriptionTextField.getText());
		albumService.add(album);
		albumsListModel.albumsProperty().set(FXCollections.observableArrayList(albumService.getAlbums()));

	}
	
}
