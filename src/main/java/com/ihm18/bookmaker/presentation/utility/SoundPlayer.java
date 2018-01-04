package com.ihm18.bookmaker.presentation.utility;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Classe qui encapsule les fonctionnalités des audios.
 * @author qannoufoualid
 *
 */
public class SoundPlayer {

	/**
	 * Constante qui represente un un page flip.
	 */
	public static final String PAGE_FLIP_SOUND = "page-flip";
	
	/**
	 * Collection pour enregistrer les sons
	 */
	Map<String, Media> sounds = new HashMap<String, Media>();
	
	/**
	 * constructeur par defaut
	 */
	public SoundPlayer() {
		
	}
	
	/**
	 * Permet de jouer un son.
	 * @param soundName le son à jouer
	 */
	public void playSound(String soundName){
		if(!sounds.containsKey(soundName)){
			String path = getClass().getClassLoader().getResource("sounds/page-flip.mp3").getFile();
			sounds.put(soundName, new Media(new File(path).toURI().toString())) ;
		}
		MediaPlayer mediaPlayer = new MediaPlayer(sounds.get(soundName));
		mediaPlayer.play();
		
	}
}
