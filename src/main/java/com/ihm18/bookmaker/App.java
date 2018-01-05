package com.ihm18.bookmaker;


import java.util.HashMap;
import java.util.Map;

import com.airhacks.afterburner.injection.Injector;
import com.ihm18.bookmaker.presentation.centralcomponent.CentralView;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author oualidqannouf
 * Cette class est le point de depart de l'application
 */
public class App extends Application {

	/**
	 * Le point d'entrée de toute les applicatons JavaFx
	 */
    @Override
    public void start(Stage stage) throws Exception {
    	
    	Screen screen = Screen.getPrimary();
    	Rectangle2D bounds = screen.getVisualBounds();

    	stage.setX(bounds.getMinX());
    	stage.setY(bounds.getMinY());
    	stage.setWidth(bounds.getWidth());
    	stage.setHeight(bounds.getHeight());
    	
        Map<Object, Object> customProperties = new HashMap<>();
        customProperties.put("imagesDirectory", "images/");
        /*
         * any function which accepts an Object as key and returns
         * and return an Object as result can be used as source.
         */
        Injector.setConfigurationSource(customProperties::get);

        System.setProperty("happyEnding", " Enjoy the flight!");
        CentralView appView = new CentralView();
        Scene scene = new Scene(appView.getView());
        stage.setTitle("Book Maker");
        final String uri = getClass().getResource("app.css").toExternalForm();
        scene.getStylesheets().add(uri);
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
