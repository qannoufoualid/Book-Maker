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
 * Le presentateur du composant PagesComponent.
 * @author oualidqannouf
 */
public class PagesPresenter implements Initializable {

	/**
	 * Le modéle du composant albumDetailComponent.
	 */
	@Inject
	private AlbumDetailModel albumDetailModel;

	/**
	 * Le modéle du composant PagesComponent
	 */
	@Inject
	private PagesModel pagesModel;
	/**
	 * Le modele du composant EditionActions.
	 */
	@Inject
	private EditionActionsModel editionActionsModel;
	
	/**
	 * une reference sur la class utility.
	 */
	@Inject
	private Utility utility;
	
	/**
	 * une reference sur le soundPlayer
	 */
	@Inject
	private SoundPlayer soundPlayer;

	/**
	 * fx:id=pagesContainer
	 */
	@FXML
	private BorderPane pagesContainer;
	/**
	 * fx:id=leftSideImageView
	 */
	@FXML
	private ImageView leftSideImageView;
	/**
	 * fx:id=rightSideImageView
	 */
	@FXML
	private ImageView rightSideImageView;
	/**
	 * fx:id=leftPageNumberLabel
	 */
	@FXML
	private Label leftPageNumberLabel;
	/**
	 * fx:id=rightPageNumberLabel
	 */
	@FXML
	private Label rightPageNumberLabel;
	/**
	 * fx:id=leftAnchorPane
	 */
	@FXML
	private AnchorPane leftAnchorPane;
	/**
	 * fx:id=rightAnchorPane
	 */
	@FXML
	private AnchorPane rightAnchorPane;

	/**
	 * Liste des images view de la page à gauche.
	 */
	private ImageView[] leftImageViews;

	/**
	 * Liste des images view de la page à droite.
	 */
	private ImageView[] rightImageViews;

	/**
	 * L'album selectionné.
	 */
	private Album album;
	/**
	 * La page courante
	 */
	private int activePageNumber = 0;
	/**
	 * Le nombre de page.
	 */
	private int pagesNumber;
	/**
	 * Si une image a été cliquée
	 */
	protected boolean imageClicked = false;;

	/**
	 * Executé au lancement du composant
	 */
	public void launch() {}

	/**
	 * Executé aprés le lancement du composant.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		pagesContainer.topProperty().set(new EditionActionsView().getView());
		pagesContainer.rightProperty().bind(pagesModel.paletteViewProperty());
		
		leftImageViews = new ImageView[] {};
		rightImageViews = new ImageView[] {};
		album = albumDetailModel.getAlbum();
		pagesNumber = album.getPages().size();
		activePageNumber = (pagesNumber > 0) ? activePageNumber = 1 : 0;
		updateBackgroundAndPages();

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

	/**
	 * Inititialiser les evenements du drag.
	 */
	private void initDragEvents() {
		initDragEventsOnLeftSide();
		initDragEventsOnRightSide();
	}

	/**
	 * Initialiser les evenements sur la page gauche
	 */
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
					updateBackgroundAndPages();
				} else {
					event.consume();
				}
			}
		});
	}

	/**
	 * Initialiser les evenements sur la partie droite.
	 */
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
					updateBackgroundAndPages();
				} else {
					event.consume();
				}
			}
		});
	}

	/**
	 * permet de créer une image.
	 * @param isLeft si l'image doit etre créée sur la page gauche ou pas.
	 * @param imageFile le fichier de l'image.
	 */
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

	/**
	 * Permet de créer une image avec un titre.
	 * @param image
	 * @return
	 */
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
				editionActionsModel.setImageView(titledImage.getImageView());
				pagesModel.imageClickedProperty().setValue(false);
			}
		});
		return titledImage;
		
	}

	/**
	 * Permet d'ajouter une page.
	 * @param event
	 */
	public void addPage(ActionEvent event) {
		attachPage();
		activePageNumber = (pagesNumber % 2 == 0) ? pagesNumber - 1 : pagesNumber;
		updateBackgroundAndPages();
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

	/**
	 * Permet de vider un paneau.
	 * @param pane
	 */
	private void clearPane(AnchorPane pane) {
		pane.getChildren().clear();
	}

	/**
	 * Permet d'attacher une nouvelle page à l'album.
	 */
	private void attachPage() {

		Page page = new Page();
		page.setNumber(++pagesNumber);
		page.setAlbum(album);
		album.addPage(page);
	}

	/**
	 * Permet de mettre à jours les arrieres plans des 2 pages.
	 */
	private void updateBackgroundAndPages() {

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

	/**
	 * Permet d'initialiser l'arriere-plan des deux pages. 
	 */
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

	/**
	 * Permet de naviguer à gauche.
	 * @param event
	 */
	public void turnLeft(ActionEvent event) {
		if (activePageNumber - 2 >= 1) {
			activePageNumber = activePageNumber - 2;
			soundPlayer.playSound("page-flip");
			updateBackgroundAndPages();
		}
		
	}
	
	/**
	 * Permet de naviguer à droite.
	 * @param event
	 */
	public void turnRight(ActionEvent event) {
		if (activePageNumber + 2 <= pagesNumber) {
			soundPlayer.playSound("page-flip");
			activePageNumber = activePageNumber + 2;
			updateBackgroundAndPages();
		}
		
	}
}
