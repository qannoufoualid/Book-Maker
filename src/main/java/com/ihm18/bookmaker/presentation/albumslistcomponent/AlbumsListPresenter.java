package com.ihm18.bookmaker.presentation.albumslistcomponent;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.ihm18.bookmaker.businessobject.Album;
import com.ihm18.bookmaker.presentation.albumdetailcomponent.AlbumDetailModel;
import com.ihm18.bookmaker.presentation.albumdetailcomponent.AlbumDetailView;
import com.ihm18.bookmaker.presentation.centralcomponent.CentralModel;
import com.ihm18.bookmaker.service.AlbumService;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author oualidqannouf
 */
public class AlbumsListPresenter implements Initializable {

	@Inject
	private CentralModel centralModel;

	@Inject
	private AlbumDetailModel albumDetailModel;
	
    @Inject
    private AlbumService albumService;
	
	@FXML
	private ListView<Album> albumsListView;
	
	@Inject
	private AlbumsListModel albumsListModel;

	private Album selectedAlbum;
	
    public void launch() {
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		 albumsListModel.albumsProperty().set(FXCollections.observableArrayList(albumService.getAlbums()));

    	//bind the list of albums in the service to the list of albums in the listView

    	albumsListView.setCellFactory(param -> new ListCell<Album>() {
            @Override
            protected void updateItem(Album item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    	
    	albumsListView.itemsProperty().bindBidirectional(albumsListModel.albumsProperty());

	}
	
	public void displayAlbumDetail(MouseEvent event){
		selectedAlbum = albumsListView.getSelectionModel().getSelectedItem();
		
		if(selectedAlbum != null)
		{
			albumDetailModel.setAlbum(selectedAlbum);
		}
		
		AlbumDetailView albumDetailView = new AlbumDetailView();
		centralModel.setMainView(albumDetailView.getView(), AlbumDetailView.TITLE);
		
	}

}

