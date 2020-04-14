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
        cardList.add(new Image("images/back.png"));
        cardList.add(new Image("images/back.png"));
        cardList.add(new Image("images/back.png"));
        cardList.add(new Image("images/back.png"));

    }

    public void draw(){
        MainWindow.getGraphicsContext().drawImage(tableSprite, 0,0, MainWindow.width, MainWindow.height);
    }




}
