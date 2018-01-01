package com.ihm18.bookmaker.presentation.emptycomponent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.inject.Inject;

/**
 *
 * @author oualidqannouf
 */
public class EmptyPresenter implements Initializable {

	@FXML
	private GridPane pagesContainer;


    public void launch() {
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println(pagesContainer);
	}

}
