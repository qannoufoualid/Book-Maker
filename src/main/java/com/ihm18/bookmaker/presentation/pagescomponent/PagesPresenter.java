package com.ihm18.bookmaker.presentation.pagescomponent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;

import com.ihm18.bookmaker.businessobject.Album;
import com.ihm18.bookmaker.businessobject.IHMImage;
import com.ihm18.bookmaker.businessobject.Page;
import com.ihm18.bookmaker.presentation.albumdetailcomponent.AlbumDetailModel;
import com.ihm18.bookmaker.presentation.brightnesspalettecomponent.BrightnessModel;
import com.ihm18.bookmaker.presentation.centralcomponent.CentralModel;
import com.ihm18.bookmaker.presentation.customcontrols.TitledImage;
import com.ihm18.bookmaker.presentation.editionactionscomponent.EditionActionsModel;
import com.ihm18.bookmaker.presentation.editionactionscomponent.EditionActionsView;
import com.ihm18.bookmaker.presentation.utility.SoundPlayer;
import com.ihm18.bookmaker.presentation.utility.Utility;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author oualidqannouf
 */
public class PagesPresenter implements Initializable {

	@Inject
	private AlbumDetailModel albumDetailModel;

	@Inject
	private PagesModel pagesModel;
	@Inject
	private EditionActionsModel editionActionsModel;
	
	
	@Inject
	private Utility utility;
	@Inject
	private SoundPlayer soundPlayer;

	private Album album;

	@FXML
	private BorderPane pagesContainer;
	@FXML
	private ImageView leftSideImageView;
	@FXML
	private ImageView rightSideImageView;
	@FXML
	private Label leftPageNumberLabel;
	@FXML
	private Label rightPageNumberLabel;
	@FXML
	private AnchorPane leftAnchorPane;
	@FXML
	private AnchorPane rightAnchorPane;

	private ImageView[] leftImageViews;
	private ImageView[] rightImageViews;

	private int activePageNumber = 0;
	private IHMImage activeImage;
	
	private int pagesNumber;
	protected boolean imageClicked = false;;

	public void launch() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		pagesContainer.topProperty().set(new EditionActionsView().getView());
		pagesContainer.rightProperty().bind(pagesModel.paletteView());
		
		leftImageViews = new ImageView[] {};
		rightImageViews = new ImageView[] {};
		album = albumDetailModel.getAlbum();
		pagesNumber = album.getPages().size();
		activePageNumber = (pagesNumber > 0) ? activePageNumber = 1 : 0;
		updateBackground();

		initDragEvents();

		
		leftAnchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!imageClicked)
					pagesModel.imageClickedProperty().setValue(true);
				imageClicked = false;
			}
		});
		
	}

	private void initDragEvents() {
		initDragEventsOnLeftSide();
		initDragEventsOnRightSide();
	}

	private void initDragEventsOnLeftSide() {
		leftAnchorPane.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.ANY);

				event.consume();
			}
		});

		leftAnchorPane.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				final boolean isAccepted = db.hasFiles()
						&& (db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
								|| db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
								|| db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg"));

				if (isAccepted) {
					File imageFile = db.getFiles().get(0);
					acceptImageAtSide(true, imageFile);
					updateBackground();
				} else {
					event.consume();
				}
			}
		});
	}

	private void initDragEventsOnRightSide() {
		rightAnchorPane.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.ANY);

				event.consume();
			}
		});

		rightAnchorPane.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				final boolean isAccepted = db.hasFiles()
						&& (db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
								|| db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
								|| db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg"));

				if (isAccepted) {
					File imageFile = db.getFiles().get(0);
					acceptImageAtSide(false, imageFile);
					updateBackground();
				} else {
					event.consume();
				}
			}
		});
	}

	public void acceptImageAtSide(boolean isLeft, File imageFile) {
		IHMImage image = new IHMImage(imageFile.getName(), "", LocalDateTime.now());
		image.setFile(imageFile);
		Page page = album.getPages().get((isLeft) ? activePageNumber - 1 : activePageNumber);
		page.getImages().add(image);
		image.setPage(page);
		
		TitledImage titledImage = createTitledImage(image);
		if(isLeft)
			leftAnchorPane.getChildren().add(titledImage);
		else
			rightAnchorPane.getChildren().add(titledImage);
	}

	private TitledImage createTitledImage(IHMImage image) {
		TitledImage titledImage = new TitledImage(leftAnchorPane);
		titledImage.setFitHeight(150);
		titledImage.setFitWidth(150);
		Image img;
		try {
			img = new Image(FileUtils.openInputStream(image.getFile()));
			titledImage.setImage(img);
			titledImage.setText(image.getTitle());
		} catch (IOException e) {
			e.printStackTrace();
		}
		titledImage.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("image clicked");
				imageClicked = true;
				activeImage = image;
				editionActionsModel.setImageView(titledImage.getImageView());
				pagesModel.imageClickedProperty().setValue(false);
			}
		});
		return titledImage;
		
	}

	public void addPage(ActionEvent event) {
		attachPage();
		activePageNumber = (pagesNumber % 2 == 0) ? pagesNumber - 1 : pagesNumber;
		updateBackground();
	}

	/**
	 * Cette fonction permet d'actualiser les images d'une page.
	 * 
	 * @param isLeftView
	 *            true s'il s'agit de la vue à gauche(page à gauche)
	 * @param pageNumber
	 *            la page de l'album
	 * @throws IOException
	 */
	public void fillViewWithPage(int pageNumber) {

		if (pageNumber % 2 != 0) {
			clearPane(leftAnchorPane);
			Page page = album.getPages().get(pageNumber - 1);
			for (int i = 0; i < page.getImages().size(); i++) {
				IHMImage ihmImage = page.getImages().get(i);
				Image image;
				try {
					image = new Image(FileUtils.openInputStream(ihmImage.getFile()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				TitledImage titledImage = createTitledImage(ihmImage);
				leftAnchorPane.getChildren().add(titledImage);
			}
		} else {
			clearPane(rightAnchorPane);
			Page page = album.getPages().get(pageNumber - 1);
			for (int i = 0; i < page.getImages().size(); i++) {
				IHMImage ihmImage = page.getImages().get(i);
				Image image;
				try {
					image = new Image(FileUtils.openInputStream(ihmImage.getFile()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				TitledImage titledImage = createTitledImage(ihmImage);
				rightAnchorPane.getChildren().add(titledImage);
			}
		}

	}

	private void clearPane(AnchorPane pane) {
		pane.getChildren().clear();
	}

	private void attachPage() {

		Page page = new Page();
		page.setNumber(++pagesNumber);
		page.setAlbum(album);
		album.addPage(page);
	}

	private void updateBackground() {

		resetBackground();

		if (activePageNumber == pagesNumber && pagesNumber != 0) {
			Image image = utility.getFXImage("left-page.png");
			leftSideImageView.setImage(image);

			leftPageNumberLabel.setText(String.valueOf(pagesNumber));
			rightPageNumberLabel.setText("");
			fillViewWithPage(activePageNumber);
		} else if (activePageNumber < pagesNumber) {
			Image image = utility.getFXImage("left-page.png");
			leftSideImageView.setImage(image);
			image = utility.getFXImage("right-page.png");
			rightSideImageView.setImage(image);

			leftPageNumberLabel.setText(String.valueOf(activePageNumber));
			rightPageNumberLabel.setText(String.valueOf(activePageNumber + 1));
			fillViewWithPage(activePageNumber);
			fillViewWithPage(activePageNumber + 1);
		}
	}

	private void resetBackground() {
		clearPane(rightAnchorPane);
		clearPane(leftAnchorPane);
		Image image = utility.getFXImage("left-side.png");
		leftSideImageView.setImage(image);
		image = utility.getFXImage("right-side.png");
		rightSideImageView.setImage(image);
		for (ImageView imageView : leftImageViews)
			imageView.setImage(null);
		for (ImageView imageView : rightImageViews)
			imageView.setImage(null);
	}

	public void turnLeft(ActionEvent event) {
		if (activePageNumber - 2 >= 1) {
			activePageNumber = activePageNumber - 2;
			soundPlayer.playSound("page-flip");
			updateBackground();
		}
		
	}

	public void turnRight(ActionEvent event) {
		if (activePageNumber + 2 <= pagesNumber) {
			soundPlayer.playSound("page-flip");
			activePageNumber = activePageNumber + 2;
			updateBackground();
		}
		
	}


	private IHMImage getDoubleClickedImage(ImageView imageView) {
		for (int i = 0; i < leftImageViews.length; i++) {
			if (leftImageViews[i] == imageView) {
				return album.getPages().get(activePageNumber - 1).getImages().get(i);
			}
		}
		for (int i = 0; i < rightImageViews.length; i++) {
			if (rightImageViews[i] == imageView) {
				return album.getPages().get(activePageNumber).getImages().get(i);
			}
		}
		return null;
	}

	public void trackMouse(MouseEvent event) {
		ImageView imageView = (ImageView) event.getSource();
		double x = imageView.getX();
		double y = imageView.getY();
		double width = imageView.getFitWidth();
		double height = imageView.getFitHeight();

		double clickedX = event.getX();
		double clickedY = event.getX();

		System.out.println("track");
	}
}
