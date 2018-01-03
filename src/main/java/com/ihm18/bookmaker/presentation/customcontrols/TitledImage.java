package com.ihm18.bookmaker.presentation.customcontrols;

import java.io.IOException;

import com.ihm18.bookmaker.businessobject.IHMImage;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TitledImage extends VBox{

	@FXML private ImageView imageView;
	@FXML private TextField textField;
	Pane parent;
    double x = 0;
    double y = 0;
    
    double mousex=0;
    double mousey=0;

    public TitledImage() {
		this(null);
	}
    public TitledImage(Pane parent) {
    	this.parent = parent;
    	double initialWith = parent.widthProperty().get();
    	double initialHeight = parent.heightProperty().get();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ihm18/bookmaker/presentation/customcontrols/titledimage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
      //EventListener for MousePressed
        imageView.onMousePressedProperty().set(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
               //record the current mouse X and Y position on Node
               mousex = event.getSceneX();
               mousey= event.getSceneY();
               //get the x and y position measure from Left-Top
               x = getLayoutX();
               y = getLayoutY();
            }

        });

        //Event Listener for MouseDragged
        imageView.onMouseDraggedProperty().set(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                //Get the exact moved X and Y
                x += event.getSceneX()-mousex ;
                y += event.getSceneY()-mousey ;

                //set the positon of Node after calculation
                if(parent!=null && (parent.getLayoutX()<x && parent.getLayoutX()+initialWith-widthProperty().get() > x)){
                	setLayoutX(x);
                }
                if(parent!=null && (parent.getLayoutY()<y && parent.getLayoutY()+initialHeight-heightProperty().get() > y)){
                	setLayoutY(y);
                }

                //again set current Mouse x AND y position
                mousex = event.getSceneX();
                mousey= event.getSceneY();

            }
        });
        
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

	public ImageView getImageView() {
		
		return imageView;
	}
    
}
