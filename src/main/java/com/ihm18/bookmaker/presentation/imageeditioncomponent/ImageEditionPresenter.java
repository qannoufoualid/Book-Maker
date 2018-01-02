package com.ihm18.bookmaker.presentation.imageeditioncomponent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import com.ihm18.bookmaker.presentation.brighnesspalettecomponent.BrightnessPaletteView;
import com.ihm18.bookmaker.presentation.imageeditioncomponent.ImageEditionModel;;
/**
 *
 * @author oualidqannouf
 */
public class ImageEditionPresenter implements Initializable {

	@FXML
	private ImageView imageView;
	@Inject
	private ImageEditionModel imageEditionModel;
	@FXML
	private BorderPane imageEditionContainer;
	
    public void launch() {
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		imageEditionContainer.rightProperty().bind(imageEditionModel.paletteView());
		
		Image image;
		System.out.println(imageEditionModel.getImage());
		try {
			image = new Image(FileUtils.openInputStream(imageEditionModel.getImage().getFile()));
			imageView.setImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void displayBrightnessPalette(ActionEvent event){
		BrightnessPaletteView brightnessPaletteView = new BrightnessPaletteView();
		imageEditionModel.setPaletteView(brightnessPaletteView.getView());
	}
}
