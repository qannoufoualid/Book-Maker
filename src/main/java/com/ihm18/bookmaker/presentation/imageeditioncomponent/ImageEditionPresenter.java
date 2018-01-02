package com.ihm18.bookmaker.presentation.imageeditioncomponent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author oualidqannouf
 */
public class ImageEditionPresenter implements Initializable {

	@FXML
	private ImageView imageView;
	@Inject
	private ImageEditionModel imageEditionModel;

    public void launch() {
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image image;
		System.out.println(imageEditionModel.getImage());
		try {
			image = new Image(FileUtils.openInputStream(imageEditionModel.getImage().getFile()));
			imageView.setImage(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
