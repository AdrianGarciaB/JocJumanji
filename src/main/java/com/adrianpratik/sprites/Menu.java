package com.adrianpratik.sprites;

import com.adrianpratik.control.MainWindow;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class Menu extends Node {
    private ImageView backgroundImage;
    private Group groupMenu;
    private ImageView play, titleGame, exit;
    private MediaPlayer mediaPlayerInicio;
   // private Button exit;


    public Menu() {

        // Imagen de fondo
        backgroundImage = new ImageView(MainWindow.imageURI + "menu_background.png");


        // Titulo del juego
        titleGame = new ImageView("images/title_menu.gif");
        titleGame.setStyle("-fx-background-color: transparent; ");
        titleGame.setFitHeight(100);
        titleGame.setFitWidth(100);



        //Opciones
        play = new ImageView("images/option_play_menu.gif");
        play.setStyle("-fx-background-color: transparent; ");
        play.setOnMouseEntered(event -> play.setImage(new Image("images/play_mouse_on.gif")));
        play.setOnMouseExited(event -> play.setImage(new Image("images/play_mouse_on.gif")));
        play.setLayoutX(525);
        play.setLayoutY(300);

        exit = new ImageView("images/option_exit_menu.gif");
        exit.setStyle("-fx-background-color: transparent; ");
       // exit.set(event -> System.exit(0));
        exit.setOnMouseEntered(event -> exit.setImage(new Image("images/quit_hover.png")));
        exit.setOnMouseExited(event -> exit.setImage(new Image("images/quit_hover.png")));
        exit.setLayoutX(465);
        exit.setLayoutY(450);

//        btnExit.setGraphic(new ImageView("images/option_exit_menu.gif"));
//        btnExit.setStyle("-fx-background-color: transparent; ");
//        btnExit.setOnAction(event -> System.exit(0));
//        btnExit.setOnMouseEntered(event -> btnExit.setGraphic(new ImageView("images/quit_hover.png")));
//        btnExit.setOnMouseExited(event -> btnExit.setGraphic(new ImageView("images/quit_hover.png")));
//        btnExit.setLayoutX(465);
//        btnExit.setLayoutY(450);


        groupMenu = new Group();
        groupMenu.getChildren().addAll(play, exit, titleGame);
    }

    public Group getSceneMenu() {
        return groupMenu;
    }

    public void draw() {
        MainWindow.getGraphicsContext().drawImage(backgroundImage.getImage(), 0, 0, MainWindow.width, MainWindow.height);

        //Titulo del juego
        MainWindow.getGraphicsContext().drawImage(titleGame.getImage(), 700*MainWindow.diferenceWidth, 300*MainWindow.diferenceHeight, 500*MainWindow.diferenceWidth, 100*MainWindow.diferenceHeight);

        // Boton jugar
        MainWindow.getGraphicsContext().drawImage(play.getImage(), 900*MainWindow.diferenceWidth, 500*MainWindow.diferenceHeight);

        // Boton exit
        MainWindow.getGraphicsContext().drawImage(exit.getImage(), 900*MainWindow.diferenceWidth, 600*MainWindow.diferenceHeight);

    }
}
