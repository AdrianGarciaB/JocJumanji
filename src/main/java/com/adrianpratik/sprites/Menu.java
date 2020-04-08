package com.adrianpratik.sprites;

import com.adrianpratik.control.MainWindow;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.io.File;

public class Menu {
    private ImageView backgroundImage;
    private Group groupMenu;
    private ImageView play, titleGame, exit;

    private AudioClip kick;
    private BooleanProperty onClik = new SimpleBooleanProperty();



    public Menu() {

        // Imagen de fondo
        backgroundImage = new ImageView(MainWindow.imageURI + "menu_background.png");


        // Titulo del juego
        titleGame = new ImageView("images/title_menu.gif");
        titleGame.setStyle("-fx-background-color: transparent; ");
        titleGame.setFitHeight(100);
        titleGame.setFitWidth(100);



        //Opciones

        // Opción de Play
        play = new ImageView("images/option_play_menu.gif");
        play.setStyle("-fx-background-color: transparent; ");
        play.setOnMouseEntered(event -> play.setImage(new Image("images/play_mouse_on.gif")));
        play.setOnMouseExited(event -> play.setImage(new Image("images/play_mouse_on.gif")));
        play.setLayoutX(525);
        play.setLayoutY(300);

        // Opcion de Exit
        exit = new ImageView("images/option_exit_menu.gif");
        exit.setStyle("-fx-background-color: transparent; ");
       // exit.set(event -> System.exit(0));
        exit.setOnMouseEntered(event -> exit.setImage(new Image("images/quit_hover.png")));
        exit.setOnMouseExited(event -> exit.setImage(new Image("images/quit_hover.png")));
        exit.setLayoutX(465);
        exit.setLayoutY(450);

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
        MainWindow.getGraphicsContext().drawImage(play.getImage(), 900*MainWindow.diferenceWidth, 500*MainWindow.diferenceHeight, 102*MainWindow.diferenceWidth, 60*MainWindow.diferenceHeight);

        // Boton exit
        MainWindow.getGraphicsContext().drawImage(exit.getImage(), 900*MainWindow.diferenceWidth, 600*MainWindow.diferenceHeight, 103*MainWindow.diferenceWidth, 49*MainWindow.diferenceHeight);

    }

    public boolean playButtonClicked(Point2D p){
        System.out.println(p.getX() + ":" + p.getY());
        return getPlayBoundary().contains(p);
    }

    public static Rectangle2D getPlayBoundary() {
        return new Rectangle2D(900*MainWindow.diferenceWidth, 500*MainWindow.diferenceHeight, 102*MainWindow.diferenceWidth, 60*MainWindow.diferenceHeight);
    }

    public boolean exitButtonClicked(Point2D p) {
        return getExitBoundary().contains(p);
    }

    public static Rectangle2D getExitBoundary() {
        return new Rectangle2D(900*MainWindow.diferenceWidth, 600*MainWindow.diferenceHeight, 102*MainWindow.diferenceWidth, 60*MainWindow.diferenceHeight);
    }
}
