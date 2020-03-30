package com.adrianpratik.sprites;

import com.adrianpratik.control.MainWindow;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

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

        // Player 4
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 523*MainWindow.diferenceWidth, 309*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 523*MainWindow.diferenceWidth, 376*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 571*MainWindow.diferenceWidth, 309*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 571*MainWindow.diferenceWidth, 376*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);


        // Posicion cartas jugador 1
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 927*MainWindow.diferenceWidth, 683*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 927*MainWindow.diferenceWidth, 750*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 975*MainWindow.diferenceWidth, 683*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 975*MainWindow.diferenceWidth, 750*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);

        // Player 2
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 523*MainWindow.diferenceWidth, 683*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 523*MainWindow.diferenceWidth, 750*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 571*MainWindow.diferenceWidth, 683*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 571*MainWindow.diferenceWidth, 750*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);

        // Player 7
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 1316*MainWindow.diferenceWidth, 683*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 1316*MainWindow.diferenceWidth, 750*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 1364*MainWindow.diferenceWidth, 683*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 1364*MainWindow.diferenceWidth, 750*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);

        // Player 3
        MainWindow.getGraphicsContext().drawImage(getRotatedImage(cardList.get(0), 90, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight), 287*MainWindow.diferenceWidth, 517*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(getRotatedImage(cardList.get(0), 90, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight), 287*MainWindow.diferenceWidth, 565*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(getRotatedImage(cardList.get(0), 90, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight), 354*MainWindow.diferenceWidth, 517*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(getRotatedImage(cardList.get(0), 90, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight), 354*MainWindow.diferenceWidth, 565*MainWindow.diferenceHeight);

        // Player 4
        MainWindow.getGraphicsContext().drawImage(getRotatedImage(cardList.get(0), 90, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight), 1525*MainWindow.diferenceWidth, 499*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(getRotatedImage(cardList.get(0), 90, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight), 1525*MainWindow.diferenceWidth, 547*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(getRotatedImage(cardList.get(0), 90, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight), 1592*MainWindow.diferenceWidth, 499*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(getRotatedImage(cardList.get(0), 90, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight), 1592*MainWindow.diferenceWidth, 547*MainWindow.diferenceHeight);

        // Put Cards
        MainWindow.getGraphicsContext().drawImage(getRotatedImage(cardList.get(0), -60, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight), 886*MainWindow.diferenceWidth, 512*MainWindow.diferenceHeight);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 903*MainWindow.diferenceWidth, 513*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);


        // One Cards See
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 996*MainWindow.diferenceWidth, 513*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
    }

    private Image getRotatedImage(Image image, int position, double width, double height){
        // Imagen rotada
        ImageView iv = new ImageView(image);
        iv.setRotate(position);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        iv.setFitWidth(width);
        iv.setFitHeight(height);
        return iv.snapshot(params, null);
    }
}
