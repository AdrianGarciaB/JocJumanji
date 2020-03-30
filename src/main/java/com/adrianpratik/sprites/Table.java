package com.adrianpratik.sprites;

import com.adrianpratik.control.MainWindow;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private Image tableSprite;
    private List<Image> cardList;

    public Table(String URI){
        tableSprite = new Image(URI);
        cardList = new ArrayList<>();
        cardList.add(new Image("images/back1.png"));
        cardList.add(new Image("images/back1.png"));
        cardList.add(new Image("images/back1.png"));
        cardList.add(new Image("images/back1.png"));
        
    }

    public void draw(){
        MainWindow.getGraphicsContext().drawImage(tableSprite, 0,0, MainWindow.width, MainWindow.height);

        // Posicion cartas jugador 5
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 1316*MainWindow.diferenceWidth, 309*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(1), 1364*MainWindow.diferenceWidth, 309*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(2), 1316*MainWindow.diferenceWidth, 376*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(3), 1364*MainWindow.diferenceWidth, 376*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);

    }
}
