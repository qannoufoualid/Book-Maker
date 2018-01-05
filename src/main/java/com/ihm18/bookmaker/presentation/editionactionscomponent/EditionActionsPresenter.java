package com.ihm18.bookmaker.presentation.editionactionscomponent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import com.ihm18.bookmaker.presentation.brightnesspalettecomponent.BrightnessPaletteView;
import com.ihm18.bookmaker.presentation.editionactionscomponent.EditionActionsModel;
import com.ihm18.bookmaker.presentation.pagescomponent.PagesModel;;
/**
 * Presentateur du composant des éditions des images.
 * @author oualidqannouf
 */
public class EditionActionsPresenter implements Initializable {

	/**
	 * Le modéle
	 */
	@Inject
	private PagesModel pagesModel;
	/**
	 * Le boutons des cadres.
	 */
	@FXML
	private Button cadreButton;
	/**
	 * Le boutons de luminosité
	 */
	@FXML
	private Button brightnessButton;

    /**
     * Initialiser le composant.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//cadreButton.disableProperty().bind(pagesModel.imageClickedProperty());
		//brightnessButton.disableProperty().bind(pagesModel.imageClickedProperty());
	}
	
	/**
	 * Affiche la palette du luminosité
	 * @param event
	 */
	public void displayBrightnessPalette(ActionEvent event){
		BrightnessPaletteView brightnessPaletteView = new BrightnessPaletteView();
		pagesModel.paletteViewProperty().set(brightnessPaletteView.getView());
	}
}
