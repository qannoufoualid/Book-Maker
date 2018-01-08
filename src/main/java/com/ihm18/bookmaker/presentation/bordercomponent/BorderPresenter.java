package com.ihm18.bookmaker.presentation.bordercomponent;


import com.ihm18.bookmaker.presentation.customcontrols.TitledImage;
import com.ihm18.bookmaker.presentation.editionactionscomponent.EditionActionsModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.String.format;

/**
 * Le presentateur du composant de gestion des cadres
 * @author zachizac
 *
 */
public class BorderPresenter implements Initializable {

	/**
	 * Le modéle du composant des actions des éditions des images.
	 */
	@Inject
	private EditionActionsModel editionActionsModel;

	/**
	 * fx:id=cadreBox
	 */
	@FXML
	private CheckBox cadreBox;

	/**
	 * fx:id=colorPicker
	 */
	@FXML
	private ColorPicker colorPicker;

	/**
	 * fx:id=selectStyle
	 */
	@FXML
	private ChoiceBox selectStyle;

	private final DropShadow shadow = new DropShadow(20, Color.BLACK);


	/**
	 * Permet d'initialiser le composant.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	    selectStyle.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
	    	public void changed(ObservableValue ov, Number value, Number new_value){
	    		changeStyleBorder(new_value);
			}
		});

		editionActionsModel.titledImageProperty().addListener(new ChangeListener<TitledImage>() {

			@Override
			public void changed(ObservableValue<? extends TitledImage> observable, TitledImage oldValue, TitledImage newValue) {
				if(newValue == null){
					cadreBox.setSelected(false);
					colorPicker.setValue(Color.BLACK);
					selectStyle.setValue("Trait");

				}else{
					if(!editionActionsModel.getTitledImage().isBordered()) {
						cadreBox.setSelected(false);
						colorPicker.setValue(Color.BLACK);
						selectStyle.setValue("Trait");
						colorPicker.setDisable(true);
						selectStyle.setDisable(true);
					}else {
						cadreBox.setSelected(true);
						recupBorder();
						colorPicker.setDisable(false);
						selectStyle.setDisable(false);
					}
				}
			}
		});

	}

	/**
	 * fonction pour récupérer les bordure d'une image
	 */
	public void recupBorder(){
		if(editionActionsModel.getTitledImage().getBorderStyle().equals("solid")){
			selectStyle.setValue("Trait");
		}else{
			if(editionActionsModel.getTitledImage().getBorderStyle().equals("dashed")){
				selectStyle.setValue("Tiret");
			}else{
				if(editionActionsModel.getTitledImage().getBorderStyle().equals("dotted")){
					selectStyle.setValue("Pointillé");
				}
			}
		}
		colorPicker.setValue(editionActionsModel.getTitledImage().getBorderColorObject());
	}

	/**
	 * Place un cadre par défaut
	 */
	public void createBorder(ActionEvent event) {
		if(cadreBox.isSelected()){
			if(editionActionsModel.getTitledImage().getBorderSize()==null) {
				editionActionsModel.getTitledImage().setBorderStyle("solid");
				editionActionsModel.getTitledImage().setBorderColor("black; ");
				editionActionsModel.getTitledImage().setBorderColorObject(Color.BLACK);
			}else{
				recupBorder();
			}
			editionActionsModel.getTitledImage().setBorderSize("10px");
			colorPicker.setDisable(false);
			selectStyle.setDisable(false);
			editionActionsModel.getTitledImage().setBorder(true);
			editionActionsModel.getTitledImage().drawBorder();
		}
		else{
			colorPicker.setDisable(true);
			selectStyle.setDisable(true);
			editionActionsModel.getTitledImage().clearBorder();
		}
	}

	/**
	 * Change le style de la bordure
	 */
	public void changeStyleBorder(Number n){
		if(cadreBox.isSelected()){
			switch (n.intValue()){
				case 0:
					editionActionsModel.getTitledImage().setBorderStyle("solid");
					break;
				case 1:
					editionActionsModel.getTitledImage().setBorderStyle("dashed");
					break;
				case 2:
					editionActionsModel.getTitledImage().setBorderStyle("dotted");
					break;
				default:
					break;
			}
			editionActionsModel.getTitledImage().drawBorder();
		}
	}

	/**
	 * Changer la couleur de la bordure
	 */
	public void changeColorBorder(ActionEvent event){
        if(cadreBox.isSelected()){
			String color = String.format( "#%02X%02X%02X",
					(int)( colorPicker.getValue().getRed() * 255 ),
					(int)( colorPicker.getValue().getGreen() * 255 ),
					(int)( colorPicker.getValue().getBlue() * 255 ) );
            editionActionsModel.getTitledImage().setBorderColor(color);
            editionActionsModel.getTitledImage().setBorderColorObject(colorPicker.getValue());
            editionActionsModel.getTitledImage().drawBorder();
        }


	}


}
