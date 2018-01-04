package com.ihm18.bookmaker.presentation.pagescomponent;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

public class PagesModel {

	private final ObjectProperty<Node> paletteView = new SimpleObjectProperty<Node>(this, "paletteView", null);
	private final Property<Boolean> imageClicked = new SimpleBooleanProperty(true);
	
	public ObjectProperty<Node> paletteView() {
		return paletteView;
	}
	
	public final void setPaletteView(Node mainView) {
        this.paletteView.set(mainView);
    }

	public ObjectProperty<Node> getPaletteView() {
		return paletteView;
	}

	public Property<Boolean> imageClickedProperty() {
		return imageClicked;
	}
	
	
	
}
