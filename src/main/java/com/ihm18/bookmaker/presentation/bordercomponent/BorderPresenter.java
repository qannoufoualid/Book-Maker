package com.ihm18.bookmaker.presentation.bordercomponent;


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

	}

	/**
	 * Place un cadre par défaut
	 */
	public void createBorder(ActionEvent event) {
		if(cadreBox.isSelected()){

		    editionActionsModel.getTitledImage().setBorderStyle("-fx-border-style: solid; ");
            editionActionsModel.getTitledImage().setBorderSize("-fx-border-width: 5px; ");
            editionActionsModel.getTitledImage().setBorderColor("-fx-border-color: green; ");
            editionActionsModel.getTitledImage().drawBorder();

			//StackPane pane = new StackPane(editionActionsModel.getImageView());
			//pane.setStyle("-fx-border-color: red; -fx-border-width:4; -fx-border-style:solid");

			//PseudoClass imageViewBorder = PseudoClass.getPseudoClass("border");

		}
		else{
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
					editionActionsModel.getTitledImage().setBorderStyle("-fx-border-style: solid; ");
					break;
				case 1:
					editionActionsModel.getTitledImage().setBorderStyle("-fx-border-style: dashed; ");
					break;
				case 2:
					editionActionsModel.getTitledImage().setBorderStyle("-fx-border-style: dotted; ");
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
            editionActionsModel.getTitledImage().setBorderColor("-fx-border-color: " + color + "; ");
            editionActionsModel.getTitledImage().drawBorder();
        }


	}

}
