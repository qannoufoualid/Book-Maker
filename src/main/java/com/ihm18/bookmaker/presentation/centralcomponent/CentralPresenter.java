package com.ihm18.bookmaker.presentation.centralcomponent;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.ihm18.bookmaker.businessobject.Album;
import com.ihm18.bookmaker.presentation.albumslistcomponent.AlbumsListView;
import com.ihm18.bookmaker.presentation.historycomponent.HistoryModel;
import com.ihm18.bookmaker.presentation.historycomponent.HistoryView;
import com.ihm18.bookmaker.presentation.welcomecomponent.WelcomeView;
import com.ihm18.bookmaker.service.AlbumService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Le presentateur du composant central.
 * @author oualidqannouf
 */
public class CentralPresenter implements Initializable {

	@FXML 
    private BorderPane mainContainer;
	
    @Inject
    private CentralModel centralModel ;

   
    @FXML
    private AnchorPane leftContainer;
    
    @FXML
    private Label mainViewTitleLabel;
    
    
	public void initialize(URL location, ResourceBundle resources) {
		
		leftContainer.getChildren().add(new AlbumsListView().getView());
		
    	mainContainer.centerProperty().bind(centralModel.mainViewProperty());
    	mainContainer.bottomProperty().bind(centralModel.bottomViewProperty());
    	mainViewTitleLabel.textProperty().bind(centralModel.mainViewTitleProperty());
        WelcomeView welcomeView = new WelcomeView();
        centralModel.setMainView(welcomeView.getView(), WelcomeView.TITLE);
        centralModel.setBottomView(new HistoryView().getView());
    }

    public void launch() {
    }


}
