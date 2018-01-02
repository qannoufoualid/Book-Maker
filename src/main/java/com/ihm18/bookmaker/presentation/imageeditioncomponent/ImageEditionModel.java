package com.ihm18.bookmaker.presentation.imageeditioncomponent;

import com.ihm18.bookmaker.businessobject.IHMImage;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

public class ImageEditionModel {

	private final ObjectProperty<Node> paletteView = new SimpleObjectProperty<Node>(this, "paletteView", null);

	private IHMImage image;

	public IHMImage getImage() {
		return image;
	}

	public void setImage(IHMImage image) {
		this.image = image;
	}

	public ObjectProperty<Node> paletteView() {
		return paletteView;
	}
	
	public final void setPaletteView(Node mainView) {
        this.paletteView.set(mainView);
    }
}
