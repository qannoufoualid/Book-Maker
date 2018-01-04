package com.ihm18.bookmaker.presentation.customcontrols;

import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TitledImage extends AnchorPane {

	protected static final double DIFF_RESIZE_CONSTANT = 20;
	@FXML
	private ImageView imageView;
	@FXML
	private TextField textField;
	Pane parent;
	double x = 0;
	double y = 0;

	double mousex = 0;
	double mousey = 0;
	protected boolean widenToRight = false;
	protected boolean widenToBottom = false;
	private double initialImageViewWidth;
	private double initialImageViewHeight;

	public TitledImage() {
		this(null);
	}

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
				x = getLayoutX();
				y = getLayoutY();

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

				// Get the exact moved X and Y
				x += event.getSceneX() - mousex;
				y += event.getSceneY() - mousey;

				double dx = event.getSceneX() - mousex;
				double dy = event.getSceneY() - mousey;

				if (!widenToRight && !widenToBottom) {

					// set the positon of Node after calculation
					if (parent != null && (parent.getLayoutX() < x
							&& parent.getLayoutX() + initialParentWidth - widthProperty().get() > x)) {
						setLayoutX(x);
					}
					if (parent != null && (parent.getLayoutY() < y
							&& parent.getLayoutY() + initialParentHeight - heightProperty().get() > y)) {
						setLayoutY(y);
					}

					// again set current Mouse x AND y position
					mousex = event.getSceneX();
					mousey = event.getSceneY();

				} else {
					// System.out.println(initialImageViewHeight+",
					// "+initialImageViewWidth);
					System.out.println(x + "," + y);
					// System.out.println(initialImageViewWidth+x);
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
				}
				else if (getFitHeight() > relativeMouseY && getFitHeight() - DIFF_RESIZE_CONSTANT < relativeMouseY) {
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

	public void setImage(Image image) {
		imageProperty().set(image);
		initialImageViewWidth = imageView.getFitWidth();
		initialImageViewHeight = imageView.getFitHeight();
	}

	public Image getImage() {
		return this.imageProperty().get();
	}

	public DoubleProperty fitHeightProperty() {
		return imageView.fitHeightProperty();
	}

	public void setFitHeight(double height) {
		fitHeightProperty().set(height);
	}

	public double getFitHeight() {
		return this.fitHeightProperty().get();
	}

	public DoubleProperty fitWidthProperty() {
		return imageView.fitWidthProperty();
	}

	public void setFitWidth(double width) {
		fitWidthProperty().set(width);
	}

	public double getFitWidth() {
		return this.fitWidthProperty().get();
	}

	public ImageView getImageView() {
		return imageView;
	}

}
