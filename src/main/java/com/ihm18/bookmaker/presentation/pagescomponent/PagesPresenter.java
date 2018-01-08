package com.ihm18.bookmaker.presentation.pagescomponent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.ihm18.bookmaker.presentation.bordercomponent.BorderModel;
import javafx.scene.control.Button;
import javafx.scene.input.*;
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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.SepiaTone;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Le presentateur du composant PagesComponent.
 * 
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

	
	@Inject
	private CentralModel centralModel;
	
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
	 * un borderModel pour changer les paramètre d'une image
	 */
	private BorderModel borderModel;

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
	 * fx:id=editPane
	 */
	@FXML
	private BorderPane editPane;

	/**
	 * fx:id=rightArrow
	 */
	@FXML
	private Button rightArrow;

	/**
	 * Liste des images view de la page à gauche.
	 */
	private List<TitledImage> leftImageViews;

	/**
	 * Liste des images view de la page à droite.
	 */
	private List<TitledImage> rightImageViews;

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
	protected boolean isImageClicked = false;

	/**
	 * Reference sur l'image cliquée
	 */
	private TitledImage clickedImage = null;

	/**
	 * Reference sur l'entité IHMImage
	 */
	private IHMImage selectedIHMImage = null;

	/**
	 * Si le titre d'une image a été changé
	 */
	private boolean titledImageTextChanged = false;

	/**
	 * Executé au lancement du composant
	 */
	public void launch() {
	}

	/**
	 * Executé aprés le lancement du composant.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		centralModel.mainViewProperty().addListener(new ChangeListener<Node>() {

			@Override
			public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
				saveImagesState();
			}
		});
		
		editPane.topProperty().set(new EditionActionsView().getView());
		// pagesContainer.rightProperty().bind(pagesModel.paletteViewProperty());
		pagesModel.setEditPane(editPane);

		leftImageViews = new ArrayList<>();
		rightImageViews = new ArrayList<>();
		album = albumDetailModel.getAlbum();
		pagesNumber = album.getPages().size();
		activePageNumber = (pagesNumber > 0) ? activePageNumber = 1 : 0;
		
		updateBackgroundAndPages();
		initDragEvents();
		
	}

	/**
	 * Permet de gérer le clic souris sur l'une ou l'autre des pages (validation
	 * des titres)
	 * 
	 * @param event
	 */
	public void cliqueMouse(MouseEvent event) {
		if (!isImageClicked) {
			editionActionsModel.setTitledImage(null);
			pagesModel.imageClickedProperty().setValue(true);
		}

		if (titledImageTextChanged) {
			changeTitre();
		}
		isImageClicked = false;
	}

	/**
	 * Permet de valider un titre avec la touche entrée
	 * 
	 * @param event
	 */
	public void enterVal(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER && titledImageTextChanged) {
			changeTitre();
		}
	}

	/**
	 * Valider changement de titre
	 */
	public void changeTitre() {
		if (!clickedImage.getText().equals(selectedIHMImage.getTitle())) {
			selectedIHMImage.setTitle(clickedImage.getText());
			clickedImage.getTextField().getStyleClass().removeAll("bad", "med", "good", "best");
			clickedImage.getTextField().getStyleClass().add("best");
			utility.showInformationAlert(AlertType.INFORMATION, "Titre modifié avec succés");
		}
		selectedIHMImage = null;
		clickedImage = null;
		titledImageTextChanged = false;
		rightArrow.requestFocus();
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
					saveImagesState();
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
					saveImagesState();
					updateBackgroundAndPages();
				} else {
					event.consume();
				}
			}
		});
	}

	/**
	 * permet de créer une image.
	 * 
	 * @param isLeft
	 *            si l'image doit etre créée sur la page gauche ou pas.
	 * @param imageFile
	 *            le fichier de l'image.
	 */
	public void acceptImageAtSide(boolean isLeft, File imageFile) {

		IHMImage image = new IHMImage(imageFile.getName(), "", LocalDateTime.now());
		image.setFile(imageFile);
		Page page = album.getPages().get((isLeft) ? activePageNumber - 1 : activePageNumber);
		page.getImages().add(image);
		image.setPage(page);

		TitledImage titledImage = createTitledImage(image);
		
		if (isLeft) {
			leftAnchorPane.getChildren().add(titledImage);
			leftImageViews.add(titledImage);
		} else {
			rightAnchorPane.getChildren().add(titledImage);
			rightImageViews.add(titledImage);
		}

		saveImagesState();
	}

	/**
	 * Permet de créer une image avec un titre.
	 * 
	 * @param image
	 * @return
	 */
	private TitledImage createTitledImage(IHMImage image) {
		TitledImage titledImage = new TitledImage(leftAnchorPane);
		Image img;
		try {
			img = new Image(FileUtils.openInputStream(image.getFile()));
			titledImage.setImage(img);
			titledImage.setText(image.getTitle());
			double height = img.getHeight() * 200 / img.getWidth();
			titledImage.setFitWidth(200);
			titledImage.setFitHeight(height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		titledImage.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				clickedImage = titledImage;
				isImageClicked = true;
				selectedIHMImage = image;
				editionActionsModel.setTitledImage(titledImage);
				pagesModel.imageClickedProperty().setValue(false);
			}
		});
		titledImage.textProperty().addListener((observable, oldValue, newValue) -> {
			titledImageTextChanged = true;
			selectedIHMImage = image;
			clickedImage = titledImage;
			clickedImage.getTextField().getStyleClass().removeAll("bad", "med", "good", "best");
			clickedImage.getTextField().getStyleClass().add("bad");
		});
	
		
		
		return titledImage;

	}

	/**
	 * Cette fonction permet d'actualiser les images d'une page.
	 * 
	 *
	 * @param pageNumber
	 *            la page de l'album
	 * @throws IOException
	 */
	public void fillViewWithPage(int pageNumber) {

		if (pageNumber % 2 != 0) {
			clearPane(leftAnchorPane);
			// clearImageViews(leftImageViews);
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
				initTitledImage(titledImage, ihmImage);

				leftAnchorPane.getChildren().add(titledImage);

			}
		} else {
			clearPane(rightAnchorPane);
			// clearImageViews(rightImageViews);
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
				initTitledImage(titledImage, ihmImage);
				rightAnchorPane.getChildren().add(titledImage);

			}
		}

	}

	/**
	 * Permet d'initialiser une titledImage
	 * 
	 * @param titledImage
	 *            titledImage
	 * @param ihmImage
	 *            IHMimage
	 */
	private void initTitledImage(TitledImage titledImage, IHMImage ihmImage) {
		titledImage.setLayoutX(ihmImage.getX());
		titledImage.setLayoutY(ihmImage.getY());
		titledImage.setFitWidth(ihmImage.getWidth());
		titledImage.setFitHeight(ihmImage.getHeight());
		titledImage.setBorderColor(ihmImage.getBorderColor());
		titledImage.setBorderSize(ihmImage.getBorderSize());
		titledImage.setBorderStyle(ihmImage.getBorderStyle());
		titledImage.setBorderColorObject(ihmImage.getBorderColorObject());
		titledImage.setBorder(ihmImage.isBordered());

		if (titledImage.isBordered())
			titledImage.drawBorder();

		ColorAdjust colorAdjust = new ColorAdjust();
		if (ihmImage.getBrightness() != Double.MIN_VALUE)
			colorAdjust.setBrightness((1 - ihmImage.getBrightness()) * -1);
		if (ihmImage.getSaturation() != Double.MIN_VALUE)
			colorAdjust.setSaturation(ihmImage.getSaturation());

		SepiaTone st = new SepiaTone();
		if (ihmImage.getSepia() != Double.MIN_VALUE) {
			st.setLevel(ihmImage.getSepia());
			colorAdjust.setInput(st);
		}
		titledImage.setEffect(colorAdjust);
	}

	/**
	 * Permet de vider un paneau.
	 * 
	 * @param pane
	 */
	private void clearPane(AnchorPane pane) {
		pane.getChildren().clear();
	}

	/**
	 * Permet de vider un tableau de imageView.
	 * 
	 * @param pane
	 */
	private void clearImageViews(List<ImageView> list) {
		list.clear();
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
	}

	/**
	 * Gere l'appuie du bouton createPage
	 * 
	 * @param event
	 */
	public void addPagePressed(ActionEvent event) {
		addPage();
	}

	/**
	 * Action d'ajouter une page
	 */
	public void addPage() {
		editionActionsModel.setTitledImage(null);
		pagesModel.imageClickedProperty().setValue(true);
		saveImagesState();
		attachPage();
		activePageNumber = (pagesNumber % 2 == 0) ? pagesNumber - 1 : pagesNumber;

		updateBackgroundAndPages();
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
	 * Permet de naviguer à gauche.
	 * 
	 * @param event
	 */
	public void turnLeftPressed(ActionEvent event) {
		turnLeft();
	}

	/**
	 * Permet de naviguer à gauche
	 */
	public void turnLeft() {
		editionActionsModel.setTitledImage(null);
		pagesModel.imageClickedProperty().setValue(true);
		if (activePageNumber - 2 >= 1) {
			saveImagesState();
			activePageNumber = activePageNumber - 2;
			soundPlayer.playSound("page-flip");
			updateBackgroundAndPages();
		}
	}

	/**
	 * Permet de naviguer à droite.
	 * 
	 * @param event
	 */
	public void turnRightPressed(ActionEvent event) {
		turnRight();
	}

	/**
	 * permet de naviguer au clavier
	 */
	public void keyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.RIGHT) {
			turnRight();
		}
		if (event.getCode() == KeyCode.LEFT) {
			turnLeft();
		}
		if (event.getCode() == KeyCode.DELETE) {
			removeImage();
		}
	}

	/**
	 * Permet de naviguer à droite.
	 */
	public void turnRight() {
		editionActionsModel.setTitledImage(null);
		pagesModel.imageClickedProperty().setValue(true);
		if (pagesNumber == 0 || ((activePageNumber % 2 == 1)
				&& ((activePageNumber + 1 == pagesNumber) || (activePageNumber == pagesNumber)))) {
			addPage();
		}

		if (activePageNumber + 2 <= pagesNumber) {
			saveImagesState();
			soundPlayer.playSound("page-flip");
			activePageNumber = activePageNumber + 2;
			updateBackgroundAndPages();
		}
	}

	/**
	 * Permet de sauvgarder l'état des images.
	 */
	private void saveImagesState() {

		if (activePageNumber > 0) {
			for (int i = 0; activePageNumber - 1 < album.getPages().size()
					&& album.getPages().get(activePageNumber - 1) != null
					&& i < album.getPages().get(activePageNumber - 1).getImages().size(); i++) {
				TitledImage imageView = (TitledImage) leftAnchorPane.getChildren().get(i);
				Page page = album.getPages().get(activePageNumber - 1);
				IHMImage image = page.getImages().get(i);
				saveImageViewToIHMImage(image, imageView);
			}
			Page pageo = album.getPages().get(activePageNumber - 1);
			leftImageViews.clear();
			for (int i = 0; activePageNumber < album.getPages().size() && album.getPages().get(activePageNumber) != null
					&& i < album.getPages().get(activePageNumber).getImages().size(); i++) {
				TitledImage imageView = (TitledImage) rightAnchorPane.getChildren().get(i);
				Page page = album.getPages().get(activePageNumber);
				IHMImage image = page.getImages().get(i);
				saveImageViewToIHMImage(image, imageView);
			}
			rightImageViews.clear();
		}

	}

	/**
	 * Permet de sauvegarder l'état d'une imageView dans une IHMImage
	 * 
	 * @param image
	 * @param imageView
	 */
	private void saveImageViewToIHMImage(IHMImage image, TitledImage imageView) {
		double x = imageView.layoutXProperty().get();
		double y = imageView.layoutYProperty().get();
		image.setX(x);
		image.setY(y);
		image.setWidth(imageView.getFitWidth());
		image.setHeight(imageView.getFitHeight());
		image.setBorderColor(imageView.getBorderColor());
		image.setBorderSize(imageView.getBorderSize());
		image.setBorderStyle(imageView.getBorderStyle());
		image.setBorderColorObject(imageView.getBorderColorObject());
		image.setBorder(imageView.isBordered());

		ColorAdjust c = (ColorAdjust) imageView.getImageView().getEffect();

		if (c != null) {
			image.setBrightness(c.getBrightness() + 1);
			image.setSaturation(c.getSaturation());
			SepiaTone sepiaTone = (SepiaTone) c.getInput();
			if (sepiaTone != null)
				image.setSepia(sepiaTone.getLevel());
		}

	}

	/**
	 * Permet de supprimer une image.
	 */
	public void removeImage() {

		if (activePageNumber > 0) {
			Page activePage = album.getPages().get(activePageNumber - 1);

			if (activePage.getImages().contains(selectedIHMImage)) {
				activePage.getImages().remove(selectedIHMImage);
				leftAnchorPane.getChildren().remove(clickedImage);
			} else {
				Page activePageNext = album.getPages().get(activePageNumber);
				if (activePageNext.getImages().contains(selectedIHMImage)) {
					activePageNext.getImages().remove(selectedIHMImage);
					rightAnchorPane.getChildren().remove(clickedImage);
				}
			}
		}

	}

}
