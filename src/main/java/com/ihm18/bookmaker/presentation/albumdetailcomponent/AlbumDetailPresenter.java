package com.ihm18.bookmaker.presentation.albumdetailcomponent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.ihm18.bookmaker.presentation.albumslistcomponent.AlbumsListModel;
import com.ihm18.bookmaker.presentation.centralcomponent.CentralModel;
import com.ihm18.bookmaker.presentation.pagescomponent.PagesView;
import com.ihm18.bookmaker.presentation.utility.Utility;
import com.ihm18.bookmaker.service.AlbumService;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;


/**
 * presentateur du composant responsable sur l'affichage du detail d'un album.
 * @author qannoufoualid
 *
 */
public class AlbumDetailPresenter implements Initializable {

	/**
	 * fx:id='albumNameTextField' champ de texte de nom de l'album
	 */
	@FXML
	private TextField albumNameTextField;
	/**
	 * fx:id=AlbumDescriptionTextArea  texte area de la description de l'album
	 */
	@FXML
	private TextArea AlbumDescriptionTextArea;
	/**
	 * fx:id=albumCreationDateTextField champ de texte de la date de création de l'album
	 */
	@FXML
	private TextField albumCreationDateTextField;
	
	/**
	 * Modéle de detail de l'album.
	 */
	@Inject
	private AlbumDetailModel albumDetailModel;
	
	/**
	 * Modéle du composant central.
	 */
	@Inject
	private CentralModel centralModel;
	
	/**
	 * Modéle du composant affichant la liste des albums.
	 */
	@Inject
	private AlbumsListModel albumsListModel;
	
	/**
	 * Le service des albums.
	 */
	@Inject
	private AlbumService albumService;
	
	/**
	 * Le réportoire où mettre les images.
	 */
	@Inject
    private String imagesDirectory;
	
	/**
	 * La classe utilitaire.
	 */
	@Inject
	private Utility utility;
	

    public void launch() {
    	
    }

    /**
     * Permet d'initialiser le composant.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		albumNameTextField.textProperty().bindBidirectional(albumDetailModel.albumNameProperty());
		AlbumDescriptionTextArea.textProperty().bindBidirectional(albumDetailModel.albumDescriptionProperty());
		albumCreationDateTextField.setText(utility.convertToFrenshDate(albumDetailModel.getAlbum().getDateCreation()));
	}	
	
	/**
	 * Permet d'ajouter une liste des images.
	 * @param event
	 * @throws IOException
	 */
	public void addImages(ActionEvent event) throws IOException{
		FileChooser chooser = new FileChooser();
	    chooser.setTitle("Open File");
	    chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
		List<File> images = chooser.showOpenMultipleDialog(null);
		long lastImageId = findLastImageIdOfAlbum(albumDetailModel.getAlbum().getId());
		for(File file : images) {
		    Files.copy(file.toPath(),
		        (new File(imagesDirectory + albumDetailModel.getAlbum().getId()+"_"+(++lastImageId))).toPath(),
		        StandardCopyOption.REPLACE_EXISTING);
		}
	}
	
	/**
	 * Permet d'afficher les pages d'un album.
	 * @param event l'ActionEvent
	 */
	public void displayPages(ActionEvent event){
		PagesView pagesView = new PagesView();
		centralModel.setMainView(pagesView.getView(), utility.replace(PagesView.TITLE, "albumName", albumDetailModel.getAlbum().getName()));
	}
	
	/**
	 * Permet d'activer le bouton des l'édition de l'album.
	 * @param observable 
	 * @param oldValue
	 * @param newValue
	 */
	public void activateApplyChangesButton(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {
	}
	
	/**
	 * Appliquer les changements sur l'album.
	 * @param event
	 */
	public void applyChanges(ActionEvent event){
		
		albumService.edit(albumDetailModel.getAlbum().getId(), albumNameTextField.getText(), AlbumDescriptionTextArea.getText());
		utility.showInformationAlert(Alert.AlertType.INFORMATION, "Modification faite avec succés");
		albumsListModel.albumsProperty().clear();
		albumsListModel.albumsProperty().set(FXCollections.observableArrayList(albumService.getAlbums()));
		
	}
	
	/**
	 * Retrouver le dernier id des images de l'album.
	 * @param albumId
	 * @return
	 */
	public long findLastImageIdOfAlbum(Long albumId){
		return albumService.findLastImageIdOfAlbum(imagesDirectory, albumId);
	}

}
