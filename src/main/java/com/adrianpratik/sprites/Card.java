package com.adrianpratik.sprites;


import com.adrianpratik.control.Deck;
import com.adrianpratik.control.GameController;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Card{
    public static final int cardWidthSize = 43;
    public static final int cardHeightSize = 65;
    public static final Image cardFlipped = new Image(GameController.imageURI +"back.png");
    private Type type;
    public enum Type{Club, Diamond, Heart, Spade}
    private int cardNumber;
    private double x, y;
    private Image sprite;
    private boolean flipped;
    private boolean hide;
    private int cardPosition;

    public Card(int x, int y, Type cardType, int cardNumber, int cardPosition){
        type = cardType;
        this.x = x;
        this.y = y;
        this.cardNumber = cardNumber;
        this.sprite = new Image(GameController.imageURI +cardNumber+"-"+type+".png");
        this.cardPosition = cardPosition;
        flipped = false;
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

    public static Type getRandomTypeById(int typeId){
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
        if (flipped) GameController.getGraphicsContext().drawImage(cardFlipped, getX()* GameController.diferenceWidth, getY()* GameController.diferenceHeight, cardWidthSize* GameController.diferenceWidth, cardHeightSize* GameController.diferenceHeight);
        else GameController.getGraphicsContext().drawImage(sprite, getX()* GameController.diferenceWidth, getY()* GameController.diferenceHeight, cardWidthSize* GameController.diferenceWidth, cardHeightSize* GameController.diferenceHeight);
    }

    public void draw(double X, double Y){
        if (flipped) GameController.getGraphicsContext().drawImage(cardFlipped, X, Y, cardWidthSize* GameController.diferenceWidth, cardHeightSize* GameController.diferenceHeight);
        else GameController.getGraphicsContext().drawImage(sprite, X, Y, cardWidthSize* GameController.diferenceWidth, cardHeightSize* GameController.diferenceHeight);
    }

    public boolean isCardClicked(Point2D p){
        if (x == Deck.getDiscardDeckPoints().getX() && y == Deck.getDiscardDeckPoints().getY()) return false;
        return getCardBoundary().contains(p);
    }

    public Rectangle2D getCardBoundary() {
        return new Rectangle2D(x* GameController.diferenceWidth, y* GameController.diferenceHeight, cardWidthSize* GameController.diferenceWidth, cardHeightSize* GameController.diferenceHeight);
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

    public void resetPositions(){
        x = -100;
        y = -100;
    }


}