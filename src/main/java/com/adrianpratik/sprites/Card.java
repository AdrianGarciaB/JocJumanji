package com.adrianpratik.sprites;


import com.adrianpratik.control.MainWindow;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Card{
    private Type type;
    private enum Type{Club, Diamond, Heart, Spade}
    private int cardNumber;
    private Point2D coordinates;
    private Image sprite;
    private boolean hide;

    public Card(double coordX, double coordY, int cardNumber){
        type = getRandomType();
        coordinates = new Point2D(coordX, coordY);
        cardNumber = cardNumber;
        //TODO select correct sprite
    }

    private Type getRandomType(){
        int random = (int) (Math.random()*4+1);
        switch (random){
            case 1: return Type.Club;
            case 2: return Type.Diamond;
            case 3: return Type.Heart;
            case 4: return Type.Spade;
        }
        return null;
    }

    public void draw(){
        if (hide) return;
        MainWindow.getGraphicsContext().drawImage(sprite, 1364*MainWindow.diferenceWidth, 309*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
    }
}