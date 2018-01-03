package com.ihm18.bookmaker.presentation.customcontrols;

import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TitledImage extends VBox{

	@FXML private ImageView imageView;
	@FXML private TextField textField;


    public TitledImage() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ihm18/bookmaker/presentation/customcontrols/titledimage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return textField.textProperty();
    }
    public ObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }
    
    public void setImage(Image image){
    	imageProperty().set(image);
    }
    public Image getImage(){
    	return this.imageProperty().get();
    }
    
    
    public DoubleProperty fitHeightProperty() {
        return imageView.fitHeightProperty();
    }
    
    public void setFitHeight(double height){
    	fitHeightProperty().set(height);
    }
    
    public double getFitHeight(){
    	return this.fitHeightProperty().get();
    }
    
    public DoubleProperty fitWidthProperty() {
        return imageView.fitWidthProperty();
    }
    
    public void setFitWidth(double width){
    	fitWidthProperty().set(width);
    }
    
    public double getFitWidth(){
    	return this.fitWidthProperty().get();
    }
    
    
    @FXML
    protected void doSomething() {
        System.out.println("The button was clicked!");
    }
 
    
}
