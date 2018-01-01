package com.ihm18.bookmaker.presentation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.image.Image;

/**
 *
 * @author oualidqannouf
 */
public class Utility {

	public String replace(String object, String parameter, String value){
		return object.replace("{{"+parameter+"}}", value);
	}
	
	public String convertToFrenshDate(LocalDateTime dateTime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        return dateTime.format(formatter);
	}
	
	public Image getFXImage(String name){
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("images/"+name);
		return new Image(is);
	}

}
