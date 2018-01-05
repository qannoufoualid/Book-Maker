package com.ihm18.bookmaker.presentation.historycomponent;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.ihm18.bookmaker.presentation.centralcomponent.CentralModel;

import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 *
 * @author oualidqannouf
 */
public class HistoryPresenter implements Initializable {

	@Inject
	private CentralModel centralModel;

	@Inject
	private HistoryModel historyModel;
	
	@FXML
	private Button goBackButton;
	
    public void launch() {
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		goBackButton.disableProperty().bindBidirectional(historyModel.previousViewNotExistsProperty());
	}
	
	/**
	 * Permet d'afficher l'écran precedent.
	 * @param event event de click
	 */
	public void getPreviousMainView(ActionEvent event){
		historyModel.goBack();
		centralModel.setMainView(historyModel.getActiveView(), historyModel.getActiveViewTitle());
		System.out.println(historyModel.getActiveViewTitle());
	}
	

	

	
	
	

}
