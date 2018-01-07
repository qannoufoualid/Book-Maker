package com.ihm18.bookmaker.presentation.customcontrols;

import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Control personnalisé contenant une imageView + champ de texte + un bouton
 * 
 * @author qannoufoualid
 *
 */
public class TitledImage extends AnchorPane {

	/**
	 * constante qui represente combien la souri doit etre loin des bordures
	 * pour commencer l'élargissement/diminusion de la taille de l'image
	 */
	public static final double DIFF_RESIZE_CONSTANT = 20;

	/**
	 * L'imageView du control.
	 */
	@FXML
	private ImageView imageView;
	/**
	 * Le champ de text du control.
	 */
	@FXML
	private TextField textField;
	/**
	 * Le panneau pére du composant.
	 */
	private Pane parent;
	/**
	 * quantité de x deplacée
	 */
	private double mouvedx = 0;
	/**
	 * quantité de y deplacée
	 */
	private double mouvedy = 0;

	/**
	 * Position courante de la souris sur l'axe X
	 */
	private double mousex = 0;
	/**
	 * Position courante de la souris sur l'axe Y
	 */
	private double mousey = 0;
	/**
	 * Si il faut faire un elargissement/diminition sur l'axe X.
	 */
	private boolean widenToRight = false;
	/**
	 * Si il faut faire un elargissement/diminition sur l'axe Y.
	 */
	private boolean widenToBottom = false;
	/**
	 * largeur initiale de l'imageView.
	 */
	private double initialImageViewWidth;
	/**
	 * hauteur initiale de l'imageView.
	 */
	private double initialImageViewHeight;

	/**
	 * Style de la bordure
	 */
	private String borderStyle;

	/**
	 * Taille de la bordure
	 */
	private String borderSize;

	/**
	 * Couleur de la bordure
	 */
	private String borderColor;
	
	
	/**
	 * Constructeur sans des paramétres.
	 */
	public TitledImage() {
		this(null);
	}

	/**
	 * Créer un object TitledImage avec reference sur son pére.
	 * @param parent
	 */
	public TitledImage(Pane parent) {
		this.parent = parent;
		
		
		
		
		double initialParentWidth = parent.widthProperty().get();
		double initialParentHeight = parent.heightProperty().get();

		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/com/ihm18/bookmaker/presentation/customcontrols/titledimage.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		textField.setAlignment(Pos.CENTER);
		
		// EventListener for MousePressed
		imageView.onMousePressedProperty().set(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				// record the current mouse X and Y position on Node
				mousex = event.getSceneX();
				mousey = event.getSceneY();
				double relativeMouseX = event.getX();
				double relativeMouseY = event.getY();

				// get the x and y position measure from Left-Top
				mouvedx = getLayoutX();
				mouvedy = getLayoutY();

				widenToRight = false;
				widenToBottom = false;
				if (getFitWidth() > relativeMouseX && getFitWidth() - DIFF_RESIZE_CONSTANT < relativeMouseX) {
					widenToRight = true;
				}
				if (getFitHeight() > relativeMouseY && getFitHeight() - DIFF_RESIZE_CONSTANT < relativeMouseY) {
					widenToBottom = true;
				}
			}
		});

		// Event Listener for MouseDragged
		imageView.onMouseDraggedProperty().set(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				textField.setPrefWidth(imageView.getFitWidth());
				
				// Get the exact moved X and Y
				mouvedx += event.getSceneX() - mousex;
				mouvedy += event.getSceneY() - mousey;

				double dx = event.getSceneX() - mousex;
				double dy = event.getSceneY() - mousey;

				if (!widenToRight && !widenToBottom) {

					double initialParentWidth = parent.widthProperty().get();
					double initialParentHeight = parent.heightProperty().get();
					
					// set the positon of Node after calculation
					if (parent != null && (parent.getLayoutX() < mouvedx
							&& parent.getLayoutX() + initialParentWidth - widthProperty().get() > mouvedx)) {
						setLayoutX(mouvedx);
					}
					if (parent != null && (parent.getLayoutY() < mouvedy
							&& parent.getLayoutY() + initialParentHeight - heightProperty().get() > mouvedy)) {
						setLayoutY(mouvedy);
					}

					// again set current Mouse x AND y position
					mousex = event.getSceneX();
					mousey = event.getSceneY();

				} else {
					double newWidth = initialImageViewWidth + dx;
					double newHeight = initialImageViewHeight + dy;

					if (widenToRight && newWidth <= parent.getWidth())
						imageView.setFitWidth(newWidth);
					if (widenToBottom && newHeight <= parent.getHeight())
						imageView.setFitHeight(newHeight);
				}
			}
		});
		// Event Listener for MouseReleased
		imageView.onMouseReleasedProperty().set(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				textField.setPrefWidth(imageView.getFitWidth());
				if (widenToRight || widenToBottom) {
					initialImageViewWidth = imageView.getFitWidth();
					initialImageViewHeight = imageView.getFitHeight();
				}
			}
		});
		// Event Listener for Mouseover
		imageView.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				TitledImage.this.getParent().getScene().setCursor(Cursor.DEFAULT);
				double relativeMouseX = event.getX();
				double relativeMouseY = event.getY();
				if (getFitWidth() > relativeMouseX && getFitWidth() - DIFF_RESIZE_CONSTANT < relativeMouseX) {
					TitledImage.this.getParent().getScene().setCursor(Cursor.E_RESIZE);
				} else if (getFitHeight() > relativeMouseY && getFitHeight() - DIFF_RESIZE_CONSTANT < relativeMouseY) {
					TitledImage.this.getParent().getScene().setCursor(Cursor.S_RESIZE);
				}
			}
		});

		// Event Listener for Mouseover
		imageView.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				TitledImage.this.getParent().getScene().setCursor(Cursor.DEFAULT);
			}
		});
	}

	/**
	 * récuperer la proprieté text du champ de text.
	 * @return
	 */
	public String getText() {
		return textProperty().get();
	}

	/**
	 * Spécifier la proprieté text du champ de text.
	 * @param value
	 */
	public void setText(String value) {
		textProperty().set(value);
	}

	/**
	 * Spécifier la proprieté text du champ de text.
	 * @param value
	 */
	public StringProperty textProperty() {
		return textField.textProperty();
	}

	/**
	 * Récupére la proprieté image de l'imageView
	 * @return
	 */
	public ObjectProperty<Image> imageProperty() {
		return imageView.imageProperty();
	}
	/**
	 * Spécifier la proprieté image de l'imageView.
	 * @param value
	 */
	public void setImage(Image image) {
		imageProperty().set(image);
		initialImageViewWidth = imageView.getFitWidth();
		initialImageViewHeight = imageView.getFitHeight();
	}

	/**
	 * Récupére l'image de l'imageView
	 * @return
	 */
	public Image getImage() {
		return this.imageProperty().get();
	}

	/**
	 *  récuperer la fitHeightProperty.
	 * @return
	 */
	public DoubleProperty fitHeightProperty() {
		return imageView.fitHeightProperty();
	}

	public void setFitHeight(double height) {
		initialImageViewHeight = height;
		fitHeightProperty().set(height);
	}

	public double getFitHeight() {
		return this.fitHeightProperty().get();
	}

	/**
	 *  récuperer la fitWidthProperty.
	 * @return
	 */
	public DoubleProperty fitWidthProperty() {
		return imageView.fitWidthProperty();
	}

	public void setFitWidth(double width) {
		initialImageViewWidth = width;
		textField.setPrefWidth(width);
		fitWidthProperty().set(width);
	}

	public double getFitWidth() {
		return this.fitWidthProperty().get();
	}

	/**
	 * getter de l'imageView
	 * @return
	 */
	public ImageView getImageView() {
		return imageView;
	}

	public TextField getTextField() {
		return textField;
	}

	public void setTextField(TextField textField) {
		this.textField = textField;
	}


	public void setBorderStyle(String borderStyle) {
		this.borderStyle = borderStyle;
	}

	public void setBorderSize(String borderSize) {
		this.borderSize = borderSize;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getBorderStyle() {
		return borderStyle;
	}

	public String getBorderSize() {
		return borderSize;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void drawBorder(){
		this.getStyleClass().clear();
		this.setStyle(this.borderStyle + this.borderSize + this.borderColor);
	}

	public void clearBorder(){
		this.borderStyle = "";
		this.borderSize = "";
		this.borderColor = "";
		drawBorder();
	}
}
