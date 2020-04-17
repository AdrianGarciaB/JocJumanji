package com.adrianpratik.sprites;

import com.adrianpratik.control.GameWindow;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class MainMenu {
    private ImageView backgroundImage;
    private Group groupMenu;
    private ImageView play, titleGame, signGame, exit;
    private Image conectionStatus;
    private boolean connecting;
    private AudioClip kick;
    private BooleanProperty onClik = new SimpleBooleanProperty();


    public MainMenu() {

        // Imagen de fondo
        backgroundImage = new ImageView(GameWindow.imageURI + "menu_background.png");

        // Titulo del juego
        titleGame = new ImageView("images/logo.png");
        titleGame.setStyle("-fx-background-color: transparent; ");
        titleGame.setFitHeight(100);
        titleGame.setFitWidth(100);



        //Opciones

        // Opción de Play
        play = new ImageView("images/play.png");
        play.setStyle("-fx-background-color: transparent; ");
        play.setLayoutX(525);
        play.setLayoutY(300);

        // Opcion de Exit
        exit = new ImageView("images/exit.png");
        exit.setStyle("-fx-background-color: transparent; ");
       // exit.set(event -> System.exit(0));
        exit.setLayoutX(465);
        exit.setLayoutY(450);

        conectionStatus = new Image("images/connecting.png");

        groupMenu = new Group();
        groupMenu.getChildren().addAll(play, exit, titleGame);
    }

    public Group getSceneMenu() {
        return groupMenu;
    }

    public void draw() {
        GameWindow.getGraphicsContext().drawImage(backgroundImage.getImage(), 0, 0, GameWindow.width, GameWindow.height);

        //Titulo del juego
        GameWindow.getGraphicsContext().drawImage(titleGame.getImage(), 600* GameWindow.diferenceWidth, 50* GameWindow.diferenceHeight, 700* GameWindow.diferenceWidth, 400* GameWindow.diferenceHeight);

        if (!connecting) {
            // Boton jugar
            GameWindow.getGraphicsContext().drawImage(play.getImage(), 866.5 * GameWindow.diferenceWidth, 500 * GameWindow.diferenceHeight, 169 * GameWindow.diferenceWidth, 60 * GameWindow.diferenceHeight);

            // Boton exit
            GameWindow.getGraphicsContext().drawImage(exit.getImage(), 866.5 * GameWindow.diferenceWidth, 600 * GameWindow.diferenceHeight, 169 * GameWindow.diferenceWidth, 60 * GameWindow.diferenceHeight);
        }
        else if (connecting){
            GameWindow.getGraphicsContext().drawImage(conectionStatus, 560* GameWindow.diferenceWidth, 400* GameWindow.diferenceHeight, 820* GameWindow.diferenceWidth, 120* GameWindow.diferenceHeight);
        }

    }

    public boolean playButtonClicked(Point2D p){
        System.out.println(p.getX() + ":" + p.getY());
        return getPlayBoundary().contains(p);
    }

    public static Rectangle2D getPlayBoundary() {
        return new Rectangle2D(900* GameWindow.diferenceWidth, 500* GameWindow.diferenceHeight, 102* GameWindow.diferenceWidth, 60* GameWindow.diferenceHeight);
    }

    public boolean exitButtonClicked(Point2D p) {
        return getExitBoundary().contains(p);
    }

    public static Rectangle2D getExitBoundary() {
        return new Rectangle2D(900* GameWindow.diferenceWidth, 600* GameWindow.diferenceHeight, 102* GameWindow.diferenceWidth, 60* GameWindow.diferenceHeight);
    }

    public void setConnecting(boolean connecting) {
        this.connecting = connecting;
    }

    public void setConectionStatus(Image conectionStatus) {
        this.conectionStatus = conectionStatus;
    }

    public boolean isConnecting() {
        return connecting;
    }
}
