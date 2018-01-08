package com.ihm18.bookmaker.presentation.centralcomponent;

import javax.inject.Inject;

import com.ihm18.bookmaker.presentation.historycomponent.HistoryModel;
import com.ihm18.bookmaker.presentation.historycomponent.HistoryPresenter;
import com.ihm18.bookmaker.presentation.welcomecomponent.WelcomeView;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

/**
 * Modele du composant Central
 * @author qannoufoualid
 *
 */
public class CentralModel {

	@Inject 
	private HistoryModel historyModel;
	/**
	 * La vue affichée dans le centre.
	 */
    private final ObjectProperty<Node> mainView = new SimpleObjectProperty<Node>(this, "mainView", null);
    /**
	 * La vue affichée dans le bas.
	 */
    private final ObjectProperty<Node> bottomView = new SimpleObjectProperty<Node>(this, "bottomView", null);
    /**
     * Le titre de la vue affichée dans le centre.
     */
    private final ObjectProperty<String> mainViewTitle = new SimpleObjectProperty<String>(this, "mainViewTitle", null);
    
    /**
     * Getter de la proprieté mainViewProperty
     * @return mainViewProperty
     */
    public ObjectProperty<Node> mainViewProperty() {
        return mainView ;
    }
    
    /**
     * Getter de la proprieté mainViewTitleProperty
     * @return mainViewTitleProperty
     */
    public ObjectProperty<String> mainViewTitleProperty() {
        return mainViewTitle ;
    }
    
    /**
    * Getter du titre de la mainView
    * @return getMainViewTitle
    */
    public final String getMainViewTitle() {
        return mainViewTitle.get();
    }
    
    /**
     * Setter de la mainView avec son titre.
     * @param mainView la mainView 
     * @param mainViewTitle le titre
     */
    public final void setMainView(Node mainView, String mainViewTitle) {
        this.mainView.set(mainView);
        this.mainViewTitle.set(mainViewTitle);
        historyModel.add(mainView, mainViewTitle);
    }
    
    /**
     * Getter de la proprieté bottomViewProperty
     * @return bottomViewProperty bottomViewProperty
     */
    public ObjectProperty<Node> bottomViewProperty() {
        return bottomView ;
    }
    
    /**
     * Getter de la proprieté bottomViewProperty
     * @return bottomViewProperty bottomViewProperty
     */
    public void setBottomView(Node bottomView) {
        this.bottomViewProperty().set(bottomView);
    }
    
}
