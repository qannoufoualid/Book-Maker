package com.ihm18.bookmaker.presentation.centralcomponent;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

/**
 * Modele du composant Central
 * @author qannoufoualid
 *
 */
public class CentralModel {

	/**
	 * La vue affichée dans le centre.
	 */
    private final ObjectProperty<Node> mainView = new SimpleObjectProperty<Node>(this, "mainView", null);
    /**
     * Le titre de la vue affichée dans le centre.
     */
    private final ObjectProperty<String> mainViewTitle = new SimpleObjectProperty<String>(this, "mainViewTitle", null);
    
    /**
     * Getter de la proprieté mainViewProperty
     * @return
     */
    public ObjectProperty<Node> mainViewProperty() {
        return mainView ;
    }
    
    /**
     * Getter de la proprieté mainViewTitleProperty
     * @return
     */
    public ObjectProperty<String> mainViewTitleProperty() {
        return mainViewTitle ;
    }
    
    /**
    * Getter du titre de la mainView
    * @return
    */
    public final String getMainViewTitle() {
        return mainViewTitle.get();
    }
    
    /**
     * Setter de la mainView avec son titre.
     * @param mainView
     * @param mainViewTitle
     */
    public final void setMainView(Node mainView, String mainViewTitle) {
        this.mainView.set(mainView);
        this.mainViewTitle.set(mainViewTitle);
    }
    
}
