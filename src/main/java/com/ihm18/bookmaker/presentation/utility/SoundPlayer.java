package com.ihm18.bookmaker.presentation.utility;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {

	public void playSound(String soundName){
		String path = getClass().getClassLoader().getResource("sounds/"+soundName+".mp3").getFile();
		Media sound = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
}
