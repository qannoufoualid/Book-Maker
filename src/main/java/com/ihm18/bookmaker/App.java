package com.ihm18.bookmaker;


import java.util.HashMap;
import java.util.Map;

import com.airhacks.afterburner.injection.Injector;
import com.ihm18.bookmaker.presentation.centralcomponent.CentralView;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *Cette classe est le point de depart de l'application
 * @author oualidqannouf
 */
public class App extends Application {

	/**
	 * Le point d'entrée de toute les applicatons JavaFx
	 */
    @Override
    public void start(Stage stage) throws Exception {
    	
    	stage.setWidth(1450);
    	stage.setHeight(1050);
    	
        Map<Object, Object> customProperties = new HashMap<>();
        customProperties.put("imagesDirectory", "images/");
        /*
         * any function which accepts an Object as key and returns
         * and return an Object as result can be used as source.
         */
        Injector.setConfigurationSource(customProperties::get);

        CentralView appView = new CentralView();
        Scene scene = new Scene(appView.getView());
        stage.setTitle("Book Maker");
        final String uri = getClass().getResource("app.css").toExternalForm();
        scene.getStylesheets().add(uri);
        scene.getStylesheets().add("bootstrapfx.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method qui s'execute après la fin de l'application.
     */
    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
