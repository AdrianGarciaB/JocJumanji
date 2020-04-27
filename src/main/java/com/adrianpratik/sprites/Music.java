package com.adrianpratik.sprites;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public class Music {
    private static MediaPlayer mediaPlayerFondo, mediaPlayerClick, mediaPlayerMover, mediaPlayerType;

    public MediaPlayer playMusic(){

        Media songFondo = new Media(new File("src/main/resources/audio/musicaFondo.mp3").toURI().toString());
        mediaPlayerFondo = new MediaPlayer(songFondo);
        mediaPlayerFondo.setAutoPlay(true);
        mediaPlayerFondo.setVolume(70);
        mediaPlayerFondo.setCycleCount(MediaPlayer.INDEFINITE);

        return mediaPlayerFondo;
    }
}
