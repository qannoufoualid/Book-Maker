package com.ihm18.bookmaker.presentation.centralcomponent;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

public class CentralModel {

    private final ObjectProperty<Node> mainView = new SimpleObjectProperty<Node>(this, "mainView", null);
    private final ObjectProperty<String> mainViewTitle = new SimpleObjectProperty<String>(this, "mainViewTitle", null);
    
    
    public ObjectProperty<Node> mainViewProperty() {
        return mainView ;
    }
    
    public ObjectProperty<String> mainViewTitleProperty() {
        return mainViewTitle ;
    }
    
    public final String getMainViewTitle() {
        return mainViewTitle.get();
    }
    
    public final void setMainView(Node mainView, String mainViewTitle) {
        this.mainView.set(mainView);
        this.mainViewTitle.set(mainViewTitle);
    }
    
}
