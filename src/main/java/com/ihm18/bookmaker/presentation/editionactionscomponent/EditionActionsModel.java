package com.ihm18.bookmaker.presentation.editionactionscomponent;

import com.ihm18.bookmaker.presentation.customcontrols.TitledImage;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

/**
 * modéle du composant des éditions des images.
 * @author qannoufoualid
 *
 */
public class EditionActionsModel {

	/**
	 * L'imageView à éditer
	 */
	private  ObjectProperty<TitledImage> titledImageProperty = new SimpleObjectProperty<TitledImage>(null);

	public TitledImage getTitledImage() {
		return titledImageProperty.get();
	}

	public void setTitledImage(TitledImage titledImage) {
		this.titledImageProperty.set(titledImage);
	}

	public ObjectProperty<TitledImage> titledImageProperty(){
		return titledImageProperty;
	}

	
	
	
}
