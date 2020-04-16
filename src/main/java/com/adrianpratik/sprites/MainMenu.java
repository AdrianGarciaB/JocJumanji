package com.adrianpratik.sprites;

import com.adrianpratik.control.GameController;
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
        backgroundImage = new ImageView(GameController.imageURI + "menu_background.png");

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
        GameController.getGraphicsContext().drawImage(backgroundImage.getImage(), 0, 0, GameController.width, GameController.height);

        //Titulo del juego
        GameController.getGraphicsContext().drawImage(titleGame.getImage(), 600* GameController.diferenceWidth, 50* GameController.diferenceHeight, 700* GameController.diferenceWidth, 400* GameController.diferenceHeight);

        if (!connecting) {
            // Boton jugar
            GameController.getGraphicsContext().drawImage(play.getImage(), 866.5 * GameController.diferenceWidth, 500 * GameController.diferenceHeight, 169 * GameController.diferenceWidth, 60 * GameController.diferenceHeight);

            // Boton exit
            GameController.getGraphicsContext().drawImage(exit.getImage(), 866.5 * GameController.diferenceWidth, 600 * GameController.diferenceHeight, 169 * GameController.diferenceWidth, 60 * GameController.diferenceHeight);
        }
        else if (connecting){
            GameController.getGraphicsContext().drawImage(conectionStatus, 560*GameController.diferenceWidth, 400*GameController.diferenceHeight, 820*GameController.diferenceWidth, 120*GameController.diferenceHeight);
        }

    }

    public boolean playButtonClicked(Point2D p){
        System.out.println(p.getX() + ":" + p.getY());
        return getPlayBoundary().contains(p);
    }

    public static Rectangle2D getPlayBoundary() {
        return new Rectangle2D(900* GameController.diferenceWidth, 500* GameController.diferenceHeight, 102* GameController.diferenceWidth, 60* GameController.diferenceHeight);
    }

    public boolean exitButtonClicked(Point2D p) {
        return getExitBoundary().contains(p);
    }

    public static Rectangle2D getExitBoundary() {
        return new Rectangle2D(900* GameController.diferenceWidth, 600* GameController.diferenceHeight, 102* GameController.diferenceWidth, 60* GameController.diferenceHeight);
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
