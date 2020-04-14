package com.adrianpratik.control;

import com.adrianpratik.model.CardType;
import com.adrianpratik.model.PlayerType;
import com.adrianpratik.sprites.Card;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int playerNumber;
    private List<Card> cardList;

    public Player(int playerNumber){
        this.playerNumber = playerNumber;
        cardList = new ArrayList<>();
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void addCard(Card.Type type, int cardNumber, int cardPosition){
        System.out.println(cardNumber);
        CardType cardPositions = MainWindow.cardsPositions.getPlayer().get(playerNumber).getCard().get(cardPosition);
        Card tmp = new Card(cardPositions.getX(), cardPositions.getY(), type, cardNumber, cardPosition);
        tmp.setHide(true);
        cardList.add(tmp);
    }

    public void drawCards(){
        cardList.forEach(Card::draw);
    }
}