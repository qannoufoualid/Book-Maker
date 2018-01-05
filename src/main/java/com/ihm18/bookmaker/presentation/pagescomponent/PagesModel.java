package com.ihm18.bookmaker.presentation.pagescomponent;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

/**
 * Le modéle du composant PagesComponent.
 * @author qannoufoualid
 *
 */
public class PagesModel {

	/**
	 * proprieté qui contient le conteneur de la palette
	 */
	private final ObjectProperty<Node> paletteView = new SimpleObjectProperty<Node>(this, "paletteView", null);
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
	 * getter de 
	 * @return
	 */
	public Property<Boolean> imageClickedProperty() {
		return imageClicked;
	}
	
	
	
}
