package com.adrianpratik.sprites;

import com.adrianpratik.control.MainWindow;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private Image tableSprite;
    private List<Image> cardList;

    public Table(String URI){
        tableSprite = new Image(URI);
        cardList = new ArrayList<>();
        cardList.add(new Image("images/1.png"));
    }

    public void draw(){
        MainWindow.getGraphicsContext().drawImage(tableSprite, 0,0, MainWindow.width, MainWindow.height);
        MainWindow.getGraphicsContext().drawImage(cardList.get(0), 1286, 307, 48, 71);
    }
}
