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
 *
 * @author oualidqannouf
 */
public class EditionActionsPresenter implements Initializable {

	
	@Inject
	private PagesModel pagesModel;
	
	@FXML
	private Button cadreButton;
	@FXML
	private Button brightnessButton;
	@FXML
	private Button borderButton;
	
    public void launch() {
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cadreButton.disableProperty().bind(pagesModel.imageClickedProperty());
		brightnessButton.disableProperty().bind(pagesModel.imageClickedProperty());
		borderButton.disableProperty().bind(pagesModel.imageClickedProperty());
		
	}
	
	
	public void displayBrightnessPalette(ActionEvent event){
		BrightnessPaletteView brightnessPaletteView = new BrightnessPaletteView();
		pagesModel.setPaletteView(brightnessPaletteView.getView());
	}
}
