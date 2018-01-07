package com.ihm18.bookmaker.presentation.historycomponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

/**
 * Le modéle du composant HistoryComponent.
 * @author qannoufoualid
 *
 */
public class HistoryModel {

	List<Node> previousViews = new ArrayList<Node>();
	List<String> previousViewsTitles = new ArrayList<String>();
	int activeViewIndex = -1;
	final Property<Boolean> previousViewNotExists = new SimpleBooleanProperty(true);
    final Property<Boolean> nextViewNotExists = new SimpleBooleanProperty(true);
	
	public List<String> getPreviousViewsTitles() {
		return previousViewsTitles;
	}

	public void setPreviousViewsTitles(List<String> previousViewsTitles) {
		this.previousViewsTitles = previousViewsTitles;
	}

	public List<Node> getPreviousViews() {
		return previousViews;
	}

	public void setPreviousViews(List<Node> previousViews) {
		this.previousViews = previousViews;
	}

	public void setActiveViewIndex(int activeView) {
		this.activeViewIndex = activeView;
	}

	public Node getActiveView(){
		return previousViews.get(activeViewIndex);
	}
	public String getActiveViewTitle(){
		return previousViewsTitles.get(activeViewIndex);
	}

	public Property<Boolean> previousViewNotExistsProperty() {
		return previousViewNotExists;
	}

	public Property<Boolean> nextViewNotExistsProperty() {
		return nextViewNotExists;
	}
	
	public void add(Node mainView, String mainViewTitle) {
		if(!previousViews.contains(mainView)){
			activeViewIndex++;
			if(activeViewIndex > 0)
				previousViewNotExists.setValue(false);
			previousViews.add(mainView);
			previousViewsTitles.add(mainViewTitle);
		}
		
	}
	
	public void goBack(){
		activeViewIndex--;
		if(activeViewIndex<=0){
			previousViewNotExistsProperty().setValue(true);
		}
	}
	
}
