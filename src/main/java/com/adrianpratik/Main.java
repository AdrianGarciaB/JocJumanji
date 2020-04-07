package com.adrianpratik;

import com.adrianpratik.control.MainWindow;
import com.adrianpratik.sprites.Menu;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Main.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Menu menu = new Menu();
        menu.getSceneMenu().getChildren().add(root);
        Scene sc = new Scene(menu.getSceneMenu(), 1920, 1080);
        // Musica del inicio


        MainWindow window = loader.getController();
        window.setScene(sc);
        window.setWidth(sc.getWidth());
        window.setHeight(sc.getHeight());
        window.getMainCanvas().setWidth(1920);
        window.getMainCanvas().setHeight(1080);


        InvalidationListener widthListener = o -> window.setWidth(sc.getWidth());
        InvalidationListener heightListener = o ->  window.setHeight(sc.getHeight());

        sc.widthProperty().addListener(widthListener);
        sc.heightProperty().addListener(heightListener);
        window.start();


//        String ssound = "audio/musicaFondo.mp3";
//        Media sound = new Media(ssound);
//        MediaPlayer mediaPlayerInicio = new MediaPlayer(sound);
//        mediaPlayerInicio.setAutoPlay(true);
//        mediaPlayerInicio.setVolume(50);
//        mediaPlayerInicio.setCycleCount(MediaPlayer.INDEFINITE);
//        mediaPlayerInicio.play();

        primaryStage.setScene(sc);
        primaryStage.setTitle("Jumanji :)");
        primaryStage.show();

    }
}
