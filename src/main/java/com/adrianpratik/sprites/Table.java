package com.adrianpratik.sprites;

import com.adrianpratik.control.GameWindow;
import javafx.scene.image.Image;

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
        GameWindow.getGraphicsContext().drawImage(tableSprite, 0,0, GameWindow.width, GameWindow.height);
    }

}
