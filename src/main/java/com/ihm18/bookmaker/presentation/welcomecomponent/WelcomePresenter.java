package com.ihm18.bookmaker.presentation.welcomecomponent;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.airhacks.afterburner.injection.Injector;
import com.ihm18.bookmaker.presentation.albumformcomponent.AlbumFormView;
import com.ihm18.bookmaker.presentation.centralcomponent.CentralModel;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 *
 * @author oualidqannouf
 * Le presentateur du composant qui represente la vue affiché aprés le demarrage de l'application.
 */
public class WelcomePresenter implements Initializable {

	/**
	 * Le modéle du composant central.
	 */
	@Inject
	private CentralModel centralModel;

	public void launch() {}
	@Override
	public void initialize(URL location, ResourceBundle resources) {}

	/**
	 * Permet d'afficher le formulaire de création d'un album (AlbumFormView).
	 * @param event
	 */
	public void displayAlbumForm(ActionEvent event) {
		AlbumFormView albumFormView = new AlbumFormView();
		centralModel.setMainView(albumFormView.getView(), AlbumFormView.TITLE);
	}

	/**
	 * Permet d'arrêter l'application.
	 * @param event event de click sur le bouton "Quitter"
	 */
	public void exitApplication(ActionEvent event){
		Injector.forgetAll();
		System.exit(0);
	}
	
}
