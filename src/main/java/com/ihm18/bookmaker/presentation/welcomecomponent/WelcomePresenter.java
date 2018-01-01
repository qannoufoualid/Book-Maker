package com.ihm18.bookmaker.presentation.welcomecomponent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.inject.Inject;

import com.ihm18.bookmaker.presentation.albumformcomponent.AlbumFormView;
import com.ihm18.bookmaker.presentation.centralcomponent.CentralModel;

/**
 *
 * @author oualidqannouf
 */
public class WelcomePresenter implements Initializable {

	@Inject
	private CentralModel centralModel;

	public void launch() {
		System.out.println("WelcomePresenter started");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void displayAlbumForm(ActionEvent event) {
		AlbumFormView albumFormView = new AlbumFormView();
		centralModel.setMainView(albumFormView.getView(), AlbumFormView.TITLE);
	}

}
