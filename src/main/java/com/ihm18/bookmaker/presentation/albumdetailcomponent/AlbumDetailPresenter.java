package com.ihm18.bookmaker.presentation.albumdetailcomponent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.guigarage.incubator.imageviewer.data.Album;
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
import javafx.stage.StageStyle;

/**
 *
 * @author oualidqannouf
 */
public class AlbumDetailPresenter implements Initializable {

	@FXML
	private TextField albumNameTextField;
	@FXML
	private TextArea AlbumDescriptionTextArea;
	@FXML
	private TextField albumCreationDateTextField;
	
	
	@Inject
	private AlbumDetailModel albumDetailModel;
	
	@Inject
	private CentralModel centralModel;
	
	@Inject
	private AlbumService albumService;
	
	@Inject
    private String imagesDirectory;
	
	@Inject
	private Utility utility;
	
	@Inject
	private AlbumsListModel albumsListModel;
	
    public void launch() {
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		albumNameTextField.textProperty().bindBidirectional(albumDetailModel.albumNameProperty());
		AlbumDescriptionTextArea.textProperty().bindBidirectional(albumDetailModel.albumDescriptionProperty());
		albumCreationDateTextField.setText(utility.convertToFrenshDate(albumDetailModel.getAlbum().getDateCreation()));
	}	
	
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
	
	public void displayPages(ActionEvent event){
		PagesView pagesView = new PagesView();
		centralModel.setMainView(pagesView.getView(), utility.replace(PagesView.TITLE, "albumName", albumDetailModel.getAlbum().getName()));
	}
	
	public void activateApplyChangesButton(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {
			System.out.println("changed");
	}
	
	public void applyChanges(ActionEvent event){
		
		albumService.edit(albumDetailModel.getAlbum().getId(), albumNameTextField.getText(), AlbumDescriptionTextArea.getText());
		utility.showInformationAlert(Alert.AlertType.INFORMATION, "Modification faite avec succés");
		albumsListModel.albumsProperty().clear();
		albumsListModel.albumsProperty().set(FXCollections.observableArrayList(albumService.getAlbums()));
		
	}
	
	public long findLastImageIdOfAlbum(Long albumId){
		return albumService.findLastImageIdOfAlbum(imagesDirectory, albumId);
	}

}
