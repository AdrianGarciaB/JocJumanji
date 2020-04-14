package com.adrianpratik.sprites;


import com.adrianpratik.control.MainWindow;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Card{
    public static final int cardWidthSize = 43;
    public static final int cardHeightSize = 65;
    public static final Image cardFlipped = new Image(MainWindow.imageURI +"back.png");
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
        this.sprite = new Image(MainWindow.imageURI +cardNumber+"-"+type+".png");
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
        if (flipped) MainWindow.getGraphicsContext().drawImage(cardFlipped, getX()*MainWindow.diferenceWidth, getY()*MainWindow.diferenceHeight, cardWidthSize*MainWindow.diferenceWidth, cardHeightSize*MainWindow.diferenceHeight);
        else MainWindow.getGraphicsContext().drawImage(sprite, getX()*MainWindow.diferenceWidth, getY()*MainWindow.diferenceHeight, cardWidthSize*MainWindow.diferenceWidth, cardHeightSize*MainWindow.diferenceHeight);
    }

    public void draw(double X, double Y){
        MainWindow.getGraphicsContext().drawImage(sprite, X, Y, cardWidthSize*MainWindow.diferenceWidth, cardHeightSize*MainWindow.diferenceHeight);
    }

    public boolean isCardClicked(Point2D p){
        return getCardBoundary().contains(p);
    }

    public Rectangle2D getCardBoundary() {
        return new Rectangle2D(x*MainWindow.diferenceWidth, y*MainWindow.diferenceHeight, cardWidthSize*MainWindow.diferenceWidth, cardHeightSize*MainWindow.diferenceHeight);
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
}