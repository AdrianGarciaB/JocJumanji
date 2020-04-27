package com.adrianpratik.sprites;


import com.adrianpratik.control.Deck;
import com.adrianpratik.control.GameWindow;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Objects;

public class Card implements Serializable{
    public static final long serialVersionUID = 1L;
    public static final int cardWidthSize = 43;
    public static final int cardHeightSize = 65;
    public static final Image cardFlipped = new Image(GameWindow.imageURI +"back.png");
    private Type type;
    public enum Type{Club, Diamond, Heart, Spade}
    private int cardNumber;
    private double x, y;
    private Image sprite;
    private boolean flipped;
    private boolean hide;
    private int cardPosition;
    public boolean discarted;

    public Card(int x, int y, Type cardType, int cardNumber, int cardPosition, boolean withSprite){
        type = cardType;
        this.x = x;
        this.y = y;
        this.cardNumber = cardNumber;
        if (withSprite) generateSprite();
        this.cardPosition = cardPosition;
        flipped = false;
    }

    public Card(int x, int y, int cardPosition){
        this.x = x;
        this.y = y;
        this.cardPosition = cardPosition;
        flipped = true;
    }

    public static Type getRandomType(){
        int random = (int) (Math.random()*4+1);
        switch (random){
            case 1: return Type.Club;
            case 2: return Type.Diamond;
            case 3: return Type.Heart;
            case 4: return Type.Spade;
        }
        return null;
    }

    public static Type getTypeById(int typeId){
        switch (typeId){
            case 1: return Type.Club;
            case 2: return Type.Diamond;
            case 3: return Type.Heart;
            case 4: return Type.Spade;
        }
        return null;
    }

    public void draw(){
        if (hide) return;
        if (flipped) GameWindow.getGraphicsContext().drawImage(cardFlipped, getX()* GameWindow.diferenceWidth, getY()* GameWindow.diferenceHeight, cardWidthSize* GameWindow.diferenceWidth, cardHeightSize* GameWindow.diferenceHeight);
        else GameWindow.getGraphicsContext().drawImage(sprite, getX()* GameWindow.diferenceWidth, getY()* GameWindow.diferenceHeight, cardWidthSize* GameWindow.diferenceWidth, cardHeightSize* GameWindow.diferenceHeight);
    }

    public void draw(double X, double Y){
        if (flipped) GameWindow.getGraphicsContext().drawImage(cardFlipped, X, Y, cardWidthSize* GameWindow.diferenceWidth, cardHeightSize* GameWindow.diferenceHeight);
        else GameWindow.getGraphicsContext().drawImage(sprite, X, Y, cardWidthSize* GameWindow.diferenceWidth, cardHeightSize* GameWindow.diferenceHeight);
    }

    public boolean isCardClicked(Point2D p){
        if (x == Deck.getDiscardDeckPoints().getX() && y == Deck.getDiscardDeckPoints().getY()) return false;
        return getCardBoundary().contains(p) && !discarted;
    }

    public Rectangle2D getCardBoundary() {
        return new Rectangle2D(x* GameWindow.diferenceWidth, y* GameWindow.diferenceHeight, cardWidthSize* GameWindow.diferenceWidth, cardHeightSize* GameWindow.diferenceHeight);
    }

    public Point2D getCardCoordsByPlayer(int playerId, int position){
        return new Point2D(1, 2);
    }


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public Point2D getCoordinates() {
        return new Point2D(x, y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getCardPosition() {
        return cardPosition;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public void setCardPosition(int cardPosition) {
        this.cardPosition = cardPosition;
    }

    public void resetPositions(){
        x = -100;
        y = -100;
    }

    public void generateSprite(){
        try {
            this.sprite = new Image(GameWindow.imageURI +cardNumber+"-"+type+".png");
        }
        catch (IllegalArgumentException e){
            System.err.println(cardNumber + ":" + type);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardNumber == card.cardNumber &&
                Double.compare(card.x, x) == 0 &&
                Double.compare(card.y, y) == 0 &&
                flipped == card.flipped &&
                hide == card.hide &&
                cardPosition == card.cardPosition &&
                type == card.type &&
                Objects.equals(sprite, card.sprite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, cardNumber, x, y, sprite, flipped, hide, cardPosition);
    }
}