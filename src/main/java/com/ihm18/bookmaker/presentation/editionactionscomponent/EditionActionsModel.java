package com.ihm18.bookmaker.presentation.editionactionscomponent;

import javafx.scene.image.ImageView;

/**
 * modéle du composant des éditions des images.
 * @author qannoufoualid
 *
 */
public class EditionActionsModel {

	/**
	 * L'imageView à éditer
	 */
	private  ImageView imageView;

	/**
	 * getter de l'imageView
	 * @return
	 */
	public ImageView getImageView() {
		return imageView;
	}

	/**
	 * Setter de l'imageView
	 * @param imageView
	 */
	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	
}
