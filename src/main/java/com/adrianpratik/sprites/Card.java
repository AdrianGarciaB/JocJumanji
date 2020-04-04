package com.adrianpratik.sprites;


import com.adrianpratik.control.MainWindow;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Card{
    private Type type;
    private enum Type{Club, Diamond, Heart, Spade}
    private int cardNumber;
    private double x, y;
    private Image sprite;
    private boolean hide;
    private int rotation;

    public Card(double coordX, double coordY, Type cardType, int cardNumber, int rotation){
        type = cardType;
        this.x = coordX;
        this.y = coordY;
        this.cardNumber = cardNumber;
        this.rotation = rotation;
        this.sprite = new Image(MainWindow.imageURI +cardNumber+"-"+type+".png");

        if (rotation != 0) this.sprite = getRotatedImage(sprite, this.rotation, this.sprite.getWidth(), this.sprite.getHeight());


        //TODO select correct sprite
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

    public void draw(int X, int Y){
        if (hide) return;
        MainWindow.getGraphicsContext().drawImage(sprite, getX()*MainWindow.diferenceWidth, getY()*MainWindow.diferenceHeight, 33*MainWindow.diferenceWidth, 55*MainWindow.diferenceHeight);
    }

    private Image getRotatedImage(Image image, int rotation, double width, double height){
        // Imagen rotada
        ImageView iv = new ImageView(image);
        iv.setRotate(rotation);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        iv.setFitWidth(width);
        iv.setFitHeight(height);
        return iv.snapshot(params, null);
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