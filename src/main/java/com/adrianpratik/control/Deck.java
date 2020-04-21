package com.adrianpratik.control;

import com.adrianpratik.model.DeckType;
import com.adrianpratik.model.DiscardDeckType;
import com.adrianpratik.sprites.Card;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> mainDeck;
    private Card lastDiscartedCard;
    private static Point2D mainDeckPoints;
    private static Point2D discardDeckPoints;

    public Deck(){
        mainDeck = new ArrayList<>();
        DeckType tmp = GameWindow.cardsPositions.getDeck();
        DiscardDeckType tmp2 = GameWindow.cardsPositions.getDiscardDeck();
        mainDeckPoints = new Point2D(tmp.getX(), tmp.getY());
        discardDeckPoints = new Point2D(tmp2.getX(), tmp2.getY());
    }

    public static Point2D getDiscardDeckPoints() {
        return discardDeckPoints;
    }

    public static Point2D getMainDeckPoints() {
        return mainDeckPoints;
    }

    public List<Card> getMainDeck() {
        return mainDeck;
    }

    public void drawDiscardDeck(){
        if (lastDiscartedCard != null) {
            lastDiscartedCard.draw();
        }
    }

    public boolean isDiscardDeckClicked(Point2D p){
        return getDiscardCardBoundary().contains(p);
    }

    public Rectangle2D getDiscardCardBoundary() {
        return new Rectangle2D(discardDeckPoints.getX()* GameWindow.diferenceWidth, discardDeckPoints.getY()* GameWindow.diferenceHeight, Card.cardWidthSize* GameWindow.diferenceWidth, Card.cardHeightSize* GameWindow.diferenceHeight);
    }

    public boolean isMainDeckClicked(Point2D p){
        return getMainDeckBoundary().contains(p);
    }

    public Rectangle2D getMainDeckBoundary() {
        return new Rectangle2D(mainDeckPoints.getX()* GameWindow.diferenceWidth, mainDeckPoints.getY()* GameWindow.diferenceHeight, Card.cardWidthSize* GameWindow.diferenceWidth, Card.cardHeightSize* GameWindow.diferenceHeight);
    }

    public void discardCard(Card card){
        lastDiscartedCard = card;
        lastDiscartedCard.setX(discardDeckPoints.getX());
        lastDiscartedCard.setY(discardDeckPoints.getY());
        lastDiscartedCard.setHide(false);
        System.out.println(discardDeckPoints.getX()+":"+discardDeckPoints.getY());
        System.out.println(card.isHide() + ":" + card.isFlipped());

    }

    public boolean equalsCardValue(Card card){
        return card.getCardNumber() == lastDiscartedCard.getCardNumber();
    }

    public Card getLastDiscartedCard() {
        return lastDiscartedCard;
    }
}
