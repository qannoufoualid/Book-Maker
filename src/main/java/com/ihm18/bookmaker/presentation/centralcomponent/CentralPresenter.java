package com.ihm18.bookmaker.presentation.centralcomponent;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.airhacks.afterburner.injection.Injector;
import com.ihm18.bookmaker.businessobject.Album;
import com.ihm18.bookmaker.presentation.albumformcomponent.AlbumFormView;
import com.ihm18.bookmaker.presentation.albumslistcomponent.AlbumsListView;
import com.ihm18.bookmaker.presentation.historycomponent.HistoryModel;
import com.ihm18.bookmaker.presentation.historycomponent.HistoryView;
import com.ihm18.bookmaker.presentation.welcomecomponent.WelcomeView;
import com.ihm18.bookmaker.service.AlbumService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Le presentateur du composant central.
 * @author oualidqannouf
 */
public class CentralPresenter implements Initializable {

	@FXML 
    private BorderPane mainContainer;
	
    @Inject
    private CentralModel centralModel ;

   
    @FXML
    private AnchorPane leftContainer;
    
    @FXML
    private Label mainViewTitleLabel;
    
    
	public void initialize(URL location, ResourceBundle resources) {
		
		leftContainer.getChildren().add(new AlbumsListView().getView());
		
    	mainContainer.centerProperty().bind(centralModel.mainViewProperty());
    	mainContainer.bottomProperty().bind(centralModel.bottomViewProperty());
    	mainViewTitleLabel.textProperty().bind(centralModel.mainViewTitleProperty());
        WelcomeView welcomeView = new WelcomeView();
        centralModel.setMainView(welcomeView.getView(), WelcomeView.TITLE);
        centralModel.setBottomView(new HistoryView().getView());
    }

    public void launch() {
    }

    /**
     * Methode listener pour créer un nouvel album depuis le menu haut
     * @param event
     */
    public void nouvelAlbum(ActionEvent event){
        AlbumFormView albumFormView = new AlbumFormView();
        centralModel.setMainView(albumFormView.getView(), AlbumFormView.TITLE);
    }

    /**
     * Methode pour quitter l'application avec le menu haut
     * @param event
     */
    public void quitter(ActionEvent event){
        Injector.forgetAll();
        System.exit(0);
    }

    /**
     * Permet d'afficher l'aide.
     * @param event
     */
    public void displayHelp(ActionEvent event){
    	final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(null);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Aide pour l'utilisation de l'application Book-Maker :\n\nQuelques précisions quant au fonctionnement du logiciel.\n\n\n-----------------   Ajouter une image ------------------------------\n\nPour ajouter des images sur une page, il suffit de glisser l'image depuis un répertoire externe à l'application sur la page souhaitée. \nLes images peuvent ainsi être ajoutées une à une très facilement.\n\n\n-----------------   Suppression d'une image ------------------------\n\nPour supprimer une image il suffit de sélectionner l'image à supprimer et d'utiliser la touche \"suppr\"\n\n\n-----------------   Navigation avec le clavier ---------------------\n\nLes touches de navigation clavier (leftArrow et rightArrow) sont utilisables pour changer de page dans un album.\n\n\n-----------------   Changement du titre d'une image ----------------\n\nLe titre d'une image sera par défaut son nom de fichier. Pour le changer il suffit de double cliquer dessus directement sur l'album.\nLe changement de titre sera renseigné par un changement de couleur sur le fond de l'affichage du titre. Pour valider le changement il suffit alors de déselectionner l'image\nou d'appuyer sur la touche \"Entrée\". Une pop-up indiquera le changement."));
        Scene dialogScene = new Scene(dialogVbox, 1100, 600);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    
    public void displayWelcomeView(ActionEvent event){
    	WelcomeView welcomeView = new WelcomeView();
        centralModel.setMainView(welcomeView.getView(), WelcomeView.TITLE);
    }
    
}
