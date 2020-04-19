package com.adrianpratik.control;

import com.adrianpratik.model.CardsPositionType;
import com.adrianpratik.model.CardListResponse;
import com.adrianpratik.sprites.Card;
import com.adrianpratik.sprites.MainMenu;
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
import javafx.scene.image.Image;
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

public class GameWindow implements Initializable, EventHandler<MouseEvent> {
    public static final double baseWidth = 1920;
    public static final double baseHeight = 1080;
    public static final String imageURI = "images/";
    public static final CardsPositionType cardsPositions = JAXB.unmarshal(new File("src/main/resources/xml/cardPositions.xml"), CardsPositionType.class);
    public static double width = baseWidth;
    public static double height = baseHeight;
    public static double diferenceWidth = 1f;
    public static double diferenceHeight = 1f;
    private double mouseX, mouseY;
    private Scene scene;
    private static GraphicsContext gc;
    private MainMenu mainMenu;
    public boolean isInMenu = true;
    private Table table;
    private Image signImageGame;
    private MediaPlayer mediaPlayerClick;
    private MediaPlayer repartPlayer;
    private List<Player> playerList;
    private Card cardDeck;
    private Card cardSelected;
    private Deck decks;
    private Image gameInfoImage;
    private GameClient gameClient;
    public boolean isMyTurn;

    @FXML
    Canvas mainCanvas;

    @FXML
    AnchorPane anchorPane;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0017), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            gc.clearRect(0, 0, width, height);
            if (isInMenu) mainMenu.draw();
            else {
                table.draw();
                playerList.forEach(Player::drawCards);

                cardDeck.draw();

                if (cardSelected != null) {
                    cardSelected.draw(mouseX - ((Card.cardWidthSize*diferenceWidth) / 2f), mouseY - ((Card.cardHeightSize*diferenceHeight) / 2f));
                }
                decks.drawDiscardDeck();
                GameWindow.getGraphicsContext().drawImage(signImageGame, 560*diferenceWidth, 25*diferenceHeight, 820*diferenceWidth, 120*diferenceHeight);
                if (gameInfoImage != null ){
                    GameWindow.getGraphicsContext().drawImage(gameInfoImage, 560*diferenceWidth, 25*diferenceHeight, 820*diferenceWidth, 120*diferenceHeight);
                }
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
        mainMenu = new MainMenu();
        timeline.setCycleCount(Timeline.INDEFINITE);
        table = new Table("images/table.png");
        playerList = new ArrayList<>();
        decks = new Deck();
        signImageGame = new Image(GameWindow.imageURI + "sign.png");
        cardDeck = new Card((int) Deck.getMainDeckPoints().getX(), (int) Deck.getMainDeckPoints().getY(), Card.getRandomType(), 1,1, true);
        cardDeck.setFlipped(true);
    }

    public void setScene(Scene sc) {
        scene = sc;
        scene.setOnMouseMoved(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
        });
        scene.setOnMouseDragged(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
        });
        scene.setOnMouseClicked(this);
    }

    public void setWidth(double width) {
        mainCanvas.setWidth(width);
        GameWindow.width = width;
        GameWindow.diferenceWidth = width / baseWidth;
    }

    public void setHeight(double height) {
        mainCanvas.setHeight(height);
        GameWindow.height = height;
        GameWindow.diferenceHeight = height / baseHeight;
    }

    public void start() {
        timeline.play();
    }

    public Canvas getMainCanvas() {
        return mainCanvas;
    }


    public static GraphicsContext getGraphicsContext() {
        return gc;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        Point2D point = new Point2D(mouseEvent.getX(),mouseEvent.getY());
        mediaPlayerClick.stop();
        mediaPlayerClick.play();

        if (isInMenu && mainMenu.isConnecting() == false){
            if (mainMenu.playButtonClicked(point)) {
                gameClient = new GameClient(this);
                mainMenu.setConnecting(true);
                gameClient.start();
            }
            else if (mainMenu.exitButtonClicked(point)){
                Platform.exit();
            }
        }
        else if(!isInMenu){
            playerList.get(0).getCardList().forEach(card -> {
                if (decks.isDiscardDeckClicked(point) && cardSelected != null){
                    if (decks.equalsCardValue(cardSelected)) {
                        System.out.println("OK");
                    }
                    else System.out.println("FALLO");

                    decks.discardCard(cardSelected);
                    cardSelected = null;
                }
                else if (card.isCardClicked(point)) {
                    System.out.println("test");
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

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setGameInfoImage(Image gameInfoImage) {
        this.gameInfoImage = gameInfoImage;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public void springCards(final Object data) {
        final CardListResponse response = (CardListResponse) data;
        new Thread(() -> {
            if (response == null){
                System.err.println("Response is Null");
            }
            else {
                for (int i = 0; i < 2; i++) {
                    Player tmp = new Player(i);
                    playerList.add(tmp);
                    if (i == 0) {
                        for (int j = 0; j < 2; j++) {
                            tmp.addCard(response.getCardList().get(j), j, false);
                            repartSound();
                        }
                        for (int j = 2; j < 4; j++) {
                            tmp.addCard(null, 0, j);
                            repartSound();
                        }
                    }
                    else {
                        for (int j = 0; j < 4; j++) {
                            tmp.addCard(null, 0, j);
                            repartSound();

                        }
                    }

                }
            }
        }).start();
    }

    private void repartSound() {
        repartPlayer.stop();
        repartPlayer.play();
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignore) {
        }
    }

    public Deck getDecks() {
        return decks;
    }
}
