package com.ihm18.bookmaker.presentation.brightnesspalettecomponent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.inject.Inject;

import com.ihm18.bookmaker.presentation.editionactionscomponent.EditionActionsModel;
import com.ihm18.bookmaker.presentation.pagescomponent.PagesModel;

/**
 * Le presentateur du composant responsable sur la luminosité + saturation + sepia
 * @author qannoufoualid
 *
 */
public class BrightnessPalettePresenter implements Initializable {


	/**
	 * Le modéle du composant des actions des éditions des images.
	 */
	@Inject
	private EditionActionsModel editionActionsModel;
	
	/**
	 * Le modéle du composant des pages.
	 */
	@Inject
	private PagesModel pagesModel;
	/**
	 * fx:id=brightnessSlider
	 */
	@FXML
	private Slider brightnessSlider;
	/**
	 * fx:id=saturationSlider
	 */
	@FXML
	private Slider saturationSlider;
	/**
	 * fx:id=sepiaSlider
	 */
	@FXML
	private Slider sepiaSlider;
	
	public void launch() {
	}

	/**
	 * Permet d'initialiser le composant.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		brightnessSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                        setImageEffect();
                }
            });
		saturationSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                        setImageEffect();
                }
            });
		sepiaSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                        setImageEffect();
                }
            });
		
		pagesModel.imageClickedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				brightnessSlider.setValue(1);
				saturationSlider.setValue(1);
				sepiaSlider.setValue(1);
			}
		});
	}

	/**
	 * Changer les effets de l'image.
	 */
	private void setImageEffect() {
		ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness((1 - brightnessSlider.valueProperty().get()) * -1);
        colorAdjust.setSaturation(saturationSlider.valueProperty().get());

        SepiaTone st = new SepiaTone();
        st.setLevel(sepiaSlider.valueProperty().get());

        colorAdjust.setInput(st);
        if(editionActionsModel.getTitledImage() != null)
        	editionActionsModel.getTitledImage().getImageView().setEffect(colorAdjust);
	    }

}
