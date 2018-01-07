package com.ihm18.bookmaker.presentation.bordercomponent;


import com.airhacks.afterburner.views.FXMLView;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

/**
 * La vue du composant pour gérer les cadres des images
 * @author zachizac
 *
 */
public class BorderView extends FXMLView {

    /**
     * fx:id=cadreBox
     */
    @FXML
    private CheckBox cadreBox;

    public void BorderView(){

    }

    public CheckBox getCadreBox(){
        return cadreBox;
    }

    public void cocheCadre(){
        cadreBox.setSelected(true);
    }

}
