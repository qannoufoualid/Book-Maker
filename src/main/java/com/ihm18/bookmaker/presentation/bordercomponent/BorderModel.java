package com.ihm18.bookmaker.presentation.bordercomponent;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * modéle du composant d'édition des cadres
 * @author zachizac
 *
 */
public class BorderModel {

    /**
     * fx:id=cadreBox
     */
    @FXML
    private CheckBox cadreBox;

    public CheckBox getCadreBox(){
        return cadreBox;
    }
	
}
