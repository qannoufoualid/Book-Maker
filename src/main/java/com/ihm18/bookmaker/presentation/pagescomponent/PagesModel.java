package com.ihm18.bookmaker.presentation.pagescomponent;

import com.airhacks.afterburner.views.FXMLView;
import com.ihm18.bookmaker.presentation.bordercomponent.BorderView;
import com.ihm18.bookmaker.presentation.brightnesspalettecomponent.BrightnessPaletteView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * Le modéle du composant PagesComponent.
 * @author qannoufoualid
 *
 */
public class PagesModel {


	/**
	 * fx:id=editPane
	 */
	@FXML
	private BorderPane editPane;

	/**
	 * proprieté qui contient le conteneur de la palette
	 */
	private final ObjectProperty<Node> paletteView = new SimpleObjectProperty<Node>(this, "paletteView", null);

	/**
	 * propriété qui contient le conteneur de cadre
	 */
	private final ObjectProperty<Node> borderView = new SimpleObjectProperty<Node>(this, "borderView", null);

	/**
	 * Boolean utilisé pour indiquer si une image à été dèjà cliquée afin d'activier/désactiver les boutons du composant EditionActions.
	 */
	private final Property<Boolean> imageClicked = new SimpleBooleanProperty(true);

	/**
	 * getter de la proprieté paletteView
	 * @return
	 */
	public ObjectProperty<Node> paletteViewProperty() {
		return paletteView;
	}

	/**
	 * getter de la proprieté borderView
	 * @return
	 */
	public ObjectProperty<Node> borderViewProperty(){
		return borderView;
	}

	public BorderPane getEditPane() {
		return editPane;
	}

	/**
	 * getter de 
	 * @return
	 */
	public Property<Boolean> imageClickedProperty() {
		return imageClicked;
	}

	/**
	 * setter de editPane
	 */
	public void setEditPane(BorderPane editPane){
		this.editPane = editPane;
	}
	
	
}
