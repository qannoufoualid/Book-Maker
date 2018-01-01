package com.ihm18.bookmaker.presentation.pagescomponent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.inject.Inject;

import com.ihm18.bookmaker.businessobject.Album;
import com.ihm18.bookmaker.businessobject.Page;
import com.ihm18.bookmaker.presentation.albumdetailcomponent.AlbumDetailModel;
import com.ihm18.bookmaker.presentation.utility.SoundPlayer;
import com.ihm18.bookmaker.presentation.utility.Utility;

/**
 *
 * @author oualidqannouf
 */
public class PagesPresenter implements Initializable {

	@Inject
	private AlbumDetailModel albumDetailModel;
	
	@Inject
	private Utility utility;
	@Inject
	private SoundPlayer soundPlayer;
	
	private Album album;
	
	@FXML
	private GridPane pagesContainer;
	@FXML
	private ImageView leftSideImageView;
	@FXML
	private ImageView rightSideImageView;
	@FXML
	private Label leftPageNumberLabel;
	@FXML
	private Label rightPageNumberLabel;
	
	private int activePageNumber = 0;
	private int pagesNumber;
	
    public void launch() {
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//On crée les pages dynamiquement 
		album = albumDetailModel.getAlbum();
		pagesNumber = album.getPages().size();
		activePageNumber = (pagesNumber>0)? activePageNumber=1:0;
		updateBackground();
	}

	public void addPage(ActionEvent event){
		attachPage();
		activePageNumber = (pagesNumber%2==0)?pagesNumber-1: pagesNumber;
		updateBackground();
		
	}

	private void attachPage() {
		
		Page page = new Page();
		page.setNumber(++pagesNumber);
		album.addPage(page);
	}

	private void updateBackground() {

		resetBackground();
		
		if(activePageNumber == pagesNumber){
			Image image = utility.getFXImage("left-page.png");
			leftSideImageView.setImage(image);
			
			leftPageNumberLabel.setText(String.valueOf(pagesNumber));
			rightPageNumberLabel.setText("");
		}
		else if(activePageNumber < pagesNumber){
			Image image =  utility.getFXImage("left-page.png");
			leftSideImageView.setImage(image);
			image = utility.getFXImage("right-page.png");
			rightSideImageView.setImage(image);
			
			leftPageNumberLabel.setText(String.valueOf(activePageNumber));
			rightPageNumberLabel.setText(String.valueOf(activePageNumber+1));
		}
	}

	private void resetBackground() {
		Image image =  utility.getFXImage("left-side.png");
		leftSideImageView.setImage(image);
		image =  utility.getFXImage("right-side.png");
		rightSideImageView.setImage(image);
	}

	public void turnLeft(ActionEvent event){
		if(activePageNumber-2 >= 1){
			activePageNumber = activePageNumber-2;
			soundPlayer.playSound("page-flip");
		}
		updateBackground();
	}
	
	public void turnRight(ActionEvent event){
		if(activePageNumber+2 <= pagesNumber){
			soundPlayer.playSound("page-flip");
			activePageNumber = activePageNumber+2;
		}
		updateBackground();
		
		
	}
	
}
