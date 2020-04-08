package com.adrianpratik.sprites;


import com.adrianpratik.control.MainWindow;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Card{
    private static final int cardWidthSize = 33;
    private static final int cardHeightSize = 48;
    private Type type;
    public enum Type{Club, Diamond, Heart, Spade}
    private int cardNumber;
    private double x, y;
    private Image sprite;
    private boolean hide;
    private int rotation;

    public Card(double coordX, double coordY, Type cardType, int cardNumber){
        type = cardType;
        this.x = coordX;
        this.y = coordY;
        this.cardNumber = cardNumber;
        this.sprite = new Image(MainWindow.imageURI +cardNumber+"-"+type+".png");
        fixImage();

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

    public void draw(){
        if (hide) return;
        // 33, 55
        MainWindow.getGraphicsContext().drawImage(sprite, getX()*MainWindow.diferenceWidth, getY()*MainWindow.diferenceHeight, cardWidthSize*MainWindow.diferenceWidth, cardHeightSize*MainWindow.diferenceHeight);

    }


    private Image getRotatedImage(Image image, int rotation, double width, double height){
        ImageView iv = new ImageView(image);
        iv.setRotate(rotation);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        return iv.snapshot(params, null);
    }

    private void fixImage(){
        ImageView tmp = new ImageView(this.sprite);
        tmp.setFitWidth(cardWidthSize*MainWindow.diferenceWidth);
        tmp.setFitHeight(cardHeightSize*MainWindow.diferenceHeight);
        this.sprite = tmp.getImage();
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

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
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
}