package com.adrianpratik.control;

import com.adrianpratik.model.CardsPositionType;
import com.adrianpratik.sprites.Card;
import com.adrianpratik.sprites.Menu;
import com.adrianpratik.sprites.Table;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.xml.bind.JAXB;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    public static final double baseWidth = 1920;
    public static final double baseHeight = 1080;
    public static double width = baseWidth;
    public static double height = baseHeight;
    public static double diferenceWidth = 1f;
    public static double diferenceHeight = 1f;
    public static final String imageURI = "images/";

    private double mouseX, mouseY;
    private Scene scene;
    private static GraphicsContext gc;
    private Menu mainMenu;
    public boolean isInMenu = true;
    private Table table;
    private MediaPlayer mediaPlayerClick;
    private MediaPlayer repartPlayer;
    public static final CardsPositionType cardsPositions = JAXB.unmarshal(new File("src/main/resources/xml/cardPositions.xml"), CardsPositionType.class);
    private List<Player> playerList;
    private List<Card> cardDeck;
    private Card cardSelected;

    @FXML
    Canvas mainCanvas;

    @FXML
    AnchorPane anchorPane;


    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0017), new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {
            gc.clearRect(0, 0,  width, height);
            if (isInMenu) mainMenu.draw();
            else {
                table.draw();
                if (cardSelected != null) {
                    cardSelected.draw(mouseX-(Card.cardWidthSize/2f), mouseY-(Card.cardHeightSize/2f));
                }
                playerList.forEach(Player::drawCards);
            }
        }
    })
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mediaPlayerClick = new MediaPlayer(new Media(new File("src/main/resources/audio/click.mp3").toURI().toString()));
        repartPlayer = new MediaPlayer(new Media(new File("src/main/resources/audio/type.mp3").toURI().toString()));
        repartPlayer.setVolume(100);
        mediaPlayerClick.setVolume(100);
        gc = mainCanvas.getGraphicsContext2D();
        mainMenu = new Menu();
        timeline.setCycleCount(Timeline.INDEFINITE);
        table = new Table("images/table.png");
        playerList = new ArrayList<>();

        List<Card> baraja  = new ArrayList<>();

        // Crear baraja.

        for (int i = 0; i < 5; i++) {
            Player tmp = new Player(i);
            for (int j = 0; j < 4; j++) {
                tmp.addCard(Card.getRandomType(), j + i +1, j);
            }
            playerList.add(tmp);
        }
    }

    public void setScene(Scene sc) {
        scene = sc;
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseX = event.getX();
                mouseY = event.getY();
            }
        });
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Point2D point = new Point2D(mouseEvent.getX(),mouseEvent.getY());
                mediaPlayerClick.stop();
                mediaPlayerClick.play();
                if (isInMenu){
                    if (mainMenu.playButtonClicked(point)) {
                        isInMenu = false;
                        new Thread(() -> {
                            for (int i = 0; i < playerList.size() -1; i++) {
                                int finalI = i;
                                playerList.forEach(player -> {
                                    try {
                                        repartPlayer.stop();
                                        repartPlayer.play();
                                        player.getCardList().get(finalI).setHide(false);
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                        }).start();
                    }
                    else if (mainMenu.exitButtonClicked(point)){
                        Platform.exit();
                        System.exit(0);
                    }
                }
                else {
                    playerList.get(0).getCardList().forEach(card -> {
                        if (card.isCardClicked(point)) {
                            if (cardSelected != null){
                                cardSelected.setHide(false);

                                // Caso para dejar la carta donde estaba al principio
                                if (card.getCardPosition() == cardSelected.getCardPosition()) {
                                    cardSelected = null;
                                    return;
                                }
                            }
                            cardSelected = card;
                            card.setHide(true);
                        }
                    });
                }
            }
        });
    }

    public void setWidth(double width) {
        mainCanvas.setWidth(width);
        MainWindow.width = width;
        MainWindow.diferenceWidth = width / baseWidth;
    }

    public void setHeight(double height) {
        mainCanvas.setHeight(height);
        MainWindow.height = height;
        MainWindow.diferenceHeight = height / baseHeight;
    }

    public void start(){
        timeline.play();
    }

    public Canvas getMainCanvas() {
        return mainCanvas;
    }

    public static GraphicsContext getGraphicsContext() {
        return gc;
    }
}
